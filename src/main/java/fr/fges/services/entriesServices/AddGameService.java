package fr.fges.services.entriesServices;

import fr.fges.data.commands.AddGameCommand;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.data.repositories.history.HistoryDao;
import fr.fges.services.verifications.BoardGameVerificator;

public class AddGameService {
    public String addGame(BoardGame game, GameCollectionDao dao, HistoryDao history) {
        if (BoardGameVerificator.isADuplicate(game.title(), dao)) {
            return "A game with the same title already exists !";
        }
        if (dao.save(game)) {
            history.saveModification(new AddGameCommand(game));
            return "Board game added successfully.";
        } else {
            return "An error occurred, please try again.";
        }
    }
}
