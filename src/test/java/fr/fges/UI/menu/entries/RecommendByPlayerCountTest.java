package fr.fges.UI.menu.entries;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.Games.GameCollectionDao;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import static org.mockito.Mockito.*;

class RecommendByPlayerCountTest {
    @Test
    void handleValid() {
        MenuInterface formatter = mock(MenuInterface.class);
        GameCollectionDao dao = mock(GameCollectionDao.class);
        RecommendByPlayerCountEntry entry = new RecommendByPlayerCountEntry("Recommend by players");
        List<BoardGame> games = List.of(
                new BoardGame("J1", 3, 4, "C1"),
                new BoardGame("J2", 2, 10, "C2")
        );
        when(formatter.getNumberFromUser("Number of players: ")).thenReturn(4);
        when(dao.findAllInAlphabeticalOrder(anyList())).thenReturn(games);

        entry.handle(formatter, dao);

        verify(formatter).displayGames(games);
        verify(formatter, never()).displayMessage(anyString());
    }

    @Test
    void handleInvalid() {
        MenuInterface formatter = mock(MenuInterface.class);
        GameCollectionDao dao = mock(GameCollectionDao.class);
        RecommendByPlayerCountEntry entry = new RecommendByPlayerCountEntry("Recommend by players");
        when(formatter.getNumberFromUser("Number of players: ")).thenReturn(5);
        when(dao.findAllInAlphabeticalOrder(anyList())).thenReturn(Collections.emptyList());

        entry.handle(formatter, dao);

        verify(formatter).displayMessage("There is no game for this number of players.");
        verify(formatter, never()).displayGames(any());
    }
}