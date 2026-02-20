package fr.fges.UI.menu.entriesUI;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.BoardGame;
import fr.fges.data.commands.RemoveGameCommand;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.data.repositories.history.HistoryDao;

import java.util.Optional;

public record RemoveGameEntry (String label, HistoryDao historyDao, GameCollectionDao dao) implements MenuEntry {

    @Override
    public void handle(MenuInterface UI) {
        String title = UI.getGameTitle();
        Optional<BoardGame> game = dao.findByTitle(title);
        if (game.isEmpty()) {
            UI.displayMessage("No board game found with that title.");
            return;
        }
        historyDao.saveModification(new RemoveGameCommand(game.get()));
        if (dao.delete(title)) {
            UI.displayMessage("Board game removed successfully.");
        }
    }

    @Override
    public String label() {
        return label;
    }
}