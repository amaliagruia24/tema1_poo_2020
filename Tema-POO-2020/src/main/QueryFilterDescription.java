package main;

import fileio.Input;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Metoda care intoarce un Arraylist de actori sortati alfabetic(asc sau desc),
 * actori selectati in functie de cuvintele continute in descriere
 * Am impartit in cuvinte descrierea(le-am pus intr-un Arraylist)
 * si apoi am cautat in acel Arraylist toate keywords din test.
 * Daca le avea pe toate, adaugam la lista cu actori din final.
 */


final class QueryFilterDescription {
    private String outText;

    public String filterDescription(final Input input, final int current, final String sortType) {
        ArrayList<String> actors = new ArrayList<String>();
        for (int i = 0; i < input.getActors().size(); ++i) {
            String text = input.getActors().get(i).getCareerDescription();
            List<String> words = new ArrayList<String>();
            BreakIterator breakIterator = BreakIterator.getWordInstance();
            breakIterator.setText(text);
            int lastIndex = breakIterator.first();
            while (BreakIterator.DONE != lastIndex) {
                int firstIndex = lastIndex;
                lastIndex = breakIterator.next();
                if (lastIndex != BreakIterator.DONE
                        && Character.isLetterOrDigit(text.charAt(firstIndex))) {
                    words.add(text.substring(firstIndex, lastIndex));
                }
            }
            int counter = 0;
            for (int j = 0; j < input.getCommands().get(current).getFilters().get(2).size(); ++j) {
                if (words.contains(input.getCommands().get(current).getFilters().get(2).get(j))) {
                    counter++;
                }
            }
            if (counter == input.getCommands().get(current).getFilters().get(2).size()) {
                actors.add(input.getActors().get(i).getName());
            }
        }
        if (!actors.isEmpty()) {
            Collections.sort(actors);
        }
        if (sortType.equals("asc")) {
            outText = "Query result: " + actors;
            return outText;
        } else {
            Collections.reverse(actors);
            outText = "Query result: " + actors;
            return outText;
        }
    }

    public String getOutText() {
        return outText;
    }

    public void setOutText(final String outText) {
        this.outText = outText;
    }
}
