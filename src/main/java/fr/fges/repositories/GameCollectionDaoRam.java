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
    public List<BoardGame> findByNumberOfPlayers(int numberOfPlayers) {
        return findAll().stream().filter(game -> game.minPlayers() <= numberOfPlayers && game.maxPlayers() >= numberOfPlayers).toList();
    }

    @Override
    public List<BoardGame> alphabeticalOrder(List<BoardGame> games) {
        return games.stream().sorted((jeu1, jeu2) -> jeu1.title().compareToIgnoreCase(jeu2.title())).toList();
    }
}