package fr.fges.services;
import fr.fges.formatters.GameCollectionFormatter;
import fr.fges.formatters.MenuFormatter;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDao;
import fr.fges.services.Random.RandomNElementsStrategy;
import fr.fges.services.Random.RecommendationStrategy;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;
import java.util.Scanner;
import static fr.fges.formatters.MenuFormatter.displayMessage;
import static fr.fges.formatters.MenuFormatter.displayGames;
import static fr.fges.services.DateGestion.getWeekDay;
import static fr.fges.services.DateGestion.isWeekEnd;

public class MenuService {
    private final GameCollectionDao dao;
    private final MenuFormatter formatter;

    public MenuService(GameCollectionDao dao, MenuFormatter formatter) {
        this.dao = dao;
        this.formatter = formatter;
    }

    public String getUserInput(String numberPlayers) {
        Scanner scanner = new Scanner(System.in);
        displayMessage("%s", numberPlayers);
        return scanner.nextLine();
    }

    private void addGame(GameCollectionDao dao) {
        // Les vérifications doivent basculer dans l'UI car c'est de l'entrée / sortie
        String title = MenuLogic.verificationValidString(getUserInput("Title: "));
        String category = MenuLogic.verificationValidString(getUserInput("Category (e.g., fantasy, cooperative, family, strategy): "));
        int minPlayers = MenuLogic.verificationValidNumber(getUserInput("Minimum Players: "));
        int maxPlayers = MenuLogic.verificationValidNumber(getUserInput("Maximum Players: "));
        if (MenuLogic.isADuplicate(title, dao)) {
            displayMessage("A game with the same title already exists !");
            return;
        }
        if (dao.save(new BoardGame(title, minPlayers, maxPlayers, category))) {
            displayMessage("Board game added successfully.");
        } else {
            displayMessage("Game already exists.");
        }
    }

    private void removeGame(GameCollectionDao dao) {
        String title = getUserInput("Title of game to remove");
        if (dao.delete(title)) {
            displayMessage("Board game removed successfully.");
        } else {
            displayMessage("No board game found with that title.");
        }
    }

    private void exit() {
        displayMessage("Exiting the application. Goodbye!");
        System.exit(0);
    }

    private void summaryWeekend(GameCollectionDao dao){
        if (dao.findAll().size() <= 3){
            GameCollectionFormatter.viewAllGames(dao);
        } else {
            RecommendationStrategy myStrategy = new RandomNElementsStrategy();
            List<BoardGame> randomGames = myStrategy.getNRandomGame(3, dao);
            displayGames(randomGames);
        }
    }

    public void handleMenu() {
        formatter.displayMainMenu();
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        boolean weekEnd = isWeekEnd(getWeekDay());
        switch (choice) {
            case "1" -> addGame(dao);
            case "2" -> removeGame(dao);
            case "3" -> GameCollectionFormatter.viewAllGames(dao);
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