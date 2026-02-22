package fr.fges.services.entriesServices;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.services.results.Failure;
import fr.fges.services.results.Result;
import fr.fges.services.results.Success;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SummaryServiceTest {
    private GameCollectionDao dao;
    private SummaryService service;

    @BeforeEach
    void setUp() {
        dao = mock(GameCollectionDao.class);
        service = new SummaryService(dao);
    }

    @Test
    void shouldReturnFailureWhenNoGamesExist() {
        when(dao.findAll()).thenReturn(List.of());
        Result<List<BoardGame>, String> result = service.findAllGames();
        assertInstanceOf(Failure.class, result);
        assertEquals("No games found in the collection.", result.error());
        verify(dao).findAll();
    }

    @Test
    void shouldReturnSuccessWhenGamesExist() {
        List<BoardGame> games = List.of(
                new BoardGame("Catan", 3, 4, "Strategy")
        );
        when(dao.findAll()).thenReturn(games);
        Result<List<BoardGame>, String> result = service.findAllGames();
        assertInstanceOf(Success.class, result);
        assertEquals(games, result.value());
    }

    @Test
    void shouldReturnFailureWhenNoGamesForSummary() {
        when(dao.findAll()).thenReturn(List.of());
        Result<List<BoardGame>, String> result = service.makeSummary();
        assertInstanceOf(Failure.class, result);
        assertEquals("No games found in the collection.", result.error());
    }

    @Test
    void shouldReturnAtMostThreeGamesInSummary() {
        List<BoardGame> games = List.of(
                new BoardGame("Catan", 3, 4, "Strategy"),
                new BoardGame("7 Wonders", 3, 7, "Strategy"),
                new BoardGame("Carcassonne", 2, 5, "Strategy"),
                new BoardGame("Azul", 2, 4, "Family")
        );
        when(dao.findAll()).thenReturn(games);
        Result<List<BoardGame>, String> result = service.makeSummary();
        assertInstanceOf(Success.class, result);
        List<BoardGame> summary = result.value();
        assertTrue(summary.size() <= 3);
        assertFalse(summary.isEmpty());
    }

    @Test
    void shouldReturnAllGamesIfLessThanThree() {
        List<BoardGame> games = List.of(
                new BoardGame("Catan", 3, 4, "Strategy"),
                new BoardGame("7 Wonders", 3, 7, "Strategy")
        );
        when(dao.findAll()).thenReturn(games);
        Result<List<BoardGame>, String> result = service.makeSummary();
        assertInstanceOf(Success.class, result);
        List<BoardGame> summary = result.value();
        assertEquals(2, summary.size());
    }
}