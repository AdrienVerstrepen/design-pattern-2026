package fr.fges.factories;

import fr.fges.repositories.GameCollectionDao;
import fr.fges.repositories.GameCollectionDaoCsv;
import fr.fges.repositories.GameCollectionDaoJson;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DaoFactoryTest {
    @Test
    void shouldReturnJsonDao() {
        String filename = "myGreatGames.json";

        GameCollectionDao result = DaoFactory.create(filename);

        assertNotNull(result);
        assertInstanceOf(GameCollectionDaoJson.class, result);
    }

    @Test
    void shouldReturnCsvDao() {
        String filename = "myGreatGames.csv";

        GameCollectionDao result = DaoFactory.create(filename);

        assertNotNull(result);
        assertInstanceOf(GameCollectionDaoCsv.class, result);
    }

    @Test
    void shouldThrowExceptionForInvalidFileType() {
        String filename = "myGames.mmm";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> { DaoFactory.create(filename); });

        assertEquals("Unknown extension: mmm", exception.getMessage());
    }

}
