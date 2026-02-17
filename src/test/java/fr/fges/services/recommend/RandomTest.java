package fr.fges.services.recommend;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.Games.GameCollectionDao;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class RandomTest {
    protected abstract RecommendationStrategy getStrategy();

    @Test
    void shouldReturnEmptyList() {
        int N = 0;
        GameCollectionDao myDao = mock(GameCollectionDao.class);
        when(myDao.findAll()).thenReturn(List.of(
                new BoardGame("Tutel1", 2, 2, "fantasy"),
                new BoardGame("Tutel2", 2, 2, "fantasy"),
                new BoardGame("Tutel3", 2, 2, "fantasy")
        ));

        RecommendationStrategy myStrategy = getStrategy();
        List<BoardGame> myResult = myStrategy.getNRandomGame(N, myDao.findAll());

        assertEquals(N, myResult.size());
    }

    @Test
    void shouldNotReturnMoreThanAvailable() {
        int N = 10;
        GameCollectionDao myDao = mock(GameCollectionDao.class);
        when(myDao.findAll()).thenReturn(List.of(
                new BoardGame("Tutel1", 2, 2, "fantasy"),
                new BoardGame("Tutel2", 2, 2, "fantasy"),
                new BoardGame("Tutel3", 2, 2, "fantasy")
        ));

        RecommendationStrategy myStrategy = getStrategy();
        List<BoardGame> myResult = myStrategy.getNRandomGame(N, myDao.findAll());

        assertEquals(3, myResult.size());
    }

    @Test
    void shouldNotCrashIfDaoReturnsEmptyList() {
        int N = 5;
        GameCollectionDao myDao = mock(GameCollectionDao.class);
        when(myDao.findAll()).thenReturn(List.of());

        RecommendationStrategy myStrategy = getStrategy();
        List<BoardGame> myResult = myStrategy.getNRandomGame(N, myDao.findAll());

        assertTrue(myResult.isEmpty());
    }
}