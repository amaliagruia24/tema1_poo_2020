package main;

import fileio.Input;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

final class QueryFavoriteShow {
    private String outText;

    public String listFavoriteMovies(final Input input, final String sortType,
                                     final int current, final int n) {
        Map<String, Integer> favoriteMovies = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < input.getSerials().size(); ++i) {
            int noOcurrences = 0;
            if (!input.getCommands().get(current).getFilters().get(1).isEmpty()
                    && !input.getCommands().get(current).getFilters().get(0).isEmpty()) {
                if (input.getSerials().get(i).getGenres().contains(
                        input.getCommands().get(current).getFilters().get(1).get(0))
                        && input.getCommands().get(current).getFilters().get(0).contains(
                                Integer.toString(input.getSerials().get(i).getYear()))) {
                    for (int j = 0; j < input.getUsers().size(); ++j) {
                        if (input.getUsers().get(j).getFavoriteMovies().contains(
                                input.getSerials().get(i).getTitle())) {
                            noOcurrences++;
                        }
                    }
                    if (noOcurrences > 0) {
                        favoriteMovies.put(input.getSerials().get(i).getTitle(), noOcurrences);
                    }
                }
            } else
                if (input.getCommands().get(current).getFilters().get(1).isEmpty()
                        && input.getCommands().get(current).getFilters().get(0).isEmpty()) {
                    for (int j = 0; j < input.getUsers().size(); ++j) {
                        if (input.getUsers().get(j).getFavoriteMovies().contains(
                                input.getSerials().get(i).getTitle())) {
                            noOcurrences++;
                        }
                    }
                    if (noOcurrences > 0) {
                        favoriteMovies.put(input.getSerials().get(i).getTitle(), noOcurrences);
                    }
            } else
                //afisare doar dupa gen
                if (!input.getCommands().get(current).getFilters().get(1).isEmpty()
                        && input.getCommands().get(current).getFilters().get(0).isEmpty()) {
                    if (input.getSerials().get(i).getGenres().contains(
                            input.getCommands().get(current).getFilters().get(1).get(0))) {
                        for (int j = 0; j < input.getUsers().size(); ++j) {
                            if (input.getUsers().get(j).getFavoriteMovies().contains(
                                    input.getSerials().get(i).getTitle())) {
                                noOcurrences++;
                            }
                        }
                        if (noOcurrences > 0) {
                            favoriteMovies.put(input.getSerials().get(i).getTitle(), noOcurrences);
                        }
                    }
                } else
                    //afisare dupa an
                    if (input.getCommands().get(current).getFilters().get(1).isEmpty()
                            && !input.getCommands().get(current).getFilters().get(0).isEmpty()) {
                        if (input.getCommands().get(current).getFilters().get(0).contains(
                                Integer.toString(input.getMovies().get(i).getYear()))) {
                            for (int j = 0; j < input.getUsers().size(); ++j) {
                                if (input.getUsers().get(j).getFavoriteMovies().contains(
                                        input.getSerials().get(i).getTitle())) {
                                    noOcurrences++;
                                }
                            }
                            if (noOcurrences > 0) {
                                favoriteMovies.put(input.getSerials().get(i).getTitle(),
                                        noOcurrences);
                            }
                        }
                    }
        }
        Map<String, Integer> sortedFavoriteMovies = new LinkedHashMap<String, Integer>();
        if (sortType.equals("asc")) {
            favoriteMovies.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEachOrdered(x -> sortedFavoriteMovies.put(x.getKey(), x.getValue()));
        } else {
            favoriteMovies.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEachOrdered(x -> sortedFavoriteMovies.put(x.getKey(), x.getValue()));
        }

        ArrayList<String> favoriteMoviesTitles = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sortedFavoriteMovies.entrySet()) {
            favoriteMoviesTitles.add(entry.getKey());
        }
        ArrayList<String> favorites = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (i >= favoriteMoviesTitles.size()) {
                break;
            }
            favorites.add(favoriteMoviesTitles.get(i));
        }

        outText = "Query result: " + favorites;
        return outText;
    }

    public String getOutText() {
        return outText;
    }

    public void setOutText(final String outText) {
        this.outText = outText;
    }
}
