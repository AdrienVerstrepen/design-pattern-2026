package fr.fges.UI.menu.entriesUI;

import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.services.entriesServices.RecommendOneGameService;
import fr.fges.services.results.Failure;
import fr.fges.services.results.Success;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class RecommendOneGameTest {
    @Test
    void shouldDisplayOneGame() {
        int n = 4;
        MenuInterface formatter = mock(MenuInterface.class);
        when(formatter.getNumberFromUser(anyString())).thenReturn(n);
        RecommendOneGameService service = mock(RecommendOneGameService.class);
        GameCollectionDao gamesDao = mock(GameCollectionDao.class);
        BoardGame game = new BoardGame("J1", n, n+1, "Humor");
        when(service.recommendOneGame(n, gamesDao)).thenReturn(new Success<>(game));

        RecommendOneGameEntry entry = new RecommendOneGameEntry("J1", gamesDao, service);
        entry.handle(formatter);

        verify(formatter).displayGame(game);
    }

    @Test
    void shouldDisplayInvalidNumberChosen() {
        MenuInterface formatter = mock(MenuInterface.class);
        RecommendOneGameService service = mock(RecommendOneGameService.class);
        GameCollectionDao gamesDao = mock(GameCollectionDao.class);
        when(service.recommendOneGame(anyInt(), any(GameCollectionDao.class))).thenReturn(new Failure<>("Number of players must be greater than zero."));

        RecommendOneGameEntry entry = new RecommendOneGameEntry("J1", gamesDao, service);
        entry.handle(formatter);

        verify(formatter).displayMessage("Number of players must be greater than zero.");
    }

    @Test
    void shouldDisplayNoGamesAvailable() {
        MenuInterface formatter = mock(MenuInterface.class);
        RecommendOneGameService service = mock(RecommendOneGameService.class);
        GameCollectionDao gamesDao = mock(GameCollectionDao.class);
        when(service.recommendOneGame(anyInt(), any(GameCollectionDao.class))).thenReturn(new Failure<>("No games available for this number of players."));

        RecommendOneGameEntry entry = new RecommendOneGameEntry("J1", gamesDao, service);
        entry.handle(formatter);

        verify(formatter).displayMessage("No games available for this number of players.");


    }
}
