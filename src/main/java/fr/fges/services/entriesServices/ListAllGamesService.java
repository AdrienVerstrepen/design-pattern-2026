package fr.fges.services.entriesServices;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.services.exceptions.EmptyCollectionException;
import fr.fges.services.results.Result;
import fr.fges.services.results.Success;
import fr.fges.services.results.Failure;
import java.util.List;

public class ListAllGamesService {
    private final GameCollectionDao gamesDao;

    public ListAllGamesService(GameCollectionDao gamesDao) {
        this.gamesDao = gamesDao;
    }

    public Result<List<BoardGame>, Exception> findAllGames() {
        List<BoardGame> games = gamesDao.findAll();
        if (games == null || games.isEmpty()) {
            return new Failure<>(new EmptyCollectionException());
        }
        return new Success<>(games);
    }
}