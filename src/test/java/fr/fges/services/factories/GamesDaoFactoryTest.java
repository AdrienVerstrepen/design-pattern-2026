package fr.fges.services.factories;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.data.repositories.games.GameCollectionDaoCsv;
import fr.fges.data.repositories.games.GameCollectionDaoJson;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GamesFactoryTest {
    @Test
    void shouldReturnJsonDao() {
        String filename = "myGreatGames.json";
        GameCollectionDao result = GamesFactory.create(filename);
        assertNotNull(result);
        assertInstanceOf(GameCollectionDaoJson.class, result);
    }

    @Test
    void shouldReturnCsvDao() {
        String filename = "myGreatGames.csv";
        GameCollectionDao result = GamesFactory.create(filename);
        assertNotNull(result);
        assertInstanceOf(GameCollectionDaoCsv.class, result);
    }

    @Test
    void shouldThrowExceptionForInvalidFileType() {
        String filename = "myGames.mmm";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> { GamesFactory.create(filename); });
        assertEquals("Unknown extension: mmm", exception.getMessage());
    }
}