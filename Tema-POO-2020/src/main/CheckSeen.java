package main;

import fileio.Input;

/**
    Aici am implementat o metoda care ma ajuta sa vad daca un video a mai fost vazut
    de un anumit user, dat ca parametru
    Metoda intoarce 0 sau 1, 0 daca nu l-a gasit si, respectiv, 1 daca l-a gasit
 */
final class CheckSeen {

    private int checker = 0;
    public int check(final Input input, final String title, final String username) {
        for (int i = 0; i < input.getUsers().size(); ++i) {
            if (input.getUsers().get(i).getUsername().equals(username)) {
                if (input.getUsers().get(i).getHistory().containsKey(title)) {
                    checker = 1;
                } else {
                    checker = 0;
                }
            }
        }
        return checker;
    }

    public int getChecker() {
        return checker;
    }

    public void setChecker(final int checker) {
        this.checker = checker;
    }
}
