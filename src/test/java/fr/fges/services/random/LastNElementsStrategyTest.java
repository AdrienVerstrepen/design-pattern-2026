package fr.fges.services.random;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDao;
import fr.fges.services.Random.LastNElementsStrategy;
import fr.fges.services.Random.RecommendationStrategy;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LastNElementsStrategyTest extends RandomTest {
    @Override
    protected RecommendationStrategy getStrategy() {
        return new LastNElementsStrategy();
    }

    @Test
    void shouldReturnLastNElementsWhenListIsLargeEnough() {
        int N = 3;
        GameCollectionDao myDao = mock(GameCollectionDao.class);
        when(myDao.findAll()).thenReturn(List.of(
                new BoardGame("Tutel1", 2, 2, "fantasy"),
                new BoardGame("Tutel2", 2, 2, "fantasy"),
                new BoardGame("Tutel3", 2, 2, "fantasy"),
                new BoardGame("Tutel4", 2, 2, "fantasy")
        ));

        RecommendationStrategy myStrategy = getStrategy();
        List<BoardGame> myResult = myStrategy.getNRandomGame(N, myDao);

        assertEquals(N, myResult.size());
        assertEquals("Tutel4", myResult.get(0).title());
        assertEquals("Tutel3", myResult.get(1).title());
        assertEquals("Tutel2", myResult.get(2).title());
    }

    @Test
    void shouldReturnLastNAvailableElementsWhenNIsGreaterThanListSize() {
        int N = 10;
        GameCollectionDao myDao = mock(GameCollectionDao.class);
        when(myDao.findAll()).thenReturn(List.of(
                new BoardGame("Tutel1", 2, 2, "fantasy"),
                new BoardGame("Tutel2", 2, 2, "fantasy"),
                new BoardGame("Tutel3", 2, 2, "fantasy")
        ));

        RecommendationStrategy myStrategy = getStrategy();
        List<BoardGame> myResult = myStrategy.getNRandomGame(N, myDao);

        assertEquals(3, myResult.size());
        assertEquals("Tutel3", myResult.get(0).title());
        assertEquals("Tutel2", myResult.get(1).title());
        assertEquals("Tutel1", myResult.get(2).title());
    }
}