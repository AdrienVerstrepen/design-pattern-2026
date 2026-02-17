package fr.fges.UI.menu.entries;

import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.Games.GameCollectionDao;
import fr.fges.data.repositories.History.HistoryDao;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AddGameTest {
    @Test
    void shouldTellUserGameIsADuplicate() {
        MenuInterface formatter = mock(MenuInterface.class);
        GameCollectionDao dao = mock(GameCollectionDao.class);
        HistoryDao history = mock(HistoryDao.class);
        AddGameEntry entry = new AddGameEntry("Recommend by players", history);
        when(dao.findByTitle(anyString())).thenReturn(Optional.of(new BoardGame("J1", 3, 4, "C1")));
        when(formatter.getGameTitle()).thenReturn("J1");
        when(formatter.getNumberFromUser(anyString())).thenReturn(2);
        when(formatter.getGameCategory()).thenReturn("Family");

        entry.handle(formatter, dao);

        verify(formatter).displayMessage("A game with the same title already exists !");
    }

    @Test
    void shouldTellUserGameHasNotBeenSaved() {
        MenuInterface formatter = mock(MenuInterface.class);
        GameCollectionDao dao = mock(GameCollectionDao.class);
        HistoryDao history = mock(HistoryDao.class);
        AddGameEntry entry = new AddGameEntry("Recommend by players", history);
        BoardGame notDuplicate = new BoardGame("J2", 3, 4, "C1");
        when(dao.findByTitle(anyString())).thenReturn(Optional.empty());
        when(formatter.getGameTitle()).thenReturn("J1");
        when(formatter.getNumberFromUser(anyString())).thenReturn(2);
        when(formatter.getGameCategory()).thenReturn("Family");
        when(dao.save(new BoardGame("J1", 2, 2, "Family"))).thenReturn(true);

        entry.handle(formatter, dao);

        verify(formatter).displayMessage("Board game added successfully.");
    }

    @Test
    void shouldConfirmGameIsAdded() {
        MenuInterface formatter = mock(MenuInterface.class);
        GameCollectionDao dao = mock(GameCollectionDao.class);
        HistoryDao history = mock(HistoryDao.class);
        AddGameEntry entry = new AddGameEntry("Recommend by players", history);
        BoardGame notDuplicate = new BoardGame("J2", 3, 4, "C1");
        when(dao.findByTitle(anyString())).thenReturn(Optional.empty());
        when(formatter.getGameTitle()).thenReturn("J1");
        when(formatter.getNumberFromUser(anyString())).thenReturn(2);
        when(formatter.getGameCategory()).thenReturn("Family");
        when(dao.save(new BoardGame("J1", 2, 2, "Family"))).thenReturn(false);

        entry.handle(formatter, dao);

        verify(formatter).displayMessage("An error occurred, please try again.");
    }
}
