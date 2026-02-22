package fr.fges.UI.menu.entriesUI;

import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.services.entriesServices.ListAllGamesService;
import fr.fges.services.results.Failure;
import fr.fges.services.results.Success;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.mockito.Mockito.*;

public class ListAllGamesTest {
    @Test
    void shouldDisplayGames() {
        MenuInterface formatter = mock(MenuInterface.class);
        ListAllGamesService service = mock(ListAllGamesService.class);
        List<BoardGame> games = List.of(
                new BoardGame("J1", 1, 2, "Family"),
                new BoardGame("J2", 3, 5, "Test"),
                new BoardGame("J3", 2, 5, "Humor"),
                new BoardGame("J4", 4, 6, "Fantasy")
        );
        when(service.findAllGames()).thenReturn(new Success<>(games));
        ListAllGamesEntry entry = new ListAllGamesEntry("List All Board Games", service);

        entry.handle(formatter);

        verify(formatter).displayGames(games);
    }

    @Test
    void shouldDisplayNoGamesArePresent() {
        MenuInterface formatter = mock(MenuInterface.class);
        GameCollectionDao dao = mock(GameCollectionDao.class);
        ListAllGamesService service = mock(ListAllGamesService.class);
        when(dao.findAll()).thenReturn(null);
        when(service.findAllGames()).thenReturn(new Failure<>("No games found in the collection."));
        ListAllGamesEntry entry = new ListAllGamesEntry("List All Board Games", service);

        entry.handle(formatter);

        verify(formatter).displayMessage("No games found in the collection.");
    }

}
