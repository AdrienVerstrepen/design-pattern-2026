package fr.fges.UI.formatters;
import fr.fges.UI.menu.entriesUI.MenuEntry;
import fr.fges.data.models.BoardGame;
import fr.fges.data.models.Player;

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

    public void displayPlayers(List<Player> players);
}