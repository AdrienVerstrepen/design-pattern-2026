package fr.fges.UI.formatters;
import fr.fges.data.models.BoardGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MenuFormatterTest {
    private MenuFormatter formatter;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        formatter = new MenuFormatter();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void displayMessageShouldPrintToConsole() {
        formatter.displayMessage("Hello World");
        assertTrue(outContent.toString().contains("Hello World"));
    }

    @Test
    void displayGamesShouldPrintAllGames() {
        BoardGame game1 = new BoardGame("Catan", 3, 4, "Strategy");
        BoardGame game2 = new BoardGame("Monopoly", 2, 6, "Family");
        formatter.displayGames(List.of(game1, game2));
        String output = outContent.toString();
        assertTrue(output.contains("Catan"));
        assertTrue(output.contains("Monopoly"));
    }
}