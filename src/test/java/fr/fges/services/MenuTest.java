package fr.fges.services;
import fr.fges.repositories.GameCollectionDAO;
import fr.fges.repositories.GameCollectionRepository;
import fr.fges.services.Random.RandomStrategy;
import org.junit.jupiter.api.Test;
import java.util.List;
import static fr.fges.services.DateGestion.isWeekEnd;
import static fr.fges.services.MenuLogic.*;
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
    void testIsWeekEndTrue(){
        // this function tests if the function detects correctly the days that are weekends
        int weekDay = 1;
        boolean result = isWeekEnd(weekDay);
        assertTrue(result);
    }

    @Test
    void testIsWeekEndFalse(){
        // this function tests if the function detects correctly the days that are not weekends
        int weekDay = 2;
        boolean result = isWeekEnd(weekDay);
        assertFalse(result);
    }

    @Test
    void shouldAllowIfNoDupes() {
        /*
        GameCollectionDAO myDao = mock(GameCollectionDAO.class);
        when(myDao.findAll()).thenReturn(List.of(
                new BoardGame("Tutel1", 2, 2, "fantasy"),
                new BoardGame("Tutel2", 2, 2, "fantasy"),
                new BoardGame("Tutel3", 2, 2, "fantasy"),
                new BoardGame("Tutel4", 2, 2, "fantasy")
        ));
        */

        GameCollectionRepository.getGames().clear();
        GameCollectionRepository.addGame("Tutel1",2,2,"fantasy");
        GameCollectionRepository.addGame("Tutel2",2,2,"fantasy");

        assertTrue(duplicateVerification("Tutel5"));
    }

    @Test
    void shouldNotAllowIfDupes() {
        /*
        GameCollectionDAO myDao = mock(GameCollectionDAO.class);
        when(myDao.findAll()).thenReturn(List.of(
                new BoardGame("Tutel1", 2, 2, "fantasy"),
                new BoardGame("Tutel2", 2, 2, "fantasy"),
                new BoardGame("Tutel3", 2, 2, "fantasy"),
                new BoardGame("Tutel4", 2, 2, "fantasy")
        ));
        */

        GameCollectionRepository.getGames().clear();
        GameCollectionRepository.addGame("Tutel1",2,2,"fantasy");
        GameCollectionRepository.addGame("Tutel2",2,2,"fantasy");

        assertFalse(duplicateVerification("Tutel1"));
    }
}