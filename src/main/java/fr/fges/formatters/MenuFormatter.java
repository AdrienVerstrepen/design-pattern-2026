package fr.fges.formatters;
import static fr.fges.services.DateGestion.getWeekDay;
import static fr.fges.services.DateGestion.isWeekEnd;

public class MenuFormatter {
    public static void displayMainMenu() {
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

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public static void displayMessage(String format, Object... args) {
        System.out.printf(format, args);
    }
}