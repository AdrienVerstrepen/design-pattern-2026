package fr.fges.repositories;

import fr.fges.models.BoardGame;

import java.util.ArrayList;
import java.util.List;

public class GameCollectionRepository {
    private static final List<BoardGame> games = new ArrayList<>();

    public static List<BoardGame> getGames() {
        return games;
    }
}
