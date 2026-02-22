package fr.fges.services.entriesServices;
import fr.fges.data.commands.AddGameCommand;
import fr.fges.data.commands.Command;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.data.repositories.history.HistoryDao;
import static fr.fges.services.verifications.BoardGameVerificator.isEmptyList;

public class UndoLastActionService{
	private final GameCollectionDao gamesDao;
	private final HistoryDao historyDao;

	public UndoLastActionService(GameCollectionDao gamesDao, HistoryDao history) {
		this.gamesDao = gamesDao;
		this.historyDao = history;
	}

	public String undo() {
		if (isEmptyList(historyDao.findAll())) {
			return "nothing to cancel";
		}
		Command lastCommand = historyDao.removeLast();
		lastCommand.restore(gamesDao);
		if (lastCommand instanceof AddGameCommand) {
			return "Undone : Removed " + lastCommand.getModifiedGame().title() + " from collection";
		} else {
			return "Undone : Added " + lastCommand.getModifiedGame().title() + " to collection";
		}
	}
}