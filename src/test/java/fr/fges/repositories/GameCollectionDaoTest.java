package fr.fges.repositories;

import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDAO;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class GameCollectionDaoTest {

    protected abstract GameCollectionDAO getDao();

    @Test
    void shouldAddGame() {
        GameCollectionDAO myDao = getDao();
        BoardGame myGame = new BoardGame("Tutel", 1, 4, "humor");

        myDao.save(myGame);

        assertTrue(myDao.findAll().contains(myGame));
    }

    @Test
    void shouldDeleteGame() {
        GameCollectionDAO myDao = getDao();
        BoardGame myGame = new BoardGame("Tutel", 1, 4, "humor");
        myDao.save(myGame);

        assertTrue(myDao.delete(myGame.title()));

        assertFalse(myDao.findAll().contains(myGame));
    }

    @Test
    void shouldRetrieveGames() {
        GameCollectionDAO myDao = getDao();
        myDao.save(new BoardGame("Tutel1", 2, 2, "fantasy"));
        myDao.save(new BoardGame("Tutel2", 2, 2, "fantasy"));
        myDao.save(new BoardGame("Tutel3", 2, 2, "fantasy"));
        myDao.save(new BoardGame("Tutel4", 2, 2, "fantasy"));

        List<BoardGame> myGames = myDao.findAll();

        assertNotNull(myGames);
        assertEquals(4, myGames.size());

    }

}
