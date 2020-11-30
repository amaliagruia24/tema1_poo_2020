package main;

import fileio.Input;
import fileio.ShowInput;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;

import java.util.logging.SocketHandler;

public class CommandView {

    String outText;
    public String commandView(final Input input, final String title, final String username) {
        for (int i = 0; i < input.getUsers().size();++i) {
            if (input.getUsers().get(i).getUsername().equals(username)) {
                if (input.getUsers().get(i).getHistory().containsKey(title)) {
                    input.getUsers().get(i).getHistory().put(title,
                            input.getUsers().get(i).getHistory().get(title) + 1);
                    outText = "success -> " + title + " was viewed with total views of " +
                            input.getUsers().get(i).getHistory().get(title);
                } else {
                    input.getUsers().get(i).getHistory().put(title, 1);
                    outText = "success -> " + title + " was viewed with total views of " +
                            input.getUsers().get(i).getHistory().get(title);
                }
            }
        }
    return outText;
    }
}
