package fr.fges.services.recommend;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.GameCollectionDao;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FirstNElementsStrategyTest extends RandomTest {
    @Override
    protected RecommendationStrategy getStrategy() {
        return new FirstNElementsStrategy();
    }

    @Test
    void shouldReturnNFirstElementsWhenListIsLargeEnough() {
        int N = 3;
        GameCollectionDao myDao = mock(GameCollectionDao.class);
        when(myDao.findAll()).thenReturn(List.of(
                new BoardGame("Tutel1", 2, 2, "fantasy"),
                new BoardGame("Tutel2", 2, 2, "fantasy"),
                new BoardGame("Tutel3", 2, 2, "fantasy"),
                new BoardGame("Tutel4", 2, 2, "fantasy")
        ));

        RecommendationStrategy myStrategy = getStrategy();
        List<BoardGame> myResult = myStrategy.getNRandomGame(N, myDao.findAll());

        assertEquals(N, myResult.size());
        assertEquals("Tutel1", myResult.get(0).title());
        assertEquals("Tutel2", myResult.get(1).title());
        assertEquals("Tutel3", myResult.get(2).title());
    }

    @Test
    void shouldReturnNFirstAvailableElementsWhenNIsGreaterThanListSize() {
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
        assertEquals("Tutel1", myResult.get(0).title());
        assertEquals("Tutel2", myResult.get(1).title());
        assertEquals("Tutel3", myResult.get(2).title());
    }
}