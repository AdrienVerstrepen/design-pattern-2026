package fr.fges.services;
import fr.fges.Menu;
import fr.fges.formatters.GameCollectionFormatter;
import fr.fges.formatters.MenuFormatter;
import fr.fges.menu.actions.*;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDao;
import fr.fges.services.Random.RandomNElementsStrategy;
import fr.fges.services.Random.RecommendationStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static fr.fges.services.DateGestion.getWeekDay;
import static fr.fges.services.DateGestion.isWeekEnd;
import static fr.fges.services.MenuLogic.isValidString;

public class MenuService {
    private final GameCollectionDao dao;
    private final MenuFormatter formatter;

    public MenuService(GameCollectionDao dao, MenuFormatter formatter) {
        this.dao = dao;
        this.formatter = formatter;
    }

    public String getUserInput(String message) {
        Scanner scanner = new Scanner(System.in);
        formatter.displayMessage("%s", message);
        return scanner.nextLine();
    }

    private void addGame(GameCollectionDao dao) {
        // Les vérifications doivent basculer dans l'UI car c'est de l'entrée / sortie
        String title = MenuLogic.verificationValidString(getUserInput("Title: "));
        while (!isValidString(title)) {
            title = MenuLogic.verificationValidString(getUserInput("Title: "));
        }
        String category = MenuLogic.verificationValidString(getUserInput("Category (e.g., fantasy, cooperative, family, strategy): "));
        int minPlayers = MenuLogic.verificationValidNumber(getUserInput("Minimum Players: "), this);
        int maxPlayers = MenuLogic.verificationValidNumber(getUserInput("Maximum Players: "), this);
        if (MenuLogic.isADuplicate(title, dao)) {
            formatter.displayMessage("A game with the same title already exists !");
            return;
        }
        if (dao.save(new BoardGame(title, minPlayers, maxPlayers, category))) {
            formatter.displayMessage("Board game added successfully.");
        } else {
            formatter.displayMessage("Game already exists.");
        }
    }

    private void removeGame(GameCollectionDao dao) {
        String title = getUserInput("Title of game to remove");
        if (dao.delete(title)) {
            formatter.displayMessage("Board game removed successfully.");
        } else {
            formatter.displayMessage("No board game found with that title.");
        }
    }

    private void exit() {
        formatter.displayMessage("Exiting the application. Goodbye!");
        System.exit(0);
    }

    private void summaryWeekend(GameCollectionDao dao){
        if (dao.findAll().size() <= 3){
            GameCollectionFormatter.viewAllGames(dao);
        } else {
            RecommendationStrategy myStrategy = new RandomNElementsStrategy();
            List<BoardGame> randomGames = myStrategy.getNRandomGame(3, dao);
            formatter.displayGames(randomGames);
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
                    formatter.displayMessage("Invalid choice. Please select a valid option");
                }
            }
            default -> formatter.displayMessage("Invalid choice. Please select a valid option.");
        }
    }
}