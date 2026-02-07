package fr.fges.menu.actions;

import fr.fges.formatters.MenuFormatter;
import fr.fges.formatters.MenuInterface;
import fr.fges.repositories.GameCollectionDao;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class RemoveGameTest {
    @Test
    void shouldTellUserGameHasBeenRemoved () {
        MenuInterface formatter = mock(MenuInterface.class);
        GameCollectionDao dao = mock(GameCollectionDao.class);
        when(formatter.getGameTitle()).thenReturn("Catan");
        when(dao.delete("Catan")).thenReturn(true);
        RemoveGameEntry entry = new RemoveGameEntry("Remove Board Game");


        entry.handle(formatter, dao);

        verify(formatter).displayMessage("Board game removed successfully.");
    }

    @Test
    void shouldTellUserNoGameToRemove () {
        MenuInterface formatter = mock(MenuInterface.class);
        GameCollectionDao dao = mock(GameCollectionDao.class);
        when(formatter.getGameTitle()).thenReturn("Catan");
        when(dao.delete("Catan")).thenReturn(false);
        RemoveGameEntry entry = new RemoveGameEntry("Remove Board Game");

        entry.handle(formatter, dao);

        verify(formatter).displayMessage("No board game found with that title.");
    }


}
