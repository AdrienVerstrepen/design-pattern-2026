package fr.fges.services.Verifications;

import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDao;

import static fr.fges.formatters.MenuFormatter.displayMessage;

public class BoardGameVerificator {

	public static boolean isADuplicate(String title, GameCollectionDao dao){
		return dao.findAll().stream().anyMatch(game -> game.title().equalsIgnoreCase(title));
	}

}
