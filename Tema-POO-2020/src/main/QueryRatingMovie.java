package main;

import fileio.Input;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

final class QueryRatingMovie {
    private String outText;

    public String getQueryRating(final Input input, final int actionId) {
        ArrayList<String> movies = new ArrayList<>();
        CheckSeen checkSeen = new CheckSeen();
        for (int i = 0; i < input.getMovies().size(); ++i) {
            if (input.getMovies().get(i).getGenres().contains(
                    input.getCommands().get(actionId).getFilters().get(1).get(0))
                    && Integer.toString(input.getMovies().get(i).getYear()).equals(
                            input.getCommands().get(actionId).getFilters().get(0).get(0))) {
                movies.add(input.getMovies().get(i).getTitle());
            }
        }
        if (movies.isEmpty()) {
            outText = "Query result: " + movies;
            return outText;
        }
        //if movies e empty=> query result e []
        //il caut prin commands sa vad unde l gasesc si sa vad daca l a vazut sa i pot da rating
        //daca se afla in history al user ului, adaug la o mapa filmul cu rating ul dat
        // daca mapa mea e goala in final, query result e []
        // pt ca inseamna ca nu l-a vazut nimeni ca sa i dea rating
        Map<String, Double> ratedMovies = new LinkedHashMap<>();
        for (int i = 0; i < movies.size(); ++i) {
            for (int j = 0; j < input.getCommands().size(); ++j) {
                if (input.getCommands().get(j).getActionType().equals("command")
                        && input.getCommands().get(j).getType().equals("rating")
                        && input.getCommands().get(j).getTitle().equals(movies.get(i))) {
                    for (int k = 0; k < input.getUsers().size(); ++k) {
                        if (input.getUsers().get(k).getUsername().equals(
                                input.getCommands().get(j).getUsername())) {
                            if (checkSeen.check(input, movies.get(i),
                                    input.getCommands().get(j).getUsername()) == 1) {
                                ratedMovies.put(movies.get(i),
                                        input.getCommands().get(j).getGrade());
                            }
                        }
                    }
                }
            }
        }
        if (ratedMovies.isEmpty()) {
            outText = "Query result: []";
            return outText;
        }
        return outText;
        //daca e empty, return query result + []
    }

    public String getOutText() {
        return outText;
    }

    public void setOutText(final String outText) {
        this.outText = outText;
    }
}
