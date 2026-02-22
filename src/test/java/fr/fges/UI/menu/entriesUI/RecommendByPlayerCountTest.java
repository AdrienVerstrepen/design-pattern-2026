package fr.fges.UI.menu.entriesUI;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.services.entriesServices.RecommendByPlayerCountService;
import fr.fges.services.results.Failure;
import fr.fges.services.results.Success;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.mockito.Mockito.*;

class RecommendByPlayerCountTest {
    @Test
    void shouldDisplayGames() {
        MenuInterface formatter = mock(MenuInterface.class);
        RecommendByPlayerCountService service =  mock(RecommendByPlayerCountService.class);
        List<BoardGame> games = List.of(
                new BoardGame("J1", 1, 2, "Family"),
                new BoardGame("J2", 3, 5, "Test"),
                new BoardGame("J3", 2, 5, "Humor"),
                new BoardGame("J4", 4, 6, "Fantasy")
        );
        when(service.recommendByPlayerCount(anyInt())).thenReturn(new Success<>(games));
        RecommendByPlayerCountEntry entry = new RecommendByPlayerCountEntry("Recommend games for a number of players", service);

        entry.handle(formatter);

        verify(formatter).displayGames(games);
    }

    @Test
    void shouldDisplayNoGamesAvailable() {
        MenuInterface formatter = mock(MenuInterface.class);
        RecommendByPlayerCountService service =  mock(RecommendByPlayerCountService.class);
        when(service.recommendByPlayerCount(anyInt())).thenReturn(new Failure<>("The number of players must be bigger than zero."));
        RecommendByPlayerCountEntry entry = new RecommendByPlayerCountEntry("Recommend games for a number of players", service);

        entry.handle(formatter);

        verify(formatter).displayMessage("The number of players must be bigger than zero.");
    }

    @Test
    void shouldDisplayInvalidNumberChosen() {
        MenuInterface formatter = mock(MenuInterface.class);
        RecommendByPlayerCountService service =  mock(RecommendByPlayerCountService.class);
        when(service.recommendByPlayerCount(anyInt())).thenReturn(new Failure<>("There is no game for this number of players."));
        RecommendByPlayerCountEntry entry = new RecommendByPlayerCountEntry("Recommend games for a number of players", service);

        entry.handle(formatter);

        verify(formatter).displayMessage("There is no game for this number of players.");
    }

}