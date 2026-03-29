package fr.fges.services.entriesServices;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.services.exceptions.EmptyCollectionException;
import fr.fges.services.exceptions.SummaryGenerationException;
import fr.fges.services.recommend.RandomNElementsStrategy;
import fr.fges.services.recommend.RecommendationStrategy;
import fr.fges.services.results.Result;
import fr.fges.services.results.Success;
import fr.fges.services.results.Failure;
import java.util.List;

public class SummaryService {
    private final GameCollectionDao gamesDao;

    public SummaryService(GameCollectionDao gamesDao) {
        this.gamesDao = gamesDao;
    }

    public Result<List<BoardGame>, Exception> findAllGames() {
        List<BoardGame> games = gamesDao.findAll();
        if (games.isEmpty()) {
            return new Failure<>(new EmptyCollectionException());
        }
        return new Success<>(games);
    }

    public Result<List<BoardGame>, Exception> makeSummary() {
        Result<List<BoardGame>, Exception> allGamesResult = findAllGames();
        if (!allGamesResult.isSuccess()) {
            return allGamesResult;
        }
        List<BoardGame> allGames = allGamesResult.value();
        RecommendationStrategy myStrategy = new RandomNElementsStrategy();
        List<BoardGame> summaryGames = myStrategy.getNRandomGame(Math.min(3, allGames.size()), allGames);
        if (summaryGames.isEmpty()) {
            return new Failure<>(new SummaryGenerationException());
        }
        return new Success<>(summaryGames);
    }
}