package fr.fges.services.Verifications;
import fr.fges.repositories.GameCollectionDao;


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
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}