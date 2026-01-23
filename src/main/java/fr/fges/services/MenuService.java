package fr.fges.services;
import fr.fges.formatters.GameCollectionFormatter;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionRepository;
import java.util.Scanner;
import static fr.fges.formatters.MenuFormatter.displayMainMenu;

public class MenuService {
    public static String getUserInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%s: ", prompt);
        return scanner.nextLine();
    }

    public static void addGame() {
        String title = getUserInput("Title");
        String minPlayersStr = getUserInput("Minimum Players");
        String maxPlayersStr = getUserInput("Maximum Players");
        String category = getUserInput("Category (e.g., fantasy, cooperative, family, strategy)");

        int minPlayers = Integer.parseInt(minPlayersStr);
        int maxPlayers = Integer.parseInt(maxPlayersStr);

        BoardGame game = new BoardGame(title, minPlayers, maxPlayers, category);
        GameCollectionRepository.addGame(game);
        System.out.println("Board game added successfully.");
    }

    public static void removeGame() {
        String title = getUserInput("Title of game to remove");

        if (GameService.removeGame(title)) {
            System.out.println("Board game removed successfully.");
        } else {
            System.out.println("No board game found with that title.");
        }
    }

    public static void listAllGames() {
        GameCollectionFormatter.viewAllGames();
    }

    public static void exit() {
        System.out.println("Exiting the application. Goodbye!");
        System.exit(0);
    }

    public static void handleMenu() {
        displayMainMenu();
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> addGame();
            case "2" -> removeGame();
            case "3" -> listAllGames();
            case "4" -> exit();
            default -> System.out.println("Invalid choice. Please select a valid option.");
        }
    }
}