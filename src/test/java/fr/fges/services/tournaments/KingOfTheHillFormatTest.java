package fr.fges.services.tournaments;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.Player;
import fr.fges.services.tournament.KingOfTheHillFormat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KingOfTheHillFormatTest {
    private MenuInterface ui;
    private KingOfTheHillFormat format;

    @BeforeEach
    void setUp() {
        ui = mock(MenuInterface.class);
        format = new KingOfTheHillFormat("King of the Hill", ui);
    }

    @Test
    void shouldReturnLabel() {
        assertEquals("King of the Hill", format.label());
    }

    @Test
    void shouldAttributePointsCorrectly() {
        Player p = new Player("Alice", 0, 0);
        format.attributePoints(p, 3);
        assertEquals(3, p.getPoints());
        assertEquals(1, p.getNumberOfWins());
        format.attributePoints(p, 1);
        assertEquals(4, p.getPoints());
        assertEquals(1, p.getNumberOfWins(), "Wins should not increase when gain != 3");
    }

    @Test
    void shouldPlayMatchAndAssignPoints() {
        Player p1 = new Player("Alice", 0, 0);
        Player p2 = new Player("Bob", 0, 0);
        when(ui.getUserInput(anyString())).thenReturn("2");
        format.playMatch(p1, p2);

        assertEquals(3, p2.getPoints());
        assertEquals(1, p2.getNumberOfWins());
        assertEquals(1, p1.getPoints());
        assertEquals(0, p1.getNumberOfWins());
        verify(ui, atLeastOnce()).displayMessage(contains("VS"));
    }

    @Test
    void shouldPlayTournamentWithKingOfTheHillLogic() {
        Player p1 = new Player("Alice", 0, 0);
        Player p2 = new Player("Bob", 0, 0);
        Player p3 = new Player("Charlie", 0, 0);
        List<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        format.setPlayers(players);

        when(ui.getUserInput(anyString())).thenReturn("2", "2");
        List<Player> results = format.playTournament();
        assertEquals(3, results.size());
        assertEquals("Alice", results.getFirst().getName());
        assertEquals(1, results.get(0).getPoints());
        assertEquals(0, results.get(0).getNumberOfWins());
        assertEquals("Bob", results.get(1).getName());
        assertEquals(4, results.get(1).getPoints());
        assertEquals(1, results.get(1).getNumberOfWins());
        assertEquals("Charlie", results.get(2).getName());
        assertEquals(3, results.get(2).getPoints());
        assertEquals(1, results.get(2).getNumberOfWins());
        verify(ui, times(2)).getUserInput(anyString());
        verify(ui, atLeast(2)).displayMessage(contains("VS"));
    }

    @Test
    void shouldReturnEmptyListIfNoPlayers() {
        format.setPlayers(new ArrayList<>());
        List<Player> results = format.playTournament();
        assertTrue(results.isEmpty());
    }
}