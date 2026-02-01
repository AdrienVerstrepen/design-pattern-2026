package fr.fges.services;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDAO;

import static fr.fges.formatters.MenuFormatter.displayMessage;
import static fr.fges.services.MenuService.getUserInput;

public class MenuLogic {
	public static int verificationValidNumber(String numberPlayers) {
		// this function is there to check if the number the user entered is correct, it treats this kind of issue to avoid the program to crash
		// it's set to public to be tested in a different class
		while (true) {
			try {
				return Integer.parseInt(getUserInput(numberPlayers));
			} catch (NumberFormatException e) {
				displayMessage("The number entered is invalid, please write a correct number.");
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
			displayMessage("The text entered is empty, please write a correct text.");
		}
	}

	public static boolean isValidNumber(String input) {
		// this function is there to test more easily the function verificationValidNumber without having to simulate user inputs
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isValidString(String input) {
		// this function is there to test more easily the function verificationValidString without having to simulate user inputs
		return input != null && !input.isBlank();
	}

	public static boolean isNotADuplicate(String input, GameCollectionDAO dao){
		var games = dao.findAll();
		for (BoardGame game : games) {
			if (game.title().equals(input)) {
				displayMessage("A game with the same title already exists.");
				return false;
			}
		}
		return true;
	}
}
