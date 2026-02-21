package fr.fges.services.entriesServices;
import fr.fges.data.commands.RemoveGameCommand;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.data.repositories.history.HistoryDao;
import fr.fges.services.results.Result;
import fr.fges.services.results.Success;
import fr.fges.services.results.Failure;
import java.util.Optional;

public class RemoveGameService {
    private final GameCollectionDao dao;
    private final HistoryDao history;

    public RemoveGameService(GameCollectionDao dao, HistoryDao history) {
        this.dao = dao;
        this.history = history;
    }

    public Result<Void, String> removeGame(String title) {
        Optional<BoardGame> game = dao.findByTitle(title);
        if (game.isEmpty()) {
            return new Failure<>("No board game found with that title.");
        }
        if (dao.delete(title)) {
            history.saveModification(new RemoveGameCommand(game.get()));
            return new Success<>(null);
        }
        return new Failure<>("An error occurred while trying to remove the game.");
    }
}