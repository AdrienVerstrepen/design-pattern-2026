package fr.fges.data.commands;

import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RemoveGameCommandTest {

    private GameCollectionDao dao;
    private BoardGame game;
    private RemoveGameCommand command;

    @BeforeEach
    void setUp() {
        dao = mock(GameCollectionDao.class);
        game = mock(BoardGame.class);
        command = new RemoveGameCommand(game);
    }

    @Test
    void shouldReturnSameGame() {
        BoardGame result = command.getModifiedGame();

        assertSame(game, result);
    }

    @Test
    void shouldRecreateAndSaveGameWhenRestore() {
        when(game.title()).thenReturn("Catan");
        when(game.minPlayers()).thenReturn(3);
        when(game.maxPlayers()).thenReturn(4);
        when(game.category()).thenReturn("Strategy");

        command.restore(dao);

        verify(dao).save(argThat(savedGame ->
                savedGame.title().equals("Catan") &&
                savedGame.minPlayers() == 3 &&
                savedGame.maxPlayers() == 4 &&
                savedGame.category().equals("Strategy")
        ));
    }
}