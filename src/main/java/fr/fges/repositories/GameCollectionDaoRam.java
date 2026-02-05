package fr.fges.repositories;
import fr.fges.models.BoardGame;

import java.util.ArrayList;
import java.util.List;

public class GameCollectionDaoRam implements GameCollectionDao {
    private final List<BoardGame> games = new ArrayList<>();

    @Override
    public boolean save(BoardGame newGame) {
        return games.add(newGame);
    }

    @Override
    public List<BoardGame> findAll() {
        return games;
    }

    @Override
    public boolean delete(String title) {
        return games.removeIf(game -> game.title().equals(title));
    }

    @Override
    public BoardGame findByNumberOfPlayers(int NumberOfPlayers) {
        return findAll().stream().filter(game -> game.minPlayers() <= NumberOfPlayers && game.maxPlayers() >= NumberOfPlayers).findFirst().orElse(null);
    }
}