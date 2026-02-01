package fr.fges.repositories;

import fr.fges.models.BoardGame;
import fr.fges.services.MenuLogic;

import java.util.List;

import static fr.fges.services.MenuLogic.isNotADuplicate;

public class GameCollectionDAORAM implements GameCollectionDAO {
    private List<BoardGame> games;

    @Override
    public boolean save(BoardGame newGame) {
        if (!isNotADuplicate(newGame.title(), this)) {
            return false;
        }
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
}
