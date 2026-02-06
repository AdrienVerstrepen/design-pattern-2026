package fr.fges.formatters;
import fr.fges.menu.actions.MenuEntry;
import fr.fges.models.BoardGame;

import java.util.List;
import java.util.Scanner;

import static fr.fges.services.Verifications.BoardGameVerificator.*;

public class MenuFormatter {

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayMessage(String format, Object... args) {
        System.out.printf(format, args);
    }

    public void displayGames(List<BoardGame> games) {
        if (isEmptyList(games)) {
            displayMessage("Error: no games found :'(");
        }
        for (BoardGame game: games) {
            displayGame(game);
        }
    }

    public void displayGame(BoardGame game) {
        displayMessage('"' + game.title() + '"' + ": (" + game.minPlayers() + "-" + game.maxPlayers() + " players, " + game.category() + ")");
    }

    public String getUserInput(String message) {
        Scanner scanner = new Scanner(System.in);
        displayMessage("%s", message);
        return scanner.nextLine();
    }

    public void displayMenu(List<MenuEntry> entries) {
        displayMessage("=== Board Game Collection ===");
        int i = 1;
        for (MenuEntry entry : entries) {
            displayMessage(i + ". " + entry.getLabel());
            i++;
        }
    }

    public String getGameTitle() {
        String title = getUserInput("Title: ");
        while (!isValidString(title)) {
            displayMessage("The text entered is invalid, please write a non empty text.");
            title = getUserInput("Title: ");
        }
        return title;
    }

    public int getNumberFromUser(String message) {
        String min = getUserInput(message);
        while (!isValidNumber(min)) {
            displayMessage("The number entered is invalid, please write a correct number.");
            min = getUserInput(message);
        }
        return Integer.parseInt(min);
    }

    public String getGameCategory() {
        String category = getUserInput("Category: ");
        while (!isValidString(category)) {
            displayMessage("The text entered is invalid, please write a non empty text.");
            category = getUserInput("Title: ");
        }
        return category;
    }
}