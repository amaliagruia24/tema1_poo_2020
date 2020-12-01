package main;

import fileio.Input;

import java.util.*;

final class QueryUsers {

    private String outText;
    public String getActiveUsers(final Input input, final String sortType, final int n) {
        Map<String, Integer> activeUsers = new LinkedHashMap<>();
        CheckSeen checkSeen = new CheckSeen();
        for (int i = 0; i < input.getCommands().size(); ++i) {
            int noratings = 0;
            if (input.getCommands().get(i).getActionType().equals("command")
                    && input.getCommands().get(i).getType().equals("rating")) {
                if (checkSeen.check(input, input.getCommands().get(i).getTitle(),
                        input.getCommands().get(i).getUsername()) == 1) {
                    int ok = 1;
                    if (i > 0) {
                        for (int j = i - 1; j >= 0; --j) {
                            if (input.getCommands().get(j).getTitle() != null
                                    && input.getCommands().get(j).getTitle().equals(
                                            input.getCommands().get(i).getTitle())
                                    && input.getCommands().get(j).getUsername().equals(
                                            input.getCommands().get(i).getUsername())
                                    && input.getCommands().get(j).getSeasonNumber()
                                        == input.getCommands().get(i).getSeasonNumber()
                                    && checkSeen.check(input, input.getCommands().get(j).getTitle(),
                                        input.getCommands().get(j).getUsername()) == 1) {
                                ok = 0;
                                break;
                            }
                        }
                    }
                    if (ok == 1) {
                        noratings++;
                    }
                }
                if (noratings > 0) {
                    if (activeUsers.containsKey(input.getCommands().get(i).getUsername())) {
                        activeUsers.put(input.getCommands().get(i).getUsername(),
                                activeUsers.get(input.getCommands().get(i).getUsername())
                                        + noratings);
                    } else {
                        activeUsers.put(input.getCommands().get(i).getUsername(), noratings);
                    }
                }
            }
        }
        Map<String, Integer> sortedUsers = new LinkedHashMap<>();
        if (sortType.equals("asc")) {
            activeUsers.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEachOrdered(x -> sortedUsers.put(x.getKey(), x.getValue()));
        } else {
            activeUsers.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEachOrdered(x -> sortedUsers.put(x.getKey(), x.getValue()));
        }

        ArrayList<Integer> noRatings = new ArrayList<Integer>();
        ArrayList<String> auxUsers = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : sortedUsers.entrySet()) {
            noRatings.add(entry.getValue());
            auxUsers.add(entry.getKey());
        }

        for (int i = 0; i < noRatings.size(); ++i) {
            for (int j = i + 1; j < noRatings.size(); ++j) {
                if (noRatings.get(i) == noRatings.get(j)) {
                    if (sortType.equals("asc")) {
                        if (auxUsers.get(i).compareTo(auxUsers.get(j)) > 0) {
                            Collections.swap(auxUsers, i, j);
                        }
                    } else {
                        if (auxUsers.get(i).compareTo(auxUsers.get(j)) < 0) {
                            Collections.swap(auxUsers, i, j);
                        }
                    }
                }
            }
        }
        ArrayList<String> topN = new ArrayList<String>();
        for (int i = 0; i < n; ++i) {
            if (i >= auxUsers.size()) {
                break;
            }
            topN.add(auxUsers.get(i));
        }
        outText = "Query result: " + topN;
        return outText;
    }

    public String getOutText() {
        return outText;
    }

    public void setOutText(final String outText) {
        this.outText = outText;
    }
}
