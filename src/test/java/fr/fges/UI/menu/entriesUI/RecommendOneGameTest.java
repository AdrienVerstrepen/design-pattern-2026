package fr.fges.UI.menu.entriesUI;

import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.repositories.games.GameCollectionDao;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RecommendOneGameTest {
    @Test
    void shouldDisplayOneGame() {
        MenuInterface formatter = mock(MenuInterface.class);
        GameCollectionDao dao = mock(GameCollectionDao.class);
        when(formatter.getNumberFromUser(anyString())).thenReturn(3);
        when(dao.delete("Catan")).thenReturn(true);
    }
}
