package fr.fges.services.entriesServices;
import fr.fges.data.commands.AddGameCommand;
import fr.fges.data.commands.Command;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.data.repositories.history.HistoryDao;
import fr.fges.services.exceptions.CancelActionException;
import fr.fges.services.results.Failure;
import fr.fges.services.results.Result;

import static fr.fges.services.verifications.BoardGameVerificator.isEmptyList;

public class UndoLastActionService{
	private final GameCollectionDao gamesDao;
	private final HistoryDao historyDao;

	public UndoLastActionService(GameCollectionDao gamesDao, HistoryDao history) {
		this.gamesDao = gamesDao;
		this.historyDao = history;
	}

	public Result<Command, Exception> undo() {
		if (isEmptyList(historyDao.findAll())) {
			return new Failure<>(new CancelActionException());
		}
		Command lastCommand = historyDao.removeLast();
		lastCommand.restore(gamesDao);
		return lastCommand;
	}
}