package fr.fges.services.entriesServices;

import fr.fges.data.commands.RemoveGameCommand;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.data.repositories.history.HistoryDao;

import java.util.Optional;

public class RemoveGameService {
    public String removeGame(String title, HistoryDao historyDao, GameCollectionDao gamesDao) {
        Optional<BoardGame> game = gamesDao.findByTitle(title);
        if (game.isEmpty()) {
            return "No board game found with that title.";
        }
        historyDao.saveModification(new RemoveGameCommand(game.get()));
        if (gamesDao.delete(title)) {
            return "Board game removed successfully.";
        }
        return "An error has occured, while trying to remove the game";
    }
}
