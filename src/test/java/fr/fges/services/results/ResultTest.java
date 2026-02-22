package fr.fges.services.results;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ResultTest {
    @Test
    void successShouldReturnValueAndBeSuccess() {
        Success<String, String> success = new Success<>("Hello");
        assertTrue(success.isSuccess());
        assertEquals("Hello", success.value());
        assertDoesNotThrow(success::value);
        assertThrows(UnsupportedOperationException.class, success::error);
    }

    @Test
    void failureShouldReturnErrorAndNotBeSuccess() {
        Failure<String, String> failure = new Failure<>("Something went wrong");
        assertFalse(failure.isSuccess());
        assertEquals("Something went wrong", failure.error());
        assertDoesNotThrow(failure::error);
        assertThrows(UnsupportedOperationException.class, failure::value);
    }
}