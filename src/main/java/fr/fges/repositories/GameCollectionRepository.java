package fr.fges.repositories;
import fr.fges.formatters.GameCollectionFormatter;
import fr.fges.models.BoardGame;
import fr.fges.services.GameCollectionSaver;
import java.util.ArrayList;
import java.util.List;

public class GameCollectionRepository {
    private static final List<BoardGame> games = new ArrayList<>();

    // These methods help to access / edit the data structure holding our games
    public static List<BoardGame> getGames() {
        System.out.println(games);
        return games;
    }

    public static Integer numberGames() {
        return games.size();
    }

    public static boolean removeGame(String title) {
        var games = GameCollectionRepository.getGames();
        for (BoardGame game : games) {
            if (game.title().equals(title)) {
                games.remove(game);
                return true;
            }
        }
        return false;
    }

    public static void listAllGames() {
        GameCollectionFormatter.viewAllGames();
    }

    public static void addGame(String title, Integer minPlayers, Integer maxPlayers, String category){
        BoardGame game = new BoardGame(title, minPlayers, maxPlayers, category);
        games.add(game);
        GameCollectionSaver.saveToFile(GameCollectionRepository.getGames());
    }

    public static void printGames(List<BoardGame> games) {
        for (BoardGame game : games) {
            System.out.println(game);
        }
    }
}