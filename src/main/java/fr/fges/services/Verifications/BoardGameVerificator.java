package fr.fges.services.Verifications;
import fr.fges.data.repositories.GameCollectionDao;
import java.util.List;

public class BoardGameVerificator {
	public static boolean isADuplicate(String title, GameCollectionDao dao){
		return dao.findByTitle(title).isPresent();
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

	public static boolean isEmptyList(List<?> list){
		return list.isEmpty();
	}
}