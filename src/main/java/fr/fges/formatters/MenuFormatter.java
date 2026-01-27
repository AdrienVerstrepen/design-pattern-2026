package fr.fges.formatters;

import fr.fges.services.MenuService;

public class MenuFormatter {
    public static void displayMainMenu() {
        if (MenuService.isWeekEnd(MenuService.getWeekDay())){
            String menuText = """
                === Board Game Collection ===
                1. Add Board Game
                2. Remove Board Game
                3. List All Board Games
                4. View summary
                5. Exit
                Please select an option (1-5):
                """;
            System.out.println(menuText);
        } else {
            String menuText = """
                === Board Game Collection ===
                1. Add Board Game
                2. Remove Board Game
                3. List All Board Games
                4. Exit
                Please select an option (1-4):
                """;
            System.out.println(menuText);
        }
    }
}