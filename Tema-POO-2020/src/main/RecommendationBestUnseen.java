package main;

import fileio.Input;

import java.util.Map;

final class RecommendationBestUnseen {
    private String outText;

    public String bestUnseen(final Map<String, Double> orderedMovies,
                             final Input input, final String username) {

        for (Map.Entry<String, Double> entry : orderedMovies.entrySet()) {
            for (int i = 0; i < input.getUsers().size(); ++i) {
                if (input.getUsers().get(i).getUsername().equals(username)) {
                    for (int j = 0; j < input.getUsers().get(i).getHistory().size(); ++j) {
                        if (!input.getUsers().get(i).getHistory().containsKey(entry.getKey())) {
                            outText = "BestRatedUnseenRecommendation result: " + entry.getKey();
                            return outText;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < input.getMovies().size(); ++i) {
            for (int j = 0; j < input.getUsers().size(); ++j) {
                if (input.getUsers().get(j).getUsername().equals(username)) {
                    for (int k = 0; k < input.getUsers().get(j).getHistory().size(); ++k) {
                        if (!input.getUsers().get(j).getHistory().containsKey(
                                input.getMovies().get(i).getTitle())) {
                            outText = "BestRatedUnseenRecommendation result: "
                                    + input.getMovies().get(i).getTitle();
                            return outText;
                        }
                    }
                }
            }
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
