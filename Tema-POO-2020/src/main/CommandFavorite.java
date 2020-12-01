package main;

import fileio.Input;
/**
    Aici am implememtat o metoda care ma ajuta sa vad daca un video se afla deja
    in lista de favorite a unui user, dat ca parametru
    Metoda intoarce mesaje corespunzatoare raspunusului gasit
 */

final class CommandFavorite {

    private String myString;
    public String commandFavorite(final Input input, final String username, final String title) {

        for (int i = 0; i < input.getUsers().size(); ++i) {
            if (input.getUsers().get(i).getUsername().equals(username)) {
                if (input.getUsers().get(i).getHistory().containsKey(title)) {
                    if (input.getUsers().get(i).getFavoriteMovies().contains(title)) {
                        myString = "error -> " + title + " is already in favourite list";
                    } else {
                        input.getUsers().get(i).getFavoriteMovies().add(title);
                        myString = "success -> " + title + " was added as favourite";
                    }
                } else {
                    myString = "error -> " + title + " is not seen";
                }
            }
        }
        return myString;
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(final String myString) {
        this.myString = myString;
    }
}
