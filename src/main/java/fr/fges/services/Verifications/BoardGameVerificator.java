package fr.fges.services.Verifications;

import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDAO;

import static fr.fges.formatters.MenuFormatter.displayMessage;

public class BoardGameVerificator {

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
