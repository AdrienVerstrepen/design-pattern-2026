package fr.fges.services.entriesServices;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.services.results.Result;
import fr.fges.services.results.Success;
import fr.fges.services.results.Failure;
import java.util.List;

public class RecommendByPlayerCountService {
    private final GameCollectionDao dao;

    public RecommendByPlayerCountService(GameCollectionDao dao) {
        this.dao = dao;
    }

    public Result<List<BoardGame>, String> recommendByPlayerCount(int playerCount) {
        if (playerCount <= 0) {
            return new Failure<>("The number of players must be bigger than zero.");
        }
        List<BoardGame> games = dao.findByNumberOfPlayers(playerCount);
        games = dao.findAllInAlphabeticalOrder(games);
        if (games.isEmpty()) {
            return new Failure<>("There is no game for this number of players.");
        }
        return new Success<>(games);
    }
}