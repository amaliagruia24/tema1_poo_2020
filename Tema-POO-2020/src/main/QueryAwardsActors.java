package main;

import fileio.Input;

import java.util.*;

public class QueryAwardsActors {
    String outText;

    public String topActorsAwards(Input input, String sortType, int actionId) {
        ArrayList<String> actors = new ArrayList<String>();
        for (int j = 0; j < input.getActors().size(); ++j) {
            int counter = 0;
            for (int k = 0; k < input.getCommands().get(actionId).getFilters().get(3).size(); ++k) {
                for (int m = 0; m < input.getActors().get(j).getAwards().size(); ++m) {
                    if (input.getActors().get(m).getAwards().containsKey(
                            input.getCommands().get(actionId).getFilters().get(3).get(k))) {
                        counter++;
                    }
                }
            }
            if (counter == input.getCommands().get(actionId).getFilters().get(3).size()) {
                actors.add(input.getActors().get(j).getName());
            }
        }
        if (actors.isEmpty()) {
            outText = "Query result: " + actors;
            return  outText;
        }
        Map<String, Integer> actorsAwards = new LinkedHashMap<String,Integer>();
        int awards = 0;
        for (int i = 0; i < actors.size();++i) {
            for (int j = 0; j < input.getActors().size(); ++j) {
                awards = 0;
                if (actors.get(i).equals(input.getActors().get(j).getName())) {
                    for (Map.Entry<actor.ActorsAwards, Integer> entry : input.getActors().get(j).getAwards().entrySet()) {
                        awards += entry.getValue();
                    }
                }
            }
            actorsAwards.put(actors.get(i),awards);
        }
        Map<String, Integer> sortedAwards = new LinkedHashMap<String,Integer>();
        if (sortType.equals("asc")) {
            actorsAwards.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEachOrdered(x -> sortedAwards.put(x.getKey(), x.getValue()));
        }
        else {
            actorsAwards.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEachOrdered(x -> sortedAwards.put(x.getKey(), x.getValue()));
        }
        //System.out.println(sortedAwards);
        ArrayList<Integer> noAwards = new ArrayList<Integer>();
        ArrayList<String> auxActors = new ArrayList<String>();
        for (Map.Entry<String,Integer> entry : sortedAwards.entrySet()) {
            noAwards.add(entry.getValue());
            auxActors.add(entry.getKey());
        }
        //System.out.println(noAwards);
        //System.out.println(actors);
        for (int i = 0; i < noAwards.size();++i) {
            for (int j = i + 1; j < noAwards.size(); ++j) {
                if (noAwards.get(i) == noAwards.get(j)) {
                    if (auxActors.get(i).compareTo(auxActors.get(j)) > 0) {
                        Collections.swap(auxActors, i, j);
                    }
                }
            }
        }
        outText = "Query result: " + auxActors;
        return outText;
    }
}
