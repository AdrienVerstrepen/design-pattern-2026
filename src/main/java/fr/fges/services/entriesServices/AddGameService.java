package fr.fges.services.entriesServices;
import fr.fges.data.commands.AddGameCommand;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.data.repositories.history.HistoryDao;
import fr.fges.services.results.Failure;
import fr.fges.services.results.Result;
import fr.fges.services.results.Success;
import fr.fges.services.verifications.BoardGameVerificator;

public class AddGameService {
    private final GameCollectionDao dao;
    private final HistoryDao history;

    public AddGameService(GameCollectionDao dao, HistoryDao history) {
        this.dao = dao;
        this.history = history;
    }

    public Result<Void, String> addGame(BoardGame game) {
        if (BoardGameVerificator.isADuplicate(game.title(), dao)) {
            return new Failure<>("A game with the same title already exists !");
        }
        if (dao.save(game)) {
            history.saveModification(new AddGameCommand(game));
            return new Success<>(null);
        } else {
            return new Failure<>("An error occurred, please try again.");
        }
    }
}