package fr.fges.samplecode;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameCollectionTest {
    @Test
    void numberGamesValid() {
        BoardGame game = new BoardGame("a", 1, 2, "b");
        GameCollectionRepository.addGame(game);
        assertEquals(1, GameCollectionRepository.numberGames());
    }
}
