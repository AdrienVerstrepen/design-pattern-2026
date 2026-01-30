package fr.fges.repositories;

import fr.fges.models.BoardGame;

import java.util.List;

public class GameCollectionDAORAM implements GameCollectionDAO {
    private List<BoardGame> games;

    @Override
    public void save(BoardGame game) {
        games.add(game);
    }

    @Override
    public List<BoardGame> findAll() {
        return games;
    }

    @Override
    public void delete(BoardGame game) {
        games.remove(game);
    }
}
