package fr.fges.repositories;
import fr.fges.models.BoardGame;
import java.util.List;
import java.util.Optional;

public interface GameCollectionDao {
    boolean save(BoardGame game);

    List<BoardGame> findAll();

    boolean delete(String title);

    List<BoardGame> findByNumberOfPlayers(int NumberOfPlayers);

    Optional<BoardGame> findByTitle(String title);
}