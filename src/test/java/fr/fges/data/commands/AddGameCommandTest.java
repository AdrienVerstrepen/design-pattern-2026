package fr.fges.data.commands;

import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddGameCommandTest {

    private GameCollectionDao dao;
    private BoardGame game;
    private AddGameCommand command;

    @BeforeEach
    void setUp() {
        dao = mock(GameCollectionDao.class);
        game = mock(BoardGame.class);
        command = new AddGameCommand(game);
    }

    @Test
    void shouldReturnSameGame() {
        BoardGame result = command.getModifiedGame();

        assertSame(game, result);
    }

    @Test
    void shouldDeleteGameFromDaoWhenRestore() {
        when(game.title()).thenReturn("Catan");

        command.restore(dao);

        verify(dao).delete("Catan");
    }
}