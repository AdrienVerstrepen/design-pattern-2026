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

class RecommendByPlayerCountServiceTest {
    private GameCollectionDao dao;
    private RecommendByPlayerCountService service;

    @BeforeEach
    void setUp() {
        dao = mock(GameCollectionDao.class);
        service = new RecommendByPlayerCountService(dao);
    }

    @Test
    void shouldReturnFailureWhenPlayerCountIsZeroOrNegative() {
        Result<List<BoardGame>, String> result = service.recommendByPlayerCount(0);
        assertInstanceOf(Failure.class, result);
        assertEquals("The number of players must be bigger than zero.", result.error());
        verify(dao, never()).findByNumberOfPlayers(anyInt());
        verify(dao, never()).findAllInAlphabeticalOrder(any());
    }

    @Test
    void shouldReturnFailureWhenNoGameMatchesPlayerCount() {
        when(dao.findByNumberOfPlayers(4)).thenReturn(List.of());
        when(dao.findAllInAlphabeticalOrder(List.of())).thenReturn(List.of());
        Result<List<BoardGame>, String> result = service.recommendByPlayerCount(4);
        assertInstanceOf(Failure.class, result);
        assertEquals("There is no game for this number of players.", result.error());
        verify(dao).findByNumberOfPlayers(4);
        verify(dao).findAllInAlphabeticalOrder(any());
    }

    @Test
    void shouldReturnSuccessWhenGamesExist() {
        List<BoardGame> filteredGames = List.of(
                new BoardGame("Catan", 3, 4, "Strategy"),
                new BoardGame("7 Wonders", 3, 7, "Strategy")
        );
        List<BoardGame> sortedGames = List.of(
                new BoardGame("7 Wonders", 3, 7, "Strategy"),
                new BoardGame("Catan", 3, 4, "Strategy")
        );
        when(dao.findByNumberOfPlayers(4)).thenReturn(filteredGames);
        when(dao.findAllInAlphabeticalOrder(filteredGames)).thenReturn(sortedGames);
        Result<List<BoardGame>, String> result = service.recommendByPlayerCount(4);
        assertInstanceOf(Success.class, result);
        assertEquals(sortedGames, result.value());
        verify(dao).findByNumberOfPlayers(4);
        verify(dao).findAllInAlphabeticalOrder(filteredGames);
    }
}