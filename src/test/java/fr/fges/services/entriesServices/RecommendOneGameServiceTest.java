package fr.fges.services.entriesServices;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.services.recommend.RecommendationStrategy;
import fr.fges.services.results.Failure;
import fr.fges.services.results.Result;
import fr.fges.services.results.Success;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecommendOneGameServiceTest {
    private RecommendationStrategy strategy;
    private GameCollectionDao dao;
    private RecommendOneGameService service;

    @BeforeEach
    void setUp() {
        strategy = mock(RecommendationStrategy.class);
        dao = mock(GameCollectionDao.class);
        service = new RecommendOneGameService(strategy);
    }

    @Test
    void shouldReturnFailureWhenPlayerCountIsZeroOrNegative() {
        Result<BoardGame, String> result = service.recommendOneGame(0, dao);
        assertInstanceOf(Failure.class, result);
        assertEquals("Number of players must be greater than zero.", ((Failure<BoardGame, String>) result).error());
        verify(dao, never()).findByNumberOfPlayers(anyInt());
        verify(strategy, never()).getNRandomGame(anyInt(), any());
    }

    @Test
    void shouldReturnFailureWhenNoGameAvailable() {
        when(dao.findByNumberOfPlayers(4)).thenReturn(List.of());
        Result<BoardGame, String> result = service.recommendOneGame(4, dao);
        assertInstanceOf(Failure.class, result);
        assertEquals("No games available for this number of players.", ((Failure<BoardGame, String>) result).error());
        verify(dao).findByNumberOfPlayers(4);
        verify(strategy, never()).getNRandomGame(anyInt(), any());
    }

    @Test
    void shouldReturnSuccessWhenGameIsRecommended() {
        BoardGame game1 = new BoardGame("Catan", 3, 4, "Strategy");
        BoardGame game2 = new BoardGame("7 Wonders", 3, 7, "Strategy");
        List<BoardGame> possibleGames = List.of(game1, game2);
        when(dao.findByNumberOfPlayers(4)).thenReturn(possibleGames);
        when(strategy.getNRandomGame(1, possibleGames)).thenReturn(List.of(game2));
        Result<BoardGame, String> result = service.recommendOneGame(4, dao);
        assertInstanceOf(Success.class, result);
        assertEquals(game2, ((Success<BoardGame, String>) result).value());
        verify(dao).findByNumberOfPlayers(4);
        verify(strategy).getNRandomGame(1, possibleGames);
    }
}