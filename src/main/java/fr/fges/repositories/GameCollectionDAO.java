package fr.fges.repositories;
import fr.fges.models.BoardGame;

import java.util.List;

public interface GameCollectionDAO {
    void save(BoardGame game);

    List<BoardGame> findAll();

    void delete(BoardGame game);
}