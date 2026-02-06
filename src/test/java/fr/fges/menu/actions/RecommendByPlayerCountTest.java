package fr.fges.menu.actions;
import fr.fges.formatters.MenuFormatter;
import fr.fges.formatters.MenuInterface;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDao;
import org.junit.jupiter.api.Test;
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

        when(formatter.getNumberFromUser("Number of players")).thenReturn(4);
        when(dao.findByNumberOfPlayers(4)).thenReturn(games);
        entry.handle(formatter, dao);
        verify(formatter).displayGames(games);
        verify(formatter, never()).displayMessage(anyString());
    }

    @Test
    void handleInvalid() {
        MenuInterface formatter = mock(MenuInterface.class);
        GameCollectionDao dao = mock(GameCollectionDao.class);
        RecommendByPlayerCountEntry entry = new RecommendByPlayerCountEntry("Recommend by players");

        when(formatter.getNumberFromUser("Number of players")).thenReturn(5);
        when(dao.findByNumberOfPlayers(5)).thenReturn(List.of());
        entry.handle(formatter, dao);
        verify(formatter).displayMessage("There is no game for this number of players.");
        verify(formatter, never()).displayGames(any());
    }
}