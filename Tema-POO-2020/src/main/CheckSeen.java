package main;

import fileio.Input;

/*
    Clasa care ma ajuta sa vad daca un video a mai fost vazut
 */
public class CheckSeen {
    int checker = 0;
    public int check(Input input, String title, String username) {
        for (int i = 0; i < input.getUsers().size(); ++i) {
            if (input.getUsers().get(i).getUsername().equals(username))
                if (input.getUsers().get(i).getHistory().containsKey(title))
                    checker = 1;
                else
                    checker = 0;
        }
        return checker;
    }
}
