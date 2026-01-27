package fr.fges.services;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import static fr.fges.formatters.MenuFormatter.displayMainMenu;
import static fr.fges.services.GameService.listAllGames;

public class MenuService {
    private static String getUserInput(String numberPlayers) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%s: ", numberPlayers);
        return scanner.nextLine();
    }

    public static int verificationValidNumber(String numberPlayers) {
        // this function is there to check if the number the user entered is correct, it treats this kind of issue to avoid the program to crash
        // it's set to public to be tested in a different class
        while (true) {
            try {
                return Integer.parseInt(getUserInput(numberPlayers));
            } catch (NumberFormatException e) {
                System.out.println("The number entered is invalid, please write a correct number.");
            }
        }
    }

    public static String verificationValidString(String stringInput) {
        // this function is there to check if the title or the category entered are not empty strings
        // it's set to public to be tested in a different class
        while (true) {
            String input = getUserInput(stringInput);
            if (input != null && !input.isBlank()) {
                return input;
            }
            System.out.println("The text entered is empty, please write a correct text.");
        }
    }

    private static void addGame() {
        String title = verificationValidString("Title");
        String category = verificationValidString("Category (e.g., fantasy, cooperative, family, strategy)");
        int minPlayers = verificationValidNumber("Minimum Players");
        int maxPlayers = verificationValidNumber("Maximum Players");
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

    public static boolean isWeekEnd(int weekDay){
        return weekDay == 1 || weekDay == 7;
    }

    public static Calendar getDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar;
    }

    public static int getWeekDay() {
        Calendar calendar = getDate();
        //int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        // System.out.println(weekDay);
        return calendar.get(Calendar.DAY_OF_WEEK);
        //return 1;
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
            case "4" -> getWeekDay();
            case "5" -> exit();
            default -> System.out.println("Invalid choice. Please select a valid option.");
        }
    }
}