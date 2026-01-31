package fr.fges.repositories;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameCollectionTest {
    @Test
    void numberGamesValid() {
        GameCollectionRepository.addGame("a", 1, 2, "b");
        assertEquals(1, GameCollectionRepository.numberGames());
    }
}