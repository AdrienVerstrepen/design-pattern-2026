package fr.fges.services;
import fr.fges.formatters.GameCollectionFormatter;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionRepository;
import java.util.Scanner;
import static fr.fges.formatters.MenuFormatter.displayMainMenu;

public class MenuService {
    private static String getUserInput(String numberPlayers) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%s: ", numberPlayers);
        return scanner.nextLine();
    }

    private static int verificationValidNumber(String numberPlayers) {
        // This function is there to check if the number the user entered is correct, it treats this kind of issue to avoid the program to crash
        while (true) {
            try {
                return Integer.parseInt(getUserInput(numberPlayers));
            } catch (NumberFormatException e) {
                System.out.println("The number entered is invalid, please write a correct number.");
            }
        }
    }

    private static void addGame() {
        String title = getUserInput("Title");
        String category = getUserInput("Category (e.g., fantasy, cooperative, family, strategy)");
        int minPlayers = verificationValidNumber("Minimum Players");
        int maxPlayers = verificationValidNumber("Maximum Players");

        BoardGame game = new BoardGame(title, minPlayers, maxPlayers, category);
        GameCollectionRepository.addGame(game);
        System.out.println("Board game added successfully.");
    }

    private static void removeGame() {
        String title = getUserInput("Title of game to remove");
        if (GameService.removeGame(title)) {
            System.out.println("Board game removed successfully.");
        } else {
            System.out.println("No board game found with that title.");
        }
    }

    private static void listAllGames() {
        GameCollectionFormatter.viewAllGames();
    }

    private static void exit() {
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