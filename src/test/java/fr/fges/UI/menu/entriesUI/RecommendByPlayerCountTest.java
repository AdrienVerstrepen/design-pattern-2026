package fr.fges.UI.menu.entriesUI;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.services.entriesServices.RecommendByPlayerCountService;
import fr.fges.services.exceptions.InvalidPlayerCountException;
import fr.fges.services.exceptions.NoMatchingGamesException;
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
        when(service.recommendByPlayerCount(anyInt())).thenReturn(new Failure<>(new InvalidPlayerCountException()));
        RecommendByPlayerCountEntry entry = new RecommendByPlayerCountEntry("Recommend games for a number of players", service);

        entry.handle(formatter);

        verify(formatter).displayMessage("Number of players must be greater than zero.");
    }

    @Test
    void shouldDisplayInvalidNumberChosen() {
        MenuInterface formatter = mock(MenuInterface.class);
        RecommendByPlayerCountService service =  mock(RecommendByPlayerCountService.class);
        when(service.recommendByPlayerCount(anyInt())).thenReturn(new Failure<>(new NoMatchingGamesException()));
        RecommendByPlayerCountEntry entry = new RecommendByPlayerCountEntry("Recommend games for a number of players", service);

        entry.handle(formatter);

        verify(formatter).displayMessage("No games available for this number of players.");
    }

}