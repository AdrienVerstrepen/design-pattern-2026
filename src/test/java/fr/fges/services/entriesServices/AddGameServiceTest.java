package fr.fges.services.entriesServices;
import fr.fges.data.commands.AddGameCommand;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.data.repositories.history.HistoryDao;
import fr.fges.services.results.Failure;
import fr.fges.services.results.Result;
import fr.fges.services.results.Success;
import fr.fges.services.verifications.BoardGameVerificator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddGameServiceTest {
    private GameCollectionDao dao;
    private HistoryDao history;
    private AddGameService service;

    @BeforeEach
    void setUp() {
        dao = mock(GameCollectionDao.class);
        history = mock(HistoryDao.class);
        service = new AddGameService(dao, history);
    }

    @Test
    void shouldReturnFailureWhenGameIsDuplicate() {
        BoardGame game = new BoardGame("Catan", 3, 4, "Strategy");
        try (MockedStatic<BoardGameVerificator> mockedStatic = mockStatic(BoardGameVerificator.class)) {
            mockedStatic.when(() -> BoardGameVerificator.isADuplicate("Catan", dao)).thenReturn(true);
            Result<Void, String> result = service.addGame(game);
            assertInstanceOf(Failure.class, result);
            assertEquals("A game with the same title already exists !", ((Failure<Void, String>) result).error());
            verify(dao, never()).save(any());
            verify(history, never()).saveModification(any());
        }
    }

    @Test
    void shouldSaveGameAndHistoryWhenValid() {
        BoardGame game = new BoardGame("Catan", 3, 4, "Strategy");
        try (MockedStatic<BoardGameVerificator> mockedStatic = mockStatic(BoardGameVerificator.class)) {
            mockedStatic.when(() -> BoardGameVerificator.isADuplicate("Catan", dao)).thenReturn(false);
            when(dao.save(game)).thenReturn(true);
            Result<Void, String> result = service.addGame(game);
            assertInstanceOf(Success.class, result);
            verify(dao).save(game);
            verify(history).saveModification(any(AddGameCommand.class));
        }
    }

    @Test
    void shouldReturnFailureWhenSaveFails() {
        BoardGame game = new BoardGame("Catan", 3, 4, "Strategy");
        try (MockedStatic<BoardGameVerificator> mockedStatic = mockStatic(BoardGameVerificator.class)) {
            mockedStatic.when(() -> BoardGameVerificator.isADuplicate("Catan", dao)).thenReturn(false);
            when(dao.save(game)).thenReturn(false);
            Result<Void, String> result = service.addGame(game);
            assertInstanceOf(Failure.class, result);
            assertEquals("An error occurred, please try again.", ((Failure<Void, String>) result).error());
            verify(history, never()).saveModification(any());
        }
    }
}