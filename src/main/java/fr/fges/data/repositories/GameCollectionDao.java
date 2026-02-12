package fr.fges.data.repositories;
import fr.fges.data.models.BoardGame;
import java.util.List;
import java.util.Optional;

public interface GameCollectionDao {
    boolean save(BoardGame game);

    List<BoardGame> findAll();

    boolean delete(String title);

    List<BoardGame> findByNumberOfPlayers(int NumberOfPlayers);

    Optional<BoardGame> findByTitle(String title);

    List<BoardGame> findAllInAlphabeticalOrder(List<BoardGame> games);
}