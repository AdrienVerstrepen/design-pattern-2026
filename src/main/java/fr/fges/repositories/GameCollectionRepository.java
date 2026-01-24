package fr.fges.repositories;

import fr.fges.models.BoardGame;

import java.util.ArrayList;
import java.util.List;

public class GameCollectionRepository {
    private static final List<BoardGame> games = new ArrayList<>();

    // These methods help to access / edit the data structure holding our games
    public static List<BoardGame> getGames() {
        return games;
    }
    public static Boolean addGame(BoardGame game) {
        games.add(game);
        return true;
    }
    public static Boolean removeGame(BoardGame game) {
        games.remove(game);
        return true;
    }
}
