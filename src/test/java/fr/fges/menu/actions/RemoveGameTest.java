package fr.fges.menu.actions;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.UI.menu.actions.RemoveGameEntry;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.GameCollectionDao;
import fr.fges.data.repositories.HistoryDao;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class RemoveGameTest {
    @Test
    void shouldTellUserGameHasBeenRemoved () {
        MenuInterface formatter = mock(MenuInterface.class);
        GameCollectionDao dao = mock(GameCollectionDao.class);
        HistoryDao history = mock(HistoryDao.class);
        when(formatter.getGameTitle()).thenReturn("Catan");
        when(dao.delete("Catan")).thenReturn(true);
        when(dao.findByTitle(anyString())).thenReturn(Optional.of(new BoardGame("Catan", 1, 5, "fam")));

        RemoveGameEntry entry = new RemoveGameEntry("Remove Board Game", history);

        entry.handle(formatter, dao);

        verify(formatter).displayMessage("Board game removed successfully.");
    }

    @Test
    void shouldTellUserNoGameToRemove () {
        MenuInterface formatter = mock(MenuInterface.class);
        GameCollectionDao dao = mock(GameCollectionDao.class);
        HistoryDao history = mock(HistoryDao.class);
        when(formatter.getGameTitle()).thenReturn("Catan");
        when(dao.findByTitle(anyString())).thenReturn(Optional.empty());
        when(dao.delete("Catan")).thenReturn(false);
        RemoveGameEntry entry = new RemoveGameEntry("Remove Board Game", history);

        entry.handle(formatter, dao);

        verify(formatter).displayMessage("No board game found with that title.");
    }
}
