package fr.fges.services.factories;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.services.tournament.ChampionshipFormat;
import fr.fges.services.tournament.KingOfTheHillFormat;
import fr.fges.services.tournament.TournamentFormat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class TournamentFormatFactoryTest {
    private MenuInterface mockUI;

    @BeforeEach
    void setUp() {
        mockUI = mock(MenuInterface.class);
    }

    @Test
    void testCreateTournamentFormats() {
        List<TournamentFormat> formats = TournamentFormatFactory.create(mockUI);
        assertNotNull(formats, "La liste de formats ne doit pas être nulle");
        assertEquals(2, formats.size(), "Il doit y avoir exactement 2 formats de tournoi");
        assertInstanceOf(ChampionshipFormat.class, formats.get(0), "Le premier format doit être ChampionshipFormat");
        assertInstanceOf(KingOfTheHillFormat.class, formats.get(1), "Le second format doit être KingOfTheHillFormat");
        assertEquals("Championship (everyone plays everyone)", formats.get(0).label());
        assertEquals("King of the Hill (winner stays)", formats.get(1).label());
    }
}