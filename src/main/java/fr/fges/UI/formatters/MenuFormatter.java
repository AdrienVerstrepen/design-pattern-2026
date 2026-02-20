package fr.fges.UI.formatters;
import fr.fges.UI.menu.entriesUI.MenuEntry;
import fr.fges.data.models.BoardGame;
import fr.fges.data.models.Player;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static fr.fges.services.verifications.BoardGameVerificator.*;

public class MenuFormatter implements MenuInterface {
    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void displayMessage(String format, Object... args) {
        System.out.printf(format, args);
    }

    @Override
    public void displayGames(List<BoardGame> games) {
        if (isEmptyList(games)) {
            displayMessage("Error: no games found :'(");
        }
        for (BoardGame game: games) {
            displayGame(game);
        }
    }

    @Override
    public void displayGame(BoardGame game) {
        displayMessage('"' + game.title() + '"' + ": (" + game.minPlayers() + "-" + game.maxPlayers() + " players, " + game.category() + ")");
    }

    @Override
    public String getUserInput(String message) {
        Scanner scanner = new Scanner(System.in);
        displayMessage("%s", message);
        return scanner.nextLine();
    }

    @Override
    public void displayMenu(List<MenuEntry> entries) {
        displayMessage("=== Board Game Collection ===");
        int i = 1;
        for (MenuEntry entry : entries) {
            displayMessage(i + ". " + entry.label());
            i++;
        }
    }

    @Override
    public String getGameTitle() {
        String title = getUserInput("Title: ");
        while (!isValidString(title)) {
            displayMessage("The text entered is invalid, please write a non empty text.");
            title = getUserInput("Title: ");
        }
        return title;
    }

    @Override
    public int getNumberFromUser(String message) {
        String min = getUserInput(message);
        while (!isValidNumber(min)) {
            displayMessage("The number entered is invalid, please write a correct number.");
            min = getUserInput(message);
        }
        return Integer.parseInt(min);
    }

    @Override
    public String getGameCategory() {
        String category = getUserInput("Category: ");
        while (!isValidString(category)) {
            displayMessage("The text entered is invalid, please write a non empty text.");
            category = getUserInput("Title: ");
        }
        return category;
    }

    @Override
    public void displayPlayers(List<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            displayMessage((i+1) + ". " + player.getName() + " - " + player.getPoints() + " points " + "(" + player.getNumberOfWins() + " wins)" );
        }
    }
}