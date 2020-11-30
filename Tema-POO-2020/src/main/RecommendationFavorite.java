package main;

import fileio.Input;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class RecommendationFavorite {
    String outText;

    public String getRecommendationFav(Input input, int current) {

        ArrayList<String> unseenVideos = new ArrayList<>();
        for(int i = 0; i < input.getMovies().size(); ++i) {
            for(int j = 0; j < input.getUsers().size(); ++j) {
                if(input.getUsers().get(j).getUsername().equals(input.getCommands().get(current).getUsername())) {
                    if(!input.getUsers().get(j).getSubscriptionType().equals("PREMIUM")) {
                        outText = "FavoriteRecommendation cannot be applied!";
                        return outText;
                    } else
                    if(!input.getUsers().get(j).getHistory().containsKey(input.getMovies().get(i).getTitle())) {
                        unseenVideos.add(input.getMovies().get(i).getTitle());
                    }
                }
            }
        }
        for(int i = 0; i < input.getSerials().size(); ++i) {
            for(int j = 0; j < input.getUsers().size(); ++j) {
                if(input.getUsers().get(j).getUsername().equals(input.getCommands().get(current).getUsername())) {
                    if(!input.getUsers().get(j).getHistory().containsKey(input.getSerials().get(i).getTitle())) {
                        unseenVideos.add(input.getSerials().get(i).getTitle());
                    }
                }
            }
        }
        if(unseenVideos.isEmpty()) {
            outText = "FavoriteRecommendation cannot be applied!";
            return outText;
        }
        Map<String, Integer> favsOccurences = new LinkedHashMap<>();
        Map<String, Integer> sortedfavs = new LinkedHashMap<>();
        for (int i = 0; i < unseenVideos.size(); ++i) {
            int noApp = 0;
            for (int j = 0; j < input.getUsers().size(); ++j) {
                if (input.getUsers().get(j).getFavoriteMovies().contains(unseenVideos.get(i))) {
                    noApp++;
                }
            }
            if(noApp > 0) {
                favsOccurences.put(unseenVideos.get(i), noApp);
            }
        }
        favsOccurences.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedfavs.put(x.getKey(), x.getValue()));

        String key = " ";
        for (Map.Entry<String, Integer> entry : sortedfavs.entrySet()) {
            key = entry.getKey();
            break;
        }
        outText = "FavoriteRecommendation result: " + key;
        return outText;
    }
}
