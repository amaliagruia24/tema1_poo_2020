package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/*
* implementare metoda care returneaza top-ul de N actori cerut in query average actors
* metoda primeste un map cu strings si doubles, o sorteaza crescator sau descrescator(parametru)
* la ratings egale, sorteaza dupa numele actorilor, ii pune in ordine alfabetica
* */
public class QueryAverageActors {

    String outText;
    public String createNTop(Map<String, Double> topActors, String sortType, int N) {
        Map<String, Double> sortedActors = new LinkedHashMap<String, Double>();
        if (sortType.equals("asc")) {
            topActors.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEachOrdered(x -> sortedActors.put(x.getKey(), x.getValue()));
        }
        ArrayList<Double> ratings = new ArrayList<Double>();
        ArrayList<String> actors = new ArrayList<String>();
        for (Map.Entry<String,Double> entry : sortedActors.entrySet()) {
            ratings.add(entry.getValue());
            actors.add(entry.getKey());
        }
        for (int i = 0; i < ratings.size();++i) {
            for (int j = i + 1; j < ratings.size(); ++j) {
                if (Double.compare(ratings.get(i),ratings.get(j)) == 0) {
                    if (actors.get(i).compareTo(actors.get(j)) > 0) {
                        Collections.swap(actors, i, j);
                    }
                }
            }
        }
        ArrayList<String> topN = new ArrayList<String>();
        for (int i = 0; i < N; ++i) {
            if (i >= actors.size())
                break;
            topN.add(actors.get(i));
        }
        outText = "Query result: " + topN;
        return outText;
    }
}
