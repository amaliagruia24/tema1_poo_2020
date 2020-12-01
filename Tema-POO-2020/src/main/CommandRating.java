package main;

import fileio.Input;
/**
    Am implementat aici o metoda care verifica daca un video se afla in istoria unui user
    Daca da, atunci ii poate da rating, daca nu, va afisa un mesaj de eroare
 */
final class CommandRating {
    private String outText;

    public String commandRating(final Input input, final String title, final String username,
                                final double grade) {
        for (int i = 0; i < input.getUsers().size(); ++i) {
            if (input.getUsers().get(i).getUsername().equals(username)) {
                if (input.getUsers().get(i).getHistory().containsKey(title)) {
                    outText = "success -> " + title
                            + " was rated with " + grade + " by " + username;
                } else {
                    outText = "error -> " + title + " is not seen";
                    }

            }
        }
        return  outText;
    }

    public String getOutText() {
        return outText;
    }

    public void setOutText(final String outText) {
        this.outText = outText;
    }
}
