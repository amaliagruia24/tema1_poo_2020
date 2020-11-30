package main;

import fileio.Input;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

// clasa care creeaza mapa cu filmele ordonate descrecator dupa rating
public class CreateMap {
    CheckSeen checkkkk = new CheckSeen();
    Map<String,Double> map = new LinkedHashMap<String, Double>();

    public Map<String,Double> create(Input input) {
        for (int k = 0; k < input.getMovies().size();++k) {
            for (int j = 0; j < input.getCommands().size(); ++j) {
                if (input.getCommands().get(j).getActionType().equals("command") &&
                        input.getCommands().get(j).getTitle().equals(input.getMovies().get(k).getTitle()) &&
                        checkkkk.check(input,input.getCommands().get(j).getTitle(), input.getCommands().get(j).getUsername()) == 1) {
                    map.put(input.getCommands().get(j).getTitle(), input.getCommands().get(j).getGrade());
                }
            }
        }
        LinkedHashMap<String, Double> reverseSortedMap = new LinkedHashMap<>();
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
        return reverseSortedMap;
    }
}
