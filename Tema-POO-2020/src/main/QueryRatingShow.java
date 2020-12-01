package main;

import fileio.Input;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Metoda care intoarce un mesaj cu un ArrayList de seriale, dupa rating-ul
 * dat de users.
 * Ca sa vad rating-ul unui serial, am cautat in actions ce rating i s-a dat,
 * tinand cont de existenta in history a user-ului sau nu.
 */
final class QueryRatingShow {
    private String outText;

    public String getRatedShows(final Input input, final int actionId) {
        ArrayList<String> serials = new ArrayList<>();
        CheckSeen checkSeen = new CheckSeen();
        for (int i = 0; i < input.getSerials().size(); ++i) {
            if (input.getSerials().get(i).getGenres().contains(
                    input.getCommands().get(actionId).getFilters().get(1).get(0))
                    && Integer.toString(input.getSerials().get(i).getYear()).equals(
                    input.getCommands().get(actionId).getFilters().get(0).get(0))) {
                serials.add(input.getSerials().get(i).getTitle());
            }
        }
        Map<String, Double> ratedMovies = new LinkedHashMap<>();
        for (int i = 0; i < serials.size(); ++i) {
            for (int j = 0; j < input.getCommands().size(); ++j) {
                if (input.getCommands().get(j).getActionType().equals("command")
                        && input.getCommands().get(j).getType().equals("rating")
                        && input.getCommands().get(j).getTitle().equals(serials.get(i))) {
                    for (int k = 0; k < input.getUsers().size(); ++k) {
                        if (input.getUsers().get(k).getUsername().equals(
                                input.getCommands().get(j).getUsername())) {
                            if (checkSeen.check(input, serials.get(i),
                                    input.getCommands().get(j).getUsername()) == 1) {
                                ratedMovies.put(serials.get(i),
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
    }

    public String getOutText() {
        return outText;
    }

    public void setOutText(final String outText) {
        this.outText = outText;
    }
}
