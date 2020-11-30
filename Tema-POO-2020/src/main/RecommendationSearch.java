package main;

import fileio.Input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class RecommendationSearch {
    String outText;

    public String getUnseenVideos(Input input, int current) {
        ArrayList<String> specifiedGenreVideos = new ArrayList<String>();
        CheckSeen checkSeen = new CheckSeen();
        for (int i = 0; i < input.getMovies().size(); ++i)
            if (input.getMovies().get(i).getGenres().contains(input.getCommands().get(current).getGenre())) {
                specifiedGenreVideos.add(input.getMovies().get(i).getTitle());
            }
        for (int i = 0; i < input.getSerials().size(); ++i)
            if (input.getSerials().get(i).getGenres().contains(input.getCommands().get(current).getGenre())) {
                specifiedGenreVideos.add(input.getSerials().get(i).getTitle());
            }
        ArrayList<String> existingGenreVideos = new ArrayList<String>();
        for (int i = 0; i < input.getUsers().size(); ++i) {
            if (input.getUsers().get(i).getUsername().equals(input.getCommands().get(current).getUsername())
                    && input.getUsers().get(i).getSubscriptionType().equals("PREMIUM"))
                for (int j = 0; j < specifiedGenreVideos.size(); ++j) {
                    if (!input.getUsers().get(i).getHistory().containsKey(specifiedGenreVideos.get(j))) {
                        existingGenreVideos.add(specifiedGenreVideos.get(j));
                    }
                }
        }
        if (existingGenreVideos.isEmpty()) {
            outText = "SearchRecommendation cannot be applied!";
            return outText;
        }
        Map<String, Double> videozz = new LinkedHashMap<>();
        for (int i = 0; i < existingGenreVideos.size(); ++i) {
            for (int j = 0; j < input.getCommands().size(); ++j) {
                if (input.getCommands().get(j).getActionType().equals("command")
                        && input.getCommands().get(j).getType().equals("rating")
                        && input.getCommands().get(j).getTitle().equals(existingGenreVideos.get(i))) {
                    if (checkSeen.check(input, existingGenreVideos.get(i), input.getCommands().get(i).getUsername()) == 1) {
                        int ok = 1;
                        if (i > 0) {
                            for (int k = i - 1; k >= 0; --k) {
                                if (input.getCommands().get(k).getTitle() != null &&
                                        input.getCommands().get(k).getTitle().equals(
                                                input.getCommands().get(j).getTitle())
                                        && input.getCommands().get(k).getUsername().equals(
                                                input.getCommands().get(j).getUsername())
                                        && input.getCommands().get(k).getSeasonNumber() ==
                                        input.getCommands().get(j).getSeasonNumber()
                                        && checkSeen.check(input, input.getCommands().get(k).getTitle(),
                                        input.getCommands().get(k).getUsername()) == 1) {
                                    ok = 0;
                                    break;
                                }
                            }
                        }
                        if (ok == 1) {
                            videozz.put(existingGenreVideos.get(i), input.getCommands().get(j).getGrade());
                        }
                    }
                }
            }
        }
        Map<String, Double> sortedvideozz = new LinkedHashMap<>();
        ArrayList<String> result = new ArrayList<String>();
        if (videozz.isEmpty()) {
            Collections.sort(existingGenreVideos);
            outText = "SearchRecommendation result: " + existingGenreVideos;
            return outText;
        } else {
            videozz.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEachOrdered(x -> sortedvideozz.put(x.getKey(), x.getValue()));

            for (Map.Entry<String, Double> entry : sortedvideozz.entrySet()) {
                result.add(entry.getKey());
            }
            outText = "SearchRecommendation result: " + result;
            return outText;
        }
    }
}
