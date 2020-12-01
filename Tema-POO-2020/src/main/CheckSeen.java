package main;

import fileio.Input;

/*
    Clasa care ma ajuta sa vad daca un video a mai fost vazut
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
