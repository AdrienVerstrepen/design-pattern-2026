package fr.fges.UI.menu.entriesUI;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.BoardGame;
import fr.fges.services.entriesServices.AddGameService;
import fr.fges.services.results.Failure;
import fr.fges.services.results.Success;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class AddGameTest {
    @Test
    void shouldTellUserGameIsADuplicate() {
        MenuInterface formatter = mock(MenuInterface.class);
        AddGameService service = mock(AddGameService.class);
        AddGameEntry entry = new AddGameEntry("Add Game", service);

        when(formatter.getGameTitle()).thenReturn("J1");
        when(formatter.getNumberFromUser(anyString())).thenReturn(2);
        when(formatter.getGameCategory()).thenReturn("Family");

        when(service.addGame(any(BoardGame.class))).thenReturn(new Failure<>("A game with the same title already exists !"));
        entry.handle(formatter);
        verify(formatter).displayMessage("A game with the same title already exists !");
    }

    @Test
    void shouldConfirmGameIsAdded() {
        MenuInterface formatter = mock(MenuInterface.class);
        AddGameService service = mock(AddGameService.class);
        AddGameEntry entry = new AddGameEntry("Add Game", service);

        when(formatter.getGameTitle()).thenReturn("J2");
        when(formatter.getNumberFromUser(anyString())).thenReturn(2);
        when(formatter.getGameCategory()).thenReturn("Family");

        when(service.addGame(any(BoardGame.class))).thenReturn(new Success<>(null));
        entry.handle(formatter);
        verify(formatter).displayMessage("Board game added successfully.");
    }

    @Test
    void shouldTellUserGameHasNotBeenSaved() {
        MenuInterface formatter = mock(MenuInterface.class);
        AddGameService service = mock(AddGameService.class);
        AddGameEntry entry = new AddGameEntry("Add Game", service);

        when(formatter.getGameTitle()).thenReturn("J3");
        when(formatter.getNumberFromUser(anyString())).thenReturn(2);
        when(formatter.getGameCategory()).thenReturn("Family");

        when(service.addGame(any(BoardGame.class))).thenReturn(new Failure<>("An error occurred, please try again."));
        entry.handle(formatter);
        verify(formatter).displayMessage("An error occurred, please try again.");
    }
}