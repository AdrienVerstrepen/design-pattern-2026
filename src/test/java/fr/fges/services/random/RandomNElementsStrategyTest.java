package fr.fges.services.random;
import fr.fges.services.Random.RandomNElementsStrategy;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.GameCollectionDao;
import fr.fges.services.Random.RecommendationStrategy;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RandomNElementsStrategyTest extends RandomTest {
    @Override
    protected RecommendationStrategy getStrategy() {
        return new RandomNElementsStrategy();
    }

    @Test
    void shouldReturnNElementsWhenListIsLargeEnough() {
        int N = 2;
        GameCollectionDao myDao = mock(GameCollectionDao.class);
        BoardGame firstGame = new BoardGame("Tutel1", 2, 2, "fantasy");
        BoardGame secondGame = new BoardGame("Tutel2", 2, 2, "fantasy");
        BoardGame thirdGame = new BoardGame("Tutel3", 2, 2, "fantasy");
        BoardGame fourthGame = new BoardGame("Tutel4", 2, 2, "fantasy");
        List<BoardGame> myGames = List.of(firstGame, secondGame, thirdGame, fourthGame);

        when(myDao.findAll()).thenReturn(myGames);

        RecommendationStrategy myStrategy = getStrategy();
        List<BoardGame> myResult = myStrategy.getNRandomGame(N, myDao.findAll());

        assertEquals(N, myResult.size());
        assertTrue(myGames.containsAll(myResult));
    }

    @Test
    void shouldReturnNAvailableElementsWhenNIsGreaterThanListSize() {
        int N = 10;
        GameCollectionDao myDao = mock(GameCollectionDao.class);
        BoardGame firstGame = new BoardGame("Tutel1", 2, 2, "fantasy");
        BoardGame secondGame = new BoardGame("Tutel2", 2, 2, "fantasy");
        BoardGame thirdGame = new BoardGame("Tutel3", 2, 2, "fantasy");
        List<BoardGame> myGames = List.of(firstGame, secondGame, thirdGame);
        when(myDao.findAll()).thenReturn(myGames);

        RecommendationStrategy myStrategy = getStrategy();
        List<BoardGame> myResult = myStrategy.getNRandomGame(N, myDao.findAll());

        assertEquals(3, myResult.size());
        assertTrue(myGames.containsAll(myResult));
    }
}