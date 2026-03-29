package fr.fges.services.results;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ResultTest {
    @Test
    void successShouldReturnValueAndBeSuccess() {
        Success<String, Exception> success = new Success<>("Hello");
        assertTrue(success.isSuccess());
        assertEquals("Hello", success.value());
        assertDoesNotThrow(success::value);
        assertThrows(UnsupportedOperationException.class, success::error);
    }

    @Test
    void failureShouldReturnErrorAndNotBeSuccess() {
        Failure<String, Exception> failure = new Failure<>(new Exception("Something went wrong"));
        assertFalse(failure.isSuccess());
        assertEquals("Something went wrong", failure.error().getMessage());
        assertDoesNotThrow(failure::error);
        assertThrows(UnsupportedOperationException.class, failure::value);
    }
}