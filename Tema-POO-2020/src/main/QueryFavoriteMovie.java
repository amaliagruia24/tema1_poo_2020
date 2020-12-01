package main;

import fileio.Input;

import java.util.*;

final class QueryFavoriteMovie {
    private String outText;

    public String listFavoriteMovies(final Input input, final String sortType,
                                     final int current, final int n) {
        Map<String, Integer> favoriteMovies = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < input.getMovies().size(); ++i) {
            int noOcurrences = 0;
            while (input.getCommands().get(current).getFilters().get(1).remove(null)) {
                input.getCommands().get(current).getFilters().get(1).remove(null);
            }
            while (input.getCommands().get(current).getFilters().get(0).remove(null)) {
                input.getCommands().get(current).getFilters().get(0).remove(null);
            }
            if (!input.getCommands().get(current).getFilters().get(1).isEmpty()
                    && !input.getCommands().get(current).getFilters().get(0).isEmpty()) {
                if (input.getMovies().get(i).getGenres().contains(
                        input.getCommands().get(current).getFilters().get(1).get(0))
                        && input.getCommands().get(current).getFilters().get(0).contains(
                                Integer.toString(input.getMovies().get(i).getYear()))) {
                    for (int j = 0; j < input.getUsers().size(); ++j) {
                        if (input.getUsers().get(j).getFavoriteMovies().contains(
                                input.getMovies().get(i).getTitle())) {
                            noOcurrences++;
                        }
                    }
                    favoriteMovies.put(input.getMovies().get(i).getTitle(), noOcurrences);
                }
            }
            if (input.getCommands().get(current).getFilters().get(1).isEmpty()
                    && input.getCommands().get(current).getFilters().get(0).isEmpty()) {
                for (int j = 0; j < input.getUsers().size(); ++j) {
                    if (input.getUsers().get(j).getFavoriteMovies().contains(
                            input.getMovies().get(i).getTitle())) {
                        noOcurrences++;
                    }
                }
                favoriteMovies.put(input.getMovies().get(i).getTitle(), noOcurrences);
            }
            //afisare doar dupa gen
            if (!input.getCommands().get(current).getFilters().get(1).isEmpty()
                    && input.getCommands().get(current).getFilters().get(0).isEmpty()) {
                if (input.getMovies().get(i).getGenres().contains(
                        input.getCommands().get(current).getFilters().get(1).get(0))) {
                    for (int j = 0; j < input.getUsers().size(); ++j) {
                        if (input.getUsers().get(j).getFavoriteMovies().contains(
                                input.getMovies().get(i).getTitle())) {
                            noOcurrences++;
                        }
                    }
                    favoriteMovies.put(input.getMovies().get(i).getTitle(), noOcurrences);
                }
            }
            //afisare dupa an
            if (input.getCommands().get(current).getFilters().get(1).isEmpty()
                    && !input.getCommands().get(current).getFilters().get(0).isEmpty()) {
                if (input.getCommands().get(current).getFilters().get(0).contains(
                        Integer.toString(input.getMovies().get(i).getYear()))) {
                    for (int j = 0; j < input.getUsers().size(); ++j) {
                        if (input.getUsers().get(j).getFavoriteMovies().contains(
                                input.getMovies().get(i).getTitle())) {
                            noOcurrences++;
                        }
                    }
                    favoriteMovies.put(input.getMovies().get(i).getTitle(), noOcurrences);
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
        ArrayList<Integer> noViews = new ArrayList<Integer>();
        ArrayList<String> auxMovies = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : sortedFavoriteMovies.entrySet()) {
            noViews.add(entry.getValue());
            auxMovies.add(entry.getKey());
        }
        for (int i = 0; i < noViews.size(); ++i) {
            for (int j = i + 1; j < noViews.size(); ++j) {
                if (noViews.get(i) == noViews.get(j)) {
                    if (sortType.equals("asc")) {
                        if (auxMovies.get(i).compareTo(auxMovies.get(j)) > 0) {
                            Collections.swap(auxMovies, i, j);
                        }
                    } else {
                        if (auxMovies.get(i).compareTo(auxMovies.get(j)) < 0) {
                            Collections.swap(auxMovies, i, j);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < auxMovies.size(); ++i) {
            favoriteMoviesTitles.add(auxMovies.get(i));
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
