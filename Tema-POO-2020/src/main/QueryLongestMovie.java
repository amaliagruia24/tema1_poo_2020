package main;

import fileio.Input;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

final class QueryLongestMovie {
    private String outText;

    public String longestMovies(final Input input, final String sortType,
                                final int current, final int n) {

        Map<String, Integer> longestMovies = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < input.getMovies().size(); ++i) {
            if (!input.getCommands().get(current).getFilters().get(1).isEmpty()
                    && !input.getCommands().get(current).getFilters().get(0).isEmpty()) {
                if (input.getMovies().get(i).getGenres().contains(
                        input.getCommands().get(current).getFilters().get(1).get(0))
                        && input.getCommands().get(current).getFilters().get(0).contains(
                                Integer.toString(input.getMovies().get(i).getYear()))) {
                    longestMovies.put(input.getMovies().get(i).getTitle(),
                            input.getMovies().get(i).getDuration());
                }
            } else
                if (!input.getCommands().get(current).getFilters().get(1).isEmpty()
                        && input.getCommands().get(current).getFilters().get(0).isEmpty()) {
                    if (input.getMovies().get(i).getGenres().contains(
                            input.getCommands().get(current).getFilters().get(1).get(0))) {
                        longestMovies.put(input.getMovies().get(i).getTitle(),
                                input.getMovies().get(i).getDuration());
                    }
                } else
                    if (input.getCommands().get(current).getFilters().get(1).isEmpty()
                            && !input.getCommands().get(current).getFilters().get(0).isEmpty()) {
                        if (input.getCommands().get(current).getFilters().get(0).contains(
                                Integer.toString(input.getMovies().get(i).getYear()))) {
                            longestMovies.put(input.getMovies().get(i).getTitle(),
                                    input.getMovies().get(i).getDuration());
                        }
                    } else
                        if (input.getCommands().get(current).getFilters().get(1).isEmpty()
                                && input.getCommands().get(current).getFilters().get(0).isEmpty()) {
                            longestMovies.put(input.getMovies().get(i).getTitle(),
                                    input.getMovies().get(i).getDuration());
                        }

        }

        Map<String, Integer> sortedLongestMovies = new LinkedHashMap<String, Integer>();
        if (sortType.equals("asc")) {
            longestMovies.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEachOrdered(x -> sortedLongestMovies.put(x.getKey(), x.getValue()));
        } else {
            longestMovies.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEachOrdered(x -> sortedLongestMovies.put(x.getKey(), x.getValue()));
        }
        ArrayList<String> longestMoviesTitles = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sortedLongestMovies.entrySet()) {
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
