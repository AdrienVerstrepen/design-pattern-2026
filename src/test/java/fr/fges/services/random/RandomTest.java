package fr.fges.services.random;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDAO;
import fr.fges.services.Random.FirstNElementsStrategy;
import fr.fges.services.Random.RandomStrategy;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class RandomTest {
    protected abstract RandomStrategy getStrategy();

    @Test
    void shouldReturnEmptyList() {
        int N = 0;
        GameCollectionDAO myDao = mock(GameCollectionDAO.class);
        when(myDao.findAll()).thenReturn(List.of(
                new BoardGame("Tutel1", 2, 2, "fantasy"),
                new BoardGame("Tutel2", 2, 2, "fantasy"),
                new BoardGame("Tutel3", 2, 2, "fantasy")
        ));

        RandomStrategy myStrategy = getStrategy();
        List<BoardGame> myResult = myStrategy.getNRandomGame(N, myDao);

        assertEquals(N, myResult.size());
    }

    @Test
    void shouldNotReturnMoreThanAvailable() {
        int N = 10;
        GameCollectionDAO myDao = mock(GameCollectionDAO.class);
        when(myDao.findAll()).thenReturn(List.of(
                new BoardGame("Tutel1", 2, 2, "fantasy"),
                new BoardGame("Tutel2", 2, 2, "fantasy"),
                new BoardGame("Tutel3", 2, 2, "fantasy")
        ));

        RandomStrategy myStrategy = getStrategy();
        List<BoardGame> myResult = myStrategy.getNRandomGame(N, myDao);

        assertEquals(3, myResult.size());
    }

    @Test
    void shouldNotCrashIfDaoReturnsEmptyList() {
        int N = 5;
        GameCollectionDAO myDao = mock(GameCollectionDAO.class);
        when(myDao.findAll()).thenReturn(List.of());

        RandomStrategy myStrategy = getStrategy();
        List<BoardGame> myResult = myStrategy.getNRandomGame(N, myDao);

        assertTrue(myResult.isEmpty());
    }
}