package main;

import fileio.Input;

final class RecommendationStandard {
    private String outText;
    private  int j;
    public String recommendationStandard(final Input input, final String username) {
        for (int i = 0; i < input.getUsers().size(); ++i) {
            if (input.getUsers().get(i).getUsername().equals(username)) {
                for (j = 0; j < input.getMovies().size(); ++j) {
                    if (!input.getUsers().get(i).getHistory().containsKey(
                            input.getMovies().get(j).getTitle())) {
                        outText = "StandardRecommendation result: "
                                + input.getMovies().get(j).getTitle();
                        return outText;
                    }
                }
            }
        }
        if (j == input.getMovies().size()) {
            outText = "StandardRecommendation cannot be applied!";
        }
        return outText;
    }

    public String getOutText() {
        return outText;
    }

    public void setOutText(final String outText) {
        this.outText = outText;
    }

    public int getJ() {
        return j;
    }

    public void setJ(final int j) {
        this.j = j;
    }
}
