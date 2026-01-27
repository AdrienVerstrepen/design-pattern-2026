package fr.fges.samplecode;
import fr.fges.services.MenuService;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static fr.fges.services.MenuService.*;
import static org.junit.jupiter.api.Assertions.*;

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
        assertTrue(MenuService.isValidString("a"));
    }

    @Test
    void testVerificationValidStringInvalid() {
        // this function tests an answer that's invalid
        assertFalse(MenuService.isValidString(""));
        assertFalse(MenuService.isValidString("   "));
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
}