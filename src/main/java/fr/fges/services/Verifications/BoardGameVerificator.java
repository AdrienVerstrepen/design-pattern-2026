package fr.fges.services.Verifications;
import fr.fges.models.BoardGame;
import fr.fges.models.commands.Command;
import fr.fges.repositories.GameCollectionDao;

import java.util.List;


public class BoardGameVerificator {
	public static boolean isADuplicate(String title, GameCollectionDao dao){
		return dao.findAll().stream().anyMatch(game -> game.title().equalsIgnoreCase(title));
	}

	public static boolean isValidString(String input) {
		// this function is there to test more easily the function verificationValidString without having to simulate user inputs
		return input != null && !input.isBlank();
	}

	public static boolean isValidNumber(String input) {
		try {
			int parsedInt = Integer.parseInt(input);
			return (parsedInt >= 0);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isEmptyList(List<BoardGame> games){
		return games.isEmpty();
	}
}