package fr.fges.services.entriesServices;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;

import java.util.List;

public class ListAllGamesService {
    private final GameCollectionDao dao;

    public ListAllGamesService(GameCollectionDao dao) {
        this.dao = dao;
    }

    public List<BoardGame> findAllGames() {
        return dao.findAll();
    }
}
