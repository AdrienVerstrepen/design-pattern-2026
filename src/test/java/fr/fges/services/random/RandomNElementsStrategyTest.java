package fr.fges.services.random;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDAO;
import fr.fges.services.Random.RandomStrategy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RandomNElementsStrategyTest extends RandomTest {
    @Override
    protected RandomStrategy getStrategy() {
        return new fr.fges.services.Random.RandomNElementsStrategy();
    }

    @Test
    void shouldReturnNElementsWhenListIsLargeEnough() {
        int N = 2;
        GameCollectionDAO myDao = mock(GameCollectionDAO.class);
        BoardGame firstGame = new BoardGame("Tutel1", 2, 2, "fantasy");
        BoardGame secondGame = new BoardGame("Tutel2", 2, 2, "fantasy");
        BoardGame thirdGame = new BoardGame("Tutel3", 2, 2, "fantasy");
        BoardGame fourthGame = new BoardGame("Tutel4", 2, 2, "fantasy");
        List<BoardGame> myGames = List.of(firstGame, secondGame, thirdGame, fourthGame);

        when(myDao.findAll()).thenReturn(myGames);

        RandomStrategy myStrategy = getStrategy();
        List<BoardGame> myResult = myStrategy.getNRandomGame(N, myDao);

        assertEquals(N, myResult.size());
        assertTrue(myGames.containsAll(myResult));
    }

    @Test
    void shouldReturnNAvailableElementsWhenNIsGreaterThanListSize() {
        int N = 10;
        GameCollectionDAO myDao = mock(GameCollectionDAO.class);
        BoardGame firstGame = new BoardGame("Tutel1", 2, 2, "fantasy");
        BoardGame secondGame = new BoardGame("Tutel2", 2, 2, "fantasy");
        BoardGame thirdGame = new BoardGame("Tutel3", 2, 2, "fantasy");
        List<BoardGame> myGames = List.of(firstGame, secondGame, thirdGame);
        when(myDao.findAll()).thenReturn(myGames);

        RandomStrategy myStrategy = getStrategy();
        List<BoardGame> myResult = myStrategy.getNRandomGame(N, myDao);

        assertEquals(3, myResult.size());
        assertTrue(myGames.containsAll(myResult));
    }
}