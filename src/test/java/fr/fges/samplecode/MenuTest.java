package fr.fges.samplecode;
import java.io.ByteArrayInputStream;
import static fr.fges.services.MenuService.verificationValidNumber;
import static fr.fges.services.MenuService.verificationValidString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuTest {
    void testVerificationValidNumberValid() {
        // this function tests a valid answer
        String testInput = "4\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        int result = verificationValidNumber("Minimum Players");
        assertEquals(4, result);
    }

    void testVerificationValidNumberInvalid() {
        // this function tests an answer that's invalid and then valid
        String testInput = "a\n5\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        int result = verificationValidNumber("Minimum Players");
        assertEquals(5, result);
    }

    void testVerificationValidStringValid() {
        // this function tests a valid answer
        String testInput = "a\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        String result = verificationValidString("Title");
        assertEquals("a", result);
    }

    void testVerificationValidStringInvalid() {
        // this function tests an answer that's invalid and then valid
        String testInput = "\na\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        String result = verificationValidString("Category");
        assertEquals("a", result);
    }
}