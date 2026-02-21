package fr.fges.data.repositories;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public abstract class GameCollectionDaoTest {
    @BeforeEach
    void setUp() throws IOException {
        File csvfile = new File("unit-test.csv");
        File jsonfile = new File("unit-test.json");
        if (!csvfile.exists()) {
            csvfile.createNewFile();
        }
        if (!jsonfile.exists()) {
            java.nio.file.Files.writeString(jsonfile.toPath(), "[]");
        }
    }

    @AfterEach
    void cleanup() {
        new File("unit-test.json").delete();
        new File("unit-test.csv").delete();
    }

    protected abstract GameCollectionDao getDao();

    @Test
    void shouldAddGame() {
        GameCollectionDao myDao = getDao();
        BoardGame myGame = new BoardGame("Tutel", 1, 4, "humor");
        myDao.save(myGame);
        assertTrue(myDao.findAll().contains(myGame));
    }

    @Test
    void shouldDeleteGame() {
        GameCollectionDao myDao = getDao();
        BoardGame myGame = new BoardGame("Tutel", 1, 4, "humor");
        myDao.save(myGame);
        assertTrue(myDao.delete(myGame.title()));
        assertFalse(myDao.findAll().contains(myGame));
    }

    @Test
    void shouldRetrieveGames() {
        GameCollectionDao myDao = getDao();
        myDao.save(new BoardGame("Tutel1", 2, 2, "fantasy"));
        myDao.save(new BoardGame("Tutel2", 2, 2, "fantasy"));
        myDao.save(new BoardGame("Tutel3", 2, 2, "fantasy"));
        myDao.save(new BoardGame("Tutel4", 2, 2, "fantasy"));

        List<BoardGame> myGames = myDao.findAll();

        assertNotNull(myGames);
        assertEquals(4, myGames.size());
    }
}