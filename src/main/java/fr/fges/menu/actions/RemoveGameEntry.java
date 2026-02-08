package fr.fges.menu.actions;
import fr.fges.formatters.MenuInterface;
import fr.fges.models.BoardGame;
import fr.fges.models.commands.RemoveGameCommand;
import fr.fges.repositories.GameCollectionDao;
import fr.fges.repositories.HistoryDao;

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