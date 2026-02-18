package fr.fges.UI.menu.entriesUI;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.BoardGame;
import fr.fges.data.commands.RemoveGameCommand;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.data.repositories.history.HistoryDao;

import java.util.Optional;

public class RemoveGameEntry implements MenuEntry {
    private final String label;
    private final HistoryDao history;

    public RemoveGameEntry(String label, HistoryDao history) {
        this.label = label;
        this.history = history;
    }

    @Override
    public void handle(MenuInterface UI, GameCollectionDao dao) {
        String title = UI.getGameTitle();
        Optional<BoardGame> game = dao.findByTitle(title);
        if (game.isEmpty()) {
            UI.displayMessage("No board game found with that title.");
            return;
        }
        history.saveModification(new RemoveGameCommand(game.get()));
        if (dao.delete(title)) {
            UI.displayMessage("Board game removed successfully.");
        }
    }

    @Override
    public String label() {
        return label;
    }
}