package main;

import fileio.Input;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Metoda care intoarce un mesaj cu un Arraylist de seriale,
 * sortate dupa lungimea lor.
 * Serialel trebuie sa aiba anumite filtre, date in input.
 * Creez un Map cu serialele ce indeplinesc cerintele si
 * durata lor. La final, sortez corespunzator map-ul.
 */

final class QueryLongestSerial {
    private String outText;

    public String longestSerials(final Input input, final String sortType,
                                 final int current, final int n) {

        Map<String, Integer> longestSerials = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < input.getSerials().size(); ++i) {
            int totalD = 0;
            if (!input.getCommands().get(current).getFilters().get(1).isEmpty()
                    && !input.getCommands().get(current).getFilters().get(0).isEmpty()) {
                if (input.getSerials().get(i).getGenres().contains(
                        input.getCommands().get(current).getFilters().get(1).get(0))
                        && input.getCommands().get(current).getFilters().get(0).contains(
                        Integer.toString(input.getSerials().get(i).getYear()))) {
                    for (int j = 0; j < input.getSerials().get(i).getSeasons().size(); ++j) {
                        totalD += input.getSerials().get(i).
                                getSeasons().get(j).getDuration();
                    }
                    longestSerials.put(input.getSerials().get(i).getTitle(), totalD);
                }
            } else
            if (!input.getCommands().get(current).getFilters().get(1).isEmpty()
                    && input.getCommands().get(current).getFilters().get(0).isEmpty()) {
                if (input.getSerials().get(i).getGenres().contains(
                        input.getCommands().get(current).getFilters().get(1).get(0))) {
                    for (int j = 0; j < input.getSerials().get(i).getSeasons().size(); ++j) {
                        totalD += input.getSerials().get(i).getSeasons().get(j).getDuration();
                    }
                    longestSerials.put(input.getSerials().get(i).getTitle(), totalD);
                }
            } else
            if (input.getCommands().get(current).getFilters().get(1).isEmpty()
                    && !input.getCommands().get(current).getFilters().get(0).isEmpty()) {
                if (input.getCommands().get(current).getFilters().get(0).contains(
                        Integer.toString(input.getSerials().get(i).getYear()))) {
                    for (int j = 0; j < input.getSerials().get(i).getSeasons().size(); ++j) {
                        totalD += input.getSerials().get(i).getSeasons().get(j).getDuration();
                    }
                    longestSerials.put(input.getSerials().get(i).getTitle(), totalD);
                }
            } else
            if (input.getCommands().get(current).getFilters().get(1).isEmpty()
                    && input.getCommands().get(current).getFilters().get(0).isEmpty()) {
                for (int j = 0; j < input.getSerials().get(i).getSeasons().size(); ++j) {
                    totalD += input.getSerials().get(i).getSeasons().get(j).getDuration();
                }
                longestSerials.put(input.getSerials().get(i).getTitle(), totalD);
            }
        }
        Map<String, Integer> sortedLongestSerials = new LinkedHashMap<String, Integer>();
        if (sortType.equals("asc")) {
            longestSerials.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEachOrdered(x -> sortedLongestSerials.put(x.getKey(), x.getValue()));
        } else {
            longestSerials.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEachOrdered(x -> sortedLongestSerials.put(x.getKey(), x.getValue()));
        }
        ArrayList<String> longestMoviesTitles = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sortedLongestSerials.entrySet()) {
            longestMoviesTitles.add(entry.getKey());
        }

        ArrayList<String> longest = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (i >= longestMoviesTitles.size()) {
                break;
            }
            longest.add(longestMoviesTitles.get(i));
        }

        outText = "Query result: " + longest;
        return outText;
    }

    public String getOutText() {
        return outText;
    }

    public void setOutText(final String outText) {
        this.outText = outText;
    }
}
