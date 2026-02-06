package fr.fges.formatters;
import fr.fges.menu.actions.MenuEntry;
import fr.fges.models.BoardGame;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static fr.fges.services.DateGestion.getWeekDay;
import static fr.fges.services.DateGestion.isWeekEnd;
import static fr.fges.services.Verifications.BoardGameVerificator.isValidNumber;
import static fr.fges.services.Verifications.BoardGameVerificator.isValidString;

public class MenuFormatter {
    private final PrintStream out;

    public MenuFormatter() {
        this.out = System.out;
    }

    public void displayMainMenu() {
        String menuText = """
            === Board Game Collection ===
            1. Add Board Game
            2. Remove Board Game
            3. List All Board Games
            """;
        if (isWeekEnd(getWeekDay())) {
            menuText += """
            4. View Summary (Weekend Special !)
            5. Exit
            Please select an option (1-5):
            """;
        } else {
            menuText += """
            4. Exit
            Please select an option (1-4):
            """;
        }
        System.out.println(menuText);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayMessage(String format, Object... args) {
        System.out.printf(format, args);
    }

    public void displayGames(List<BoardGame> games) {
        for (BoardGame game: games) {
            System.out.println(game);
        }
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

    public int getMinimumPlayers() {
        String min = getUserInput("Minimum Players: ");
        while (!isValidNumber(min)) {
            displayMessage("The number entered is invalid, please write a correct number.");
            min = getUserInput("Minimum Players: ");
        }
        return Integer.parseInt(min);
    }

    public int getMaximumPlayers() {
        String max = getUserInput("Maximum Players: ");
        while (!isValidNumber(max)) {
            displayMessage("The number entered is invalid, please write a correct number.");
            max = getUserInput("Maximum Players: ");
        }
        return Integer.parseInt(max);
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