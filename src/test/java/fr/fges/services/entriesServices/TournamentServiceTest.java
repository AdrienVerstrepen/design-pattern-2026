package fr.fges.services.entriesServices;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.Player;
import fr.fges.services.factories.TournamentFormatFactory;
import fr.fges.services.results.Result;
import fr.fges.services.results.Success;
import fr.fges.services.tournament.TournamentFormat;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TournamentServiceTest {
    @Test
    void shouldExecuteTournamentAndReturnSortedResults() {
        TournamentService service = new TournamentService();
        MenuInterface ui = mock(MenuInterface.class);
        TournamentFormat format = mock(TournamentFormat.class);
        when(ui.getNumberFromUser("Number of participants (3-8): ")).thenReturn(3);
        when(ui.getUserInput("Enter player 1 name: ")).thenReturn("Alice");
        when(ui.getUserInput("Enter player 2 name: ")).thenReturn("Bob");
        when(ui.getUserInput("Enter player 3 name: ")).thenReturn("Charlie");
        try (MockedStatic<TournamentFormatFactory> mockedFactory = mockStatic(TournamentFormatFactory.class)) {
            mockedFactory.when(() -> TournamentFormatFactory.create(ui)).thenReturn(List.of(format));
            when(format.label()).thenReturn("Round Robin");
            when(ui.getNumberFromUser("Select format (1-1): ")).thenReturn(1);

            final List<Player>[] capturedPlayersHolder = new List[1];
            doAnswer(invocation -> {
                capturedPlayersHolder[0] = new ArrayList<>(invocation.getArgument(0));
                return null;
            }).when(format).setPlayers(anyList());
            when(format.playTournament()).thenAnswer(invocation -> new ArrayList<>(capturedPlayersHolder[0]));
            Result<List<Player>, String> result = service.execute(ui);
            assertInstanceOf(Success.class, result);
            List<Player> sorted = result.value();
            assertEquals("Alice", sorted.get(0).getName());
            assertEquals("Bob", sorted.get(1).getName());
            assertEquals("Charlie", sorted.get(2).getName());
            verify(format).setPlayers(anyList());
            verify(format).playTournament();
        }
    }
}