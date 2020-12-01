package main;

import fileio.Input;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

final class QueryMostViewedSerial {

    private String outText;

    public String getMostViewedSerials(final Input input, final String sortType,
                                       final int current, final int n) {
        Map<String, Integer> mostViewedSerials = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < input.getSerials().size(); ++i) {
            int totalViews = 0;
            while (input.getCommands().get(current).getFilters().get(1).remove(null)) {
                input.getCommands().get(current).getFilters().get(1).remove(null);
            }
            while (input.getCommands().get(current).getFilters().get(0).remove(null)) {
                input.getCommands().get(current).getFilters().get(0).remove(null);
            }
            if (!input.getCommands().get(current).getFilters().get(1).isEmpty()
                    && !input.getCommands().get(current).getFilters().get(0).isEmpty()) {
                if (input.getSerials().get(i).getGenres().contains(
                        input.getCommands().get(current).getFilters().get(1).get(0))
                        && input.getCommands().get(current).getFilters().get(0).contains(
                        Integer.toString(input.getSerials().get(i).getYear()))) {
                    for (int j = 0; j < input.getUsers().size(); ++j) {
                        if (input.getUsers().get(j).getHistory().containsKey(
                                input.getSerials().get(i).getTitle())) {
                            totalViews += input.getUsers().get(j).getHistory().get(
                                    input.getSerials().get(i).getTitle());
                        }
                    }
                    if (totalViews > 0) {
                        mostViewedSerials.put(input.getSerials().get(i).getTitle(), totalViews);
                    }
                }
            } else
            if (input.getCommands().get(current).getFilters().get(1).isEmpty()
                    && !input.getCommands().get(current).getFilters().get(0).isEmpty()) {
                if (input.getCommands().get(current).getFilters().get(0).contains(
                        Integer.toString(input.getSerials().get(i).getYear()))) {
                    for (int j = 0; j < input.getUsers().size(); ++j) {
                        if (input.getUsers().get(j).getHistory().containsKey(
                                input.getSerials().get(i).getTitle())) {
                            totalViews += input.getUsers().get(j).getHistory().get(
                                    input.getSerials().get(i).getTitle());
                        }
                    }
                    if (totalViews > 0) {
                        mostViewedSerials.put(input.getSerials().get(i).getTitle(), totalViews);
                    }
                }
            } else
            if (!input.getCommands().get(current).getFilters().get(1).isEmpty()
                    && input.getCommands().get(current).getFilters().get(0).isEmpty()) {
                if (input.getSerials().get(i).getGenres().contains(
                        input.getCommands().get(current).getFilters().get(1).get(0))) {
                    for (int j = 0; j < input.getUsers().size(); ++j) {
                        if (input.getUsers().get(j).getHistory().containsKey(
                                input.getSerials().get(i).getTitle())) {
                            totalViews += input.getUsers().get(j).getHistory().get(
                                    input.getSerials().get(i).getTitle());
                        }
                    }
                    if (totalViews > 0) {
                        mostViewedSerials.put(input.getSerials().get(i).getTitle(), totalViews);
                    }
                }
            } else
            if (input.getCommands().get(current).getFilters().get(1).isEmpty()
                    && input.getCommands().get(current).getFilters().get(0).isEmpty()) {
                for (int j = 0; j < input.getUsers().size(); ++j) {
                    if (input.getUsers().get(j).getHistory().containsKey(
                            input.getSerials().get(i).getTitle())) {
                        totalViews += input.getUsers().get(j).getHistory().get(
                                input.getSerials().get(i).getTitle());
                    }
                }
                if (totalViews > 0) {
                    mostViewedSerials.put(input.getSerials().get(i).getTitle(), totalViews);
                }
            }

        }

        Map<String, Integer> sortedMostViewed = new LinkedHashMap<String, Integer>();
        if (sortType.equals("asc")) {
            mostViewedSerials.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEachOrdered(x -> sortedMostViewed.put(x.getKey(), x.getValue()));
        } else {
            mostViewedSerials.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEachOrdered(x -> sortedMostViewed.put(x.getKey(), x.getValue()));
        }
        ArrayList<String> mostViewedMoviesTitles = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sortedMostViewed.entrySet()) {
            mostViewedMoviesTitles.add(entry.getKey());
        }

        ArrayList<String> mostViewed = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (i >= mostViewedMoviesTitles.size()) {
                break;
            }
            mostViewed.add(mostViewedMoviesTitles.get(i));
        }
        outText = "Query result: " + mostViewed;
        return outText;
    }

    public String getOutText() {
        return outText;
    }

    public void setOutText(final String outText) {
        this.outText = outText;
    }
}
