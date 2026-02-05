package fr.fges.services;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDao;
import org.junit.jupiter.api.Test;
import java.util.List;
import static fr.fges.services.MenuLogic.*;
import static fr.fges.services.Verifications.BoardGameVerificator.isADuplicate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MenuTest {
    @Test
    void testVerificationValidNumberValid() {
        // this function tests an answer that's valid
        assertTrue(isValidNumber("1"));
    }

    @Test
    void testVerificationValidNumberInvalid() {
        // this function tests an answer that's invalid
        assertFalse(isValidNumber("a"));
    }

    @Test
    void testVerificationValidStringValid() {
        // this function tests an answer that's valid
        assertTrue(isValidString("a"));
    }

    @Test
    void testVerificationValidStringInvalid() {
        // this function tests an answer that's invalid
        assertFalse(isValidString(""));
        assertFalse(isValidString("   "));
    }

    @Test
    void shouldAllowIfNoDupes() {
        GameCollectionDao myDao = mock(GameCollectionDao.class);
        when(myDao.findAll()).thenReturn(List.of(
                new BoardGame("Tutel1", 2, 2, "fantasy"),
                new BoardGame("Tutel2", 2, 2, "fantasy"),
                new BoardGame("Tutel3", 2, 2, "fantasy"),
                new BoardGame("Tutel4", 2, 2, "fantasy")
        ));
        assertFalse(isADuplicate("Tutel5", myDao));
    }

    @Test
    void shouldNotAllowIfDupes() {
        GameCollectionDao myDao = mock(GameCollectionDao.class);
        when(myDao.findAll()).thenReturn(List.of(
                new BoardGame("Tutel1", 2, 2, "fantasy"),
                new BoardGame("Tutel2", 2, 2, "fantasy"),
                new BoardGame("Tutel3", 2, 2, "fantasy"),
                new BoardGame("Tutel4", 2, 2, "fantasy")
        ));
        assertTrue(isADuplicate("Tutel1", myDao));
    }
}