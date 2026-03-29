package fr.fges.services.entriesServices;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.services.exceptions.InvalidPlayerCountException;
import fr.fges.services.exceptions.NoMatchingGamesException;
import fr.fges.services.recommend.RecommendationStrategy;
import fr.fges.services.results.Result;
import fr.fges.services.results.Success;
import fr.fges.services.results.Failure;
import java.util.List;

public class RecommendOneGameService {
    private final RecommendationStrategy strategy;

    public RecommendOneGameService(RecommendationStrategy strategy) {
        this.strategy = strategy;
    }

    public Result<BoardGame, Exception> recommendOneGame(int numberOfPlayers, GameCollectionDao gamesDao) {
        if (numberOfPlayers <= 0) {
            return new Failure<>(new InvalidPlayerCountException());
        }
        List<BoardGame> possibleGames = gamesDao.findByNumberOfPlayers(numberOfPlayers);
        if (possibleGames.isEmpty()) {
            return new Failure<>(new NoMatchingGamesException());
        }
        BoardGame game = strategy.getNRandomGame(1, possibleGames).getFirst();
        return new Success<>(game);
    }
}