package fr.fges.services.entriesServices;
import fr.fges.data.commands.RemoveGameCommand;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.data.repositories.history.HistoryDao;
import fr.fges.services.results.Failure;
import fr.fges.services.results.Result;
import fr.fges.services.results.Success;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RemoveGameServiceTest {
    private GameCollectionDao dao;
    private HistoryDao history;
    private RemoveGameService service;

    @BeforeEach
    void setUp() {
        dao = mock(GameCollectionDao.class);
        history = mock(HistoryDao.class);
        service = new RemoveGameService(dao, history);
    }

    @Test
    void shouldReturnFailureWhenGameDoesNotExist() {
        when(dao.findByTitle("Catan")).thenReturn(Optional.empty());
        Result<Void, String> result = service.removeGame("Catan");
        assertInstanceOf(Failure.class, result);
        assertEquals("No board game found with that title.", result.error());
        verify(dao).findByTitle("Catan");
        verify(dao, never()).delete(anyString());
        verify(history, never()).saveModification(any());
    }

    @Test
    void shouldReturnFailureWhenDeleteFails() {
        BoardGame game = new BoardGame("Catan", 3, 4, "Strategy");
        when(dao.findByTitle("Catan")).thenReturn(Optional.of(game));
        when(dao.delete("Catan")).thenReturn(false);
        Result<Void, String> result = service.removeGame("Catan");
        assertInstanceOf(Failure.class, result);
        assertEquals("An error occurred while trying to remove the game.", result.error());
        verify(dao).findByTitle("Catan");
        verify(dao).delete("Catan");
        verify(history, never()).saveModification(any());
    }

    @Test
    void shouldReturnSuccessWhenGameIsDeleted() {
        BoardGame game = new BoardGame("Catan", 3, 4, "Strategy");
        when(dao.findByTitle("Catan")).thenReturn(Optional.of(game));
        when(dao.delete("Catan")).thenReturn(true);
        Result<Void, String> result = service.removeGame("Catan");
        assertInstanceOf(Success.class, result);
        verify(dao).findByTitle("Catan");
        verify(dao).delete("Catan");
        verify(history).saveModification(any(RemoveGameCommand.class));
    }
}