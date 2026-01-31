package fr.fges.services;
import fr.fges.repositories.GameCollectionDAO;
import fr.fges.repositories.GameCollectionRepository;
import java.util.Scanner;
import static fr.fges.formatters.MenuFormatter.displayMainMenu;
import static fr.fges.services.DateGestion.getWeekDay;
import static fr.fges.services.DateGestion.isWeekEnd;
import static fr.fges.services.GameService.listAllGames;

public class MenuService {
    public static String getUserInput(String numberPlayers) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%s: ", numberPlayers);
        return scanner.nextLine();
    }

    private static void addGame() {
        String title = MenuLogic.verificationValidString("Title");
        String category = MenuLogic.verificationValidString("Category (e.g., fantasy, cooperative, family, strategy)");
        int minPlayers = MenuLogic.verificationValidNumber("Minimum Players");
        int maxPlayers = MenuLogic.verificationValidNumber("Maximum Players");
        GameService.addGame(title, minPlayers, maxPlayers, category);
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

    private static void exit() {
        System.out.println("Exiting the application. Goodbye!");
        System.exit(0);
    }

    private static void summaryWeekend(){
        if (GameCollectionRepository.numberGames() <= 3){
            listAllGames();
        } else {
            // sélection aléatoire 3 jeux
        }
    }

    public static void handleMenu() {
        displayMainMenu();
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        boolean weekEnd = isWeekEnd(getWeekDay());
        switch (choice) {
            case "1" -> addGame();
            case "2" -> removeGame();
            case "3" -> listAllGames();
            case "4" -> {
                if (weekEnd) {
                    getWeekDay();
                } else {
                    exit();
                }
            }
            case "5" -> {
                if (weekEnd) {
                    exit();
                } else {
                    System.out.println("Invalid choice. Please select a valid option.");
                }
            }
            default -> System.out.println("Invalid choice. Please select a valid option.");
        }
    }
}