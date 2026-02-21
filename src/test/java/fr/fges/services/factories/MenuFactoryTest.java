package fr.fges.services.factories;
import fr.fges.UI.menu.entriesUI.*;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.data.repositories.history.HistoryDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class MenuFactoryTest {
    private HistoryDao historyDao;
    private GameCollectionDao gamesDao;

    @BeforeEach
    void setUp() {
        historyDao = mock(HistoryDao.class);
        gamesDao = mock(GameCollectionDao.class);
    }

    @Test
    void testCreateMenuEntries() {
        List<MenuEntry> menuEntries = MenuFactory.create(historyDao, gamesDao);
        assertNotNull(menuEntries, "La liste de MenuEntry ne doit pas être nulle");
        assertFalse(menuEntries.isEmpty(), "La liste de MenuEntry ne doit pas être vide");

        assertInstanceOf(AddGameEntry.class, menuEntries.get(0));
        assertInstanceOf(RemoveGameEntry.class, menuEntries.get(1));
        assertInstanceOf(ListAllGamesEntry.class, menuEntries.get(2));
        assertInstanceOf(RecommendOneGameEntry.class, menuEntries.get(3));

        boolean summaryPresent = menuEntries.stream().anyMatch(e -> e instanceof SummaryEntry);
        assertTrue(summaryPresent || menuEntries.size() == 8, "SummaryEntry ne doit apparaître que le weekend");

        assertTrue(menuEntries.stream().anyMatch(e -> e instanceof RecommendByPlayerCountEntry));
        assertTrue(menuEntries.stream().anyMatch(e -> e instanceof UndoLastActionEntry));
        assertTrue(menuEntries.stream().anyMatch(e -> e instanceof TournamentEntry));
        assertTrue(menuEntries.stream().anyMatch(e -> e instanceof ExitEntry));

        AddGameEntry addEntry = (AddGameEntry) menuEntries.getFirst();
        assertNotNull(addEntry.service(), "AddGameEntry doit avoir un service non nul");

        ExitEntry exitEntry = (ExitEntry) menuEntries.getLast();
        assertEquals("Exit", exitEntry.label());
    }
}