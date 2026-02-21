package fr.fges.services.entriesServices;
import fr.fges.data.commands.AddGameCommand;
import fr.fges.data.commands.Command;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.data.repositories.history.HistoryDao;
import static fr.fges.services.verifications.BoardGameVerificator.isEmptyList;

public class UndoLastActionService{
	private final GameCollectionDao dao;
	private final HistoryDao history;

	public UndoLastActionService(GameCollectionDao dao, HistoryDao history) {
		this.dao = dao;
		this.history = history;
	}

	public String Undo() {
		if (isEmptyList(history.findAll())) {
			return "nothing to cancel";
		}
		Command lastCommand = history.removeLast();
		lastCommand.restore(dao);
		if (lastCommand instanceof AddGameCommand) {
			return "Undone : Removed " + lastCommand.getModifiedGame().title() + " from collection";
		} else {
			return "Undone : Added " + lastCommand.getModifiedGame().title() + " to collection";
		}
	}
}