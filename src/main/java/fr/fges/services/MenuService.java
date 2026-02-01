package fr.fges.services;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDAO;
import fr.fges.services.Random.RandomNElementsStrategy;
import fr.fges.services.Random.RandomStrategy;

import java.util.List;
import java.util.Scanner;
import static fr.fges.formatters.MenuFormatter.displayMainMenu;
import static fr.fges.formatters.MenuFormatter.displayMessage;
import static fr.fges.repositories.GameCollectionRepository.printGames;
import static fr.fges.services.DateGestion.getWeekDay;
import static fr.fges.services.DateGestion.isWeekEnd;
import static fr.fges.repositories.GameCollectionRepository.listAllGames;

public class MenuService {
    public static String getUserInput(String numberPlayers) {
        Scanner scanner = new Scanner(System.in);
        displayMessage("%s: ", numberPlayers);
        return scanner.nextLine();
    }

    private static void addGame(GameCollectionDAO dao) {
        // Les vérifications doivent basculer dans l'UI car c'est de l'entrée / sortie
        String title = MenuLogic.verificationValidString("Title");
        String category = MenuLogic.verificationValidString("Category (e.g., fantasy, cooperative, family, strategy)");
        int minPlayers = MenuLogic.verificationValidNumber("Minimum Players");
        int maxPlayers = MenuLogic.verificationValidNumber("Maximum Players");
        dao.save(new BoardGame(title, minPlayers, maxPlayers, category));
        displayMessage("Board game added successfully.");
    }

    private static void removeGame(GameCollectionDAO dao) {
        String title = getUserInput("Title of game to remove");
        if (dao.delete(title)) {
            displayMessage("Board game removed successfully.");
        } else {
            displayMessage("No board game found with that title.");
        }
    }

    private static void exit() {
        displayMessage("Exiting the application. Goodbye!");
        System.exit(0);
    }

    private static void summaryWeekend(GameCollectionDAO dao){
        if (dao.findAll().size() <= 3){
            listAllGames(dao);
        } else {
            RandomStrategy myStrategy = new RandomNElementsStrategy();
            List<BoardGame> randomGames = myStrategy.getNRandomGame(3, dao);
            printGames(randomGames);
        }
    }

    public static void handleMenu(GameCollectionDAO dao) {
        displayMainMenu();
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        boolean weekEnd = isWeekEnd(getWeekDay());
        switch (choice) {
            case "1" -> addGame(dao);
            case "2" -> removeGame(dao);
            case "3" -> listAllGames(dao);
            case "4" -> {
                if (weekEnd) {
                    summaryWeekend(dao);
                } else {
                    exit();
                }
            }
            case "5" -> {
                if (weekEnd) {
                    exit();
                } else {
                    displayMessage("Invalid choice. Please select a valid option");
                }
            }
            default -> displayMessage("Invalid choice. Please select a valid option.");
        }
    }
}