package fr.fges.services.entriesServices;
import fr.fges.data.commands.AddGameCommand;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.data.repositories.history.HistoryDao;
import fr.fges.services.exceptions.DuplicateGameException;
import fr.fges.services.exceptions.GameStorageException;
import fr.fges.services.results.Failure;
import fr.fges.services.results.Result;
import fr.fges.services.results.Success;
import fr.fges.services.verifications.BoardGameVerificator;

public class AddGameService {
    private final GameCollectionDao gamesDao;
    private final HistoryDao historyDao;

    public AddGameService(GameCollectionDao gamesDao, HistoryDao historyDao) {
        this.gamesDao = gamesDao;
        this.historyDao = historyDao;
    }

    public Result<Void, Exception> addGame(BoardGame game) {
        if (BoardGameVerificator.isADuplicate(game.title(), gamesDao)) {
            return new Failure<>(new DuplicateGameException());
        }
        if (gamesDao.save(game)) {
            historyDao.saveModification(new AddGameCommand(game));
            return new Success<>(null);
        } else {
            return new Failure<>(new GameStorageException());
        }
    }
}