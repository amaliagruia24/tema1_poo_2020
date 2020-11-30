package main;

import fileio.Input;

import java.util.LinkedHashMap;
import java.util.Map;

final class MapVideos {

    //metoda care creeaza un map cu filmul si rating ul total calculat
    public Map<String, Double> createTopVideos(final Input input) {

        Map<String, Double> map = new LinkedHashMap<String, Double>();
        Map<String, Double> map2 = new LinkedHashMap<String, Double>();
        Map<String, Double> map3 = new LinkedHashMap<String, Double>();
        CheckSeen checkSeen = new CheckSeen();
        for (int i = 0; i < input.getCommands().size(); ++i) {
            if (input.getCommands().get(i).getActionType().equals("command")
                    && input.getCommands().get(i).getType().equals("rating")
                    && checkSeen.check(input, input.getCommands().get(i).getTitle(),
                    input.getCommands().get(i).getUsername()) == 1) {
                if (input.getCommands().get(i).getSeasonNumber() == 0) {
                    double rating = input.getCommands().get(i).getGrade();
                    int number = 1;
                    double average = 0;
                    for (int j = i + 1; j < input.getCommands().size(); ++j) {
                        if (input.getCommands().get(j).getActionType().equals("command")
                                && input.getCommands().get(j).getTitle().equals(
                                        input.getCommands().get(i).getTitle())
                                && !input.getCommands().get(j).getUsername().equals(
                                        input.getCommands().get(i).getUsername())) {
                            rating += input.getCommands().get(j).getGrade();
                            number++;
                        }
                    }
                    average = rating / number;
                    if (!map.containsKey(input.getCommands().get(i).getTitle())) {
                        map.put(input.getCommands().get(i).getTitle(), average);
                    }
                } else
                if (input.getCommands().get(i).getSeasonNumber() == 1) {
                    double rating = input.getCommands().get(i).getGrade();
                    int number = 1;
                    double average = 0;
                    for (int j = i + 1; j < input.getCommands().size(); ++j) {
                        if (input.getCommands().get(j).getActionType().equals("command")
                                && input.getCommands().get(j).getType().equals("rating")
                                && input.getCommands().get(j).getTitle().equals(
                                        input.getCommands().get(i).getTitle())
                                && input.getCommands().get(j).getSeasonNumber()
                                == input.getCommands().get(i).getSeasonNumber()
                                && !input.getCommands().get(j).getUsername().equals(
                                        input.getCommands().get(i).getUsername())) {
                            rating += input.getCommands().get(j).getGrade();
                            number++;
                        }
                        average = rating / number;
                        if (!map2.containsKey(input.getCommands().get(i).getTitle())) {
                            map2.put(input.getCommands().get(i).getTitle(), average);
                        }
                    }
                } else
                if (input.getCommands().get(i).getSeasonNumber() == 2) {
                    double rating = input.getCommands().get(i).getGrade();
                    int number = 1;
                    double average = 0;
                    for (int j = i + 1; j < input.getCommands().size(); ++j) {
                        if (input.getCommands().get(j).getActionType().equals("command")
                                && input.getCommands().get(j).getType().equals("rating")
                                && input.getCommands().get(j).getTitle().equals(
                                        input.getCommands().get(i).getTitle())
                                && input.getCommands().get(j).getSeasonNumber()
                                == input.getCommands().get(i).getSeasonNumber()
                                && !input.getCommands().get(j).getUsername().equals(
                                        input.getCommands().get(i).getUsername())) {
                            rating += input.getCommands().get(j).getGrade();
                            number++;
                        }
                        average = rating / number;
                        if (!map3.containsKey(input.getCommands().get(i).getTitle())) {
                            map3.put(input.getCommands().get(i).getTitle(), average);
                        }
                    }
                }
            }
        }
        double newAverage = 0;
        int sum = 0;
        Map<String, Double> map4 = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> entry : map3.entrySet()) {
            if (map2.containsKey(entry.getKey())) {
                for (Map.Entry<String, Double> entry2 : map2.entrySet()) {
                    newAverage = (entry.getValue() + entry2.getValue()) / 3;
                    map4.put(entry.getKey(), newAverage);
                }
            } else {
                map4.put(entry.getKey(), entry.getValue() / 2);
            }

        }
        map.putAll(map4);
        return map;
    }

    // metoda care creeaza o mapa cu actorii si
    // rating urile corespunzatoare filmelor in care a jucat
    public Map<String, Double> createTopActors(final Input input,
                                               final Map<String, Double> ratedVideos) {
        Map<String, Double> topActors = new LinkedHashMap<String, Double>();
        for (int i = 0; i < input.getActors().size(); ++i) {
            double actorAverage = 0;
            double sum = 0;
            double number = 0;

            for (int j = 0; j < input.getActors().get(i).getFilmography().size(); ++j) {
                String currentVideo = input.getActors().get(i).getFilmography().get(j);
                if (ratedVideos.containsKey(currentVideo)) {
                    for (Map.Entry<String, Double> entry : ratedVideos.entrySet()) {
                        if (entry.getKey().equals(currentVideo)) {
                            sum += entry.getValue();
                            number++;
                        }
                    }
                }
            }
            if (number != 0) {
                actorAverage = sum / number;
            }
            if (actorAverage != 0) {
                topActors.put(input.getActors().get(i).getName(), actorAverage);
            }
        }
        return topActors;
    }
}
