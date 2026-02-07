package fr.fges.repositories;
import fr.fges.models.BoardGame;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<BoardGame> findByNumberOfPlayers(int numberOfPlayers) {
        return findAll().stream().filter(game -> game.minPlayers() <= numberOfPlayers && game.maxPlayers() >= numberOfPlayers).toList();
    }

    @Override
    public Optional<BoardGame> findByTitle(String title) {
        return this.findAll().stream().filter(game -> game.title().equalsIgnoreCase(title)).findFirst();
    }
}