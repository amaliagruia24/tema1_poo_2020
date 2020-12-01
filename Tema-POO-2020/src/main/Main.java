package main;

import checker.Checkstyle;
import checker.Checker;
import common.Constants;
import fileio.Input;
import fileio.InputLoader;
import fileio.Writer;
import org.json.simple.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * Call the main checker and the coding style checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        Checker checker = new Checker();
        checker.deleteFiles(outputDirectory.listFiles());

        for (File file : Objects.requireNonNull(directory.listFiles())) {

            String filepath = Constants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        checker.iterateFiles(Constants.RESULT_PATH, Constants.REF_PATH, Constants.TESTS_PATH);
        Checkstyle test = new Checkstyle();
        test.testCheckstyle();
    }
    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();

        Writer fileWriter = new Writer(filePath2);
        JSONArray arrayResult = new JSONArray();

        //TODO add here the entry point to your implementation

        int currentId = 1;
        CommandView commandView = new CommandView();
        CommandFavorite commandFavorite = new CommandFavorite();
        CommandRating commandRating = new CommandRating();
        CheckSeen checkSeen = new CheckSeen();
        RecommendationStandard recommendationStandard = new RecommendationStandard();
        RecommendationBestUnseen recommendationBestUnseen = new RecommendationBestUnseen();
        MapVideos myMap = new MapVideos();
        QueryAwardsActors queryAwardsActors = new QueryAwardsActors();
        QueryAverageActors queryAverageActors = new QueryAverageActors();
        QueryFavoriteMovie queryFavoriteMovie = new QueryFavoriteMovie();
        QueryFilterDescription queryFilterDescription = new QueryFilterDescription();
        QueryFavoriteShow queryFavoriteShow = new QueryFavoriteShow();
        QueryLongestMovie queryLongestMovie = new QueryLongestMovie();
        QueryLongestSerial queryLongestSerial = new QueryLongestSerial();
        QueryMostViewed queryMostViewed = new QueryMostViewed();
        QueryMostViewedSerial queryMostViewedSerial = new QueryMostViewedSerial();
        QueryUsers queryUsers = new QueryUsers();
        RecommendationSearch recommendationSearch = new RecommendationSearch();
        RecommendationFavorite recommendationFavorite = new RecommendationFavorite();
        QueryRatingMovie queryRatingMovie = new QueryRatingMovie();

        for (int i = 0; i < input.getCommands().size(); ++i) {
            String currentAction = input.getCommands().get(i).getActionType();
            if (currentAction.equals("command")) {
                String currentCommand = input.getCommands().get(i).getType();
                if (currentCommand.equals("view")) {
                    arrayResult.add(fileWriter.writeFile(currentId,
                            null, commandView.commandView(input,
                            input.getCommands().get(i).getTitle(),
                                    input.getCommands().get(i).getUsername())));
                    currentId++;
                }
                if (currentCommand.equals("favorite")) {
                    arrayResult.add(fileWriter.writeFile(currentId, null,
                            commandFavorite.commandFavorite(input,
                                    input.getCommands().get(i).getUsername(),
                            input.getCommands().get(i).getTitle())));
                    currentId++;
                }
                if (currentCommand.equals("rating")) {
                    int ok = 1;
                    String outText;
                    if (i > 0) {
                        for (int j = i - 1; j >= 0; --j) {
                            if (input.getCommands().get(j).getTitle() != null
                                    && input.getCommands().get(j).getTitle().equals(
                                            input.getCommands().get(i).getTitle())
                                    && input.getCommands().get(j).getUsername().equals(
                                            input.getCommands().get(i).getUsername())
                            && input.getCommands().get(j).getSeasonNumber()
                                    == input.getCommands().get(i).getSeasonNumber()
                            && checkSeen.check(input, input.getCommands().get(j).getTitle(),
                                    input.getCommands().get(j).getUsername()) == 1) {
                                ok = 0;
                                outText = "error -> " + input.getCommands().get(i).getTitle()
                                        + " has been already rated";
                                arrayResult.add(fileWriter.writeFile(currentId, null, outText));
                                currentId++;
                                break;
                            }
                        }
                    }
                    if (ok == 1) {
                        arrayResult.add(fileWriter.writeFile(currentId, null,
                                commandRating.commandRating(input, input.getCommands().get(i).getTitle(),
                                input.getCommands().get(i).getUsername(), input.getCommands().get(i).getGrade())));
                        currentId++;
                    }
                }
            }
            if (currentAction.equals("recommendation")) {
                String currentCommand = input.getCommands().get(i).getType();
                if (currentCommand.equals("standard")) {
                    arrayResult.add(fileWriter.writeFile(currentId, null,
                            recommendationStandard.recommendationStandard(input, input.getCommands().get(i).getUsername())));
                    currentId++;
                }
                if (currentCommand.equals("best_unseen")) {
                    CreateMap orderedMovies = new CreateMap();
                    arrayResult.add(fileWriter.writeFile(currentId, null,
                            recommendationBestUnseen.bestUnseen(orderedMovies.create(input),
                            input, input.getCommands().get(i).getUsername())));
                    currentId++;
                }
                if (currentCommand.equals("search")) {
                    arrayResult.add(fileWriter.writeFile(currentId, null, recommendationSearch.getUnseenVideos(
                            input, i)));
                    currentId++;
                }
                if(currentCommand.equals("favorite")) {
                    arrayResult.add(fileWriter.writeFile(currentId, null, recommendationFavorite.getRecommendationFav(input,
                            i)));
                    currentId++;
                }
                if(currentCommand.equals("popular")) {
                    arrayResult.add(fileWriter.writeFile(currentId, null, recommendationSearch.getPopularResult(input,
                            input.getCommands().get(i).getUsername())));
                    currentId++;
                }
            }
            if (currentAction.equals("query")) {
                String currentCommand = input.getCommands().get(i).getObjectType();
                String currentCriteria = input.getCommands().get(i).getCriteria();
                if (currentCommand.equals("actors") && currentCriteria.equals("average")) {
                    Map<String,Double> ratedVideos = myMap.createTopVideos(input);
                    Map<String, Double> topActors = myMap.createTopActors(input,ratedVideos);
                    arrayResult.add(fileWriter.writeFile(currentId, null, queryAverageActors.createNTop(topActors,
                            input.getCommands().get(i).getSortType(),
                            input.getCommands().get(i).getNumber())));
                    currentId++;
                }
                if (currentCommand.equals("actors") && currentCriteria.equals("awards")) {
                    arrayResult.add(fileWriter.writeFile(currentId, null, queryAwardsActors.topActorsAwards(input,
                            input.getCommands().get(i).getSortType(), i)));
                    currentId++;
                }
                if (currentCommand.equals("actors") && currentCriteria.equals("filter_description")) {
                    arrayResult.add(fileWriter.writeFile(currentId, null, queryFilterDescription.filterDescription(
                            input, i, input.getCommands().get(i).getSortType())));
                    currentId++;
                }
                if (currentCommand.equals("movies") && currentCriteria.equals("favorite")) {
                    arrayResult.add(fileWriter.writeFile(currentId, null, queryFavoriteMovie.listFavoriteMovies(
                            input, input.getCommands().get(i).getSortType(), i, input.getCommands().get(i).getNumber())));
                    currentId++;
                }
                if (currentCommand.equals("shows") && currentCriteria.equals("favorite")) {
                    arrayResult.add(fileWriter.writeFile(currentId, null, queryFavoriteShow.listFavoriteMovies(
                            input, input.getCommands().get(i).getSortType(), i, input.getCommands().get(i).getNumber())));
                    currentId++;
                }
                if (currentCommand.equals("movies") && currentCriteria.equals("longest")) {
                    arrayResult.add(fileWriter.writeFile(currentId, null, queryLongestMovie.longestMovies(input,
                            input.getCommands().get(i).getSortType(), i, input.getCommands().get(i).getNumber())));
                    currentId++;
                }
                if (currentCommand.equals("shows") && currentCriteria.equals("longest")) {
                    arrayResult.add(fileWriter.writeFile(currentId, null, queryLongestSerial.longestSerials(input,
                            input.getCommands().get(i).getSortType(), i, input.getCommands().get(i).getNumber())));
                    currentId++;
                }
                if (currentCommand.equals("movies") && currentCriteria.equals("most_viewed")) {
                    arrayResult.add(fileWriter.writeFile(currentId, null, queryMostViewed.getMostViewedMovies(input,
                            input.getCommands().get(i).getSortType(), i, input.getCommands().get(i).getNumber())));
                    currentId++;
                }
                if (currentCommand.equals("shows") && currentCriteria.equals("most_viewed")) {
                    arrayResult.add(fileWriter.writeFile(currentId, null, queryMostViewedSerial.getMostViewedSerials(input,
                            input.getCommands().get(i).getSortType(), i, input.getCommands().get(i).getNumber())));
                    currentId++;
                }
                if (currentCommand.equals("users") && currentCriteria.equals("num_ratings")) {
                    arrayResult.add(fileWriter.writeFile(currentId, null, queryUsers.getActiveUsers(input,
                            input.getCommands().get(i).getSortType(), input.getCommands().get(i).getNumber())));
                    currentId++;
                }
                if(currentCommand.equals("movies") && currentCriteria.equals("ratings")) {
                    arrayResult.add(fileWriter.writeFile(currentId, null, queryRatingMovie.getQueryRating(input,
                            i)));
                    currentId++;
                }
            }
        }
        fileWriter.closeJSON(arrayResult);
    }

}

