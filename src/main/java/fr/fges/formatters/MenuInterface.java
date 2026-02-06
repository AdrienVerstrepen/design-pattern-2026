package fr.fges.formatters;
import fr.fges.menu.actions.MenuEntry;
import fr.fges.models.BoardGame;
import java.util.List;

public interface MenuInterface {
    void displayMessage(String message);

    void displayMessage(String format, Object... args);

    void displayGames(List<BoardGame> games);

    void displayGame(BoardGame game);

    String getUserInput(String message);

    void displayMenu(List<MenuEntry> entries);

    String getGameTitle();

    int getNumberFromUser(String message);

    String getGameCategory();
}