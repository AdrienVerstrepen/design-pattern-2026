package fr.fges.services;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDao;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static fr.fges.services.Verifications.BoardGameVerificator.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BoardGameVerificatorTest {
    @Test
    void shouldBeValidString() {
        assertTrue(isValidString("Abcd"));
        assertTrue(isValidString("Abcd2"));
        assertTrue(isValidString("Abcd 2"));
    }

    @Test
    void shouldNotBeAValidString() {
        assertFalse(isValidString(""));
    }

    @Test
    void shouldBeValidNumber() {
        assertTrue(isValidNumber("1"));
    }

    @Test
    void shouldNotBeAValidNumber() {
        assertFalse(isValidNumber("a"));
        assertFalse(isValidNumber("-2"));
    }

    @Test
    void shouldReturnListIsEmpty() {
        assertTrue(isEmptyList(new ArrayList<>()));
    }

    @Test
    void shouldNotReturnThatListIsEmpty() {
        assertFalse(isEmptyList(List.of(1, 2, 3)));
    }

    @Test
    void shouldAllowIfNoDupes() {
        GameCollectionDao myDao = mock(GameCollectionDao.class);
        when(myDao.findByTitle(anyString())).thenReturn(Optional.empty());
        assertFalse(isADuplicate("Tutel5", myDao));
    }

    @Test
    void shouldNotAllowIfDupes() {
        GameCollectionDao myDao = mock(GameCollectionDao.class);
        when(myDao.findByTitle(anyString())).thenReturn(Optional.of(new BoardGame("Tutel5", 2, 2, "fam")));
        assertTrue(isADuplicate("Tutel1", myDao));
    }
}