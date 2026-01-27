package fr.fges.repositories;
import fr.fges.models.BoardGame;
import java.util.ArrayList;
import java.util.List;

public class GameCollectionRepository {
    private static final List<BoardGame> games = new ArrayList<>();

    // These methods help to access / edit the data structure holding our games
    public static List<BoardGame> getGames() {
        System.out.println(games);
        return games;
    }

    public static void addGame(BoardGame game) {
        games.add(game);
    }

    public static void removeGame(BoardGame game) {
        games.remove(game);
    }

    public static Integer numberGames() {
        return games.size();
    }
}