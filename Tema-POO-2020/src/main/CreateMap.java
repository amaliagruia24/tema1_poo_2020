package main;

import fileio.Input;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
    Clasa care creeaza un map cu filmele ordonate descrecator dupa rating
    Parcurg actions si vad daca un utilizator are voie sa dea rating(daca l-a vazut
    sau nu)
*/
final class CreateMap {
    private CheckSeen check = new CheckSeen();
    private Map<String, Double> map = new LinkedHashMap<String, Double>();

    public Map<String, Double> create(final Input input) {
        for (int k = 0; k < input.getMovies().size(); ++k) {
            for (int j = 0; j < input.getCommands().size(); ++j) {
                if (input.getCommands().get(j).getActionType().equals("command")
                        && input.getCommands().get(j).getTitle().equals(
                                input.getMovies().get(k).getTitle())
                        && check.check(input, input.getCommands().get(j).getTitle(),
                                input.getCommands().get(j).getUsername()) == 1) {
                    map.put(input.getCommands().get(j).getTitle(),
                            input.getCommands().get(j).getGrade());
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

    public CheckSeen getCheckkkk() {
        return check;
    }

    public void setCheckkkk(final CheckSeen checkkkk) {
        this.check = checkkkk;
    }

    public Map<String, Double> getMap() {
        return map;
    }

    public void setMap(final Map<String, Double> map) {
        this.map = map;
    }
}
