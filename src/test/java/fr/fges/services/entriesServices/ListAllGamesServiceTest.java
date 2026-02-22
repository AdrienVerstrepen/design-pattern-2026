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

class ListAllGamesServiceTest {
    private GameCollectionDao dao;
    private ListAllGamesService service;

    @BeforeEach
    void setUp() {
        dao = mock(GameCollectionDao.class);
        service = new ListAllGamesService(dao);
    }

    @Test
    void shouldReturnFailureWhenDaoReturnsNull() {
        when(dao.findAll()).thenReturn(null);
        Result<List<BoardGame>, String> result = service.findAllGames();
        assertInstanceOf(Failure.class, result);
        assertEquals("No games found in the collection.", result.error());
        verify(dao).findAll();
    }

    @Test
    void shouldReturnFailureWhenListIsEmpty() {
        when(dao.findAll()).thenReturn(List.of());
        Result<List<BoardGame>, String> result = service.findAllGames();
        assertInstanceOf(Failure.class, result);
        assertEquals("No games found in the collection.", result.error());
        verify(dao).findAll();
    }

    @Test
    void shouldReturnSuccessWhenGamesExist() {
        List<BoardGame> games = List.of(
                new BoardGame("Catan", 3, 4, "Strategy"),
                new BoardGame("Uno", 2, 10, "Card Game")
        );
        when(dao.findAll()).thenReturn(games);
        Result<List<BoardGame>, String> result = service.findAllGames();
        assertInstanceOf(Success.class, result);
        assertEquals(games, result.value());
        verify(dao).findAll();
    }
}