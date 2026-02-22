package fr.fges.services.entriesServices;
import fr.fges.data.commands.AddGameCommand;
import fr.fges.data.commands.Command;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.data.repositories.history.HistoryDao;
import fr.fges.services.verifications.BoardGameVerificator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import java.util.List;
import static fr.fges.services.verifications.BoardGameVerificator.isEmptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UndoLastActionServiceTest {
    private HistoryDao history;
    private UndoLastActionService service;

    @BeforeEach
    void setUp() {
        GameCollectionDao dao = mock(GameCollectionDao.class);
        history = mock(HistoryDao.class);
        service = new UndoLastActionService(dao, history);
    }

    @Test
    void shouldUndoAddGameCommandWithoutMockingFinalClass() {
        BoardGame game = new BoardGame("Catan", 3, 4, "Strategy");
        AddGameCommand command = new AddGameCommand(game);
        when(history.findAll()).thenReturn(List.of(command));
        when(history.removeLast()).thenReturn(command);
        String result = service.undo();
        assertEquals("Undone : Removed Catan from collection", result);
    }

    @Test
    void shouldReturnNothingToCancelWhenHistoryIsEmpty() {
        List<Command> emptyList = List.of();
        when(history.findAll()).thenReturn(emptyList);
        try (MockedStatic<BoardGameVerificator> mockedStatic = mockStatic(BoardGameVerificator.class)) {
            mockedStatic.when(() -> isEmptyList(emptyList)).thenReturn(true);
            String result = service.undo();
            assertEquals("nothing to cancel", result);
            verify(history).findAll();
            verify(history, never()).removeLast();
        }
    }
}