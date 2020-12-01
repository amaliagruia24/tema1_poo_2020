package main;

import fileio.Input;

import java.util.*;

final class QueryAwardsActors {
    private String outText;

    public String topActorsAwards(final Input input, final String sortType, final int actionId) {
        Map<String, Integer> actors = new LinkedHashMap<>();
        for (int i = 0; i < input.getActors().size(); ++i) {
            int noAwards = 0;
            int counter = 0;
            for (int j = 0; j < input.getCommands().get(actionId).getFilters().get(3).size(); ++j) {
                for (Map.Entry<actor.ActorsAwards, Integer> entry
                        : input.getActors().get(i).getAwards().entrySet()) {
                    if (entry.getKey().toString().equals(
                            input.getCommands().get(actionId).getFilters().get(3).get(j))) {
                        counter++;
                    }
                    noAwards += entry.getValue();
                }
            }
            if (counter == input.getCommands().get(actionId).getFilters().get(3).size()) {
                actors.put(input.getActors().get(i).getName(), noAwards);
            }
        }

        if (actors.isEmpty()) {
            outText = "Query result: []";
            return  outText;
        }

        Map<String, Integer> sortedAwards = new LinkedHashMap<String, Integer>();
        if (sortType.equals("asc")) {
            actors.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEachOrdered(x -> sortedAwards.put(x.getKey(), x.getValue()));
        } else {
            actors.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEachOrdered(x -> sortedAwards.put(x.getKey(), x.getValue()));
        }

        ArrayList<Integer> noAwards = new ArrayList<Integer>();
        ArrayList<String> auxActors = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : sortedAwards.entrySet()) {
            noAwards.add(entry.getValue());
            auxActors.add(entry.getKey());
        }

        for (int i = 0; i < noAwards.size(); ++i) {
            for (int j = i + 1; j < noAwards.size(); ++j) {
                if (noAwards.get(i) == noAwards.get(j)) {
                    if (sortType.equals("asc")) {
                        if (auxActors.get(i).compareTo(auxActors.get(j)) > 0) {
                            Collections.swap(auxActors, i, j);
                        }
                    } else {
                        if (auxActors.get(i).compareTo(auxActors.get(j)) < 0) {
                            Collections.swap(auxActors, i, j);
                        }
                    }
                }
            }
        }
        outText = "Query result: " + auxActors;
        return outText;
    }

    public String getOutText() {
        return outText;
    }

    public void setOutText(final String outText) {
        this.outText = outText;
    }
}
