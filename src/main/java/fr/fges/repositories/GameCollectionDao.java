package fr.fges.repositories;
import fr.fges.models.BoardGame;
import java.util.List;

public interface GameCollectionDao {
    boolean save(BoardGame game);

    List<BoardGame> findAll();

    boolean delete(String title);

    BoardGame findByNumberOfPlayers(int NumberOfPlayers);
}