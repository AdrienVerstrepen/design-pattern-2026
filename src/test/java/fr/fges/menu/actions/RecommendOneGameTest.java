package fr.fges.menu.actions;

import fr.fges.formatters.MenuInterface;
import fr.fges.repositories.GameCollectionDao;
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
