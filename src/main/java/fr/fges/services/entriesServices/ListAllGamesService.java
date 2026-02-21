package fr.fges.services.entriesServices;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.services.results.Result;
import fr.fges.services.results.Success;
import fr.fges.services.results.Failure;
import java.util.List;

public class ListAllGamesService {
    private final GameCollectionDao dao;

    public ListAllGamesService(GameCollectionDao dao) {
        this.dao = dao;
    }

    public Result<List<BoardGame>, String> findAllGames() {
        List<BoardGame> games = dao.findAll();
        if (games == null || games.isEmpty()) {
            return new Failure<>("No games found in the collection.");
        }
        return new Success<>(games);
    }
}