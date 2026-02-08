package fr.fges.menu.actions;
import fr.fges.formatters.MenuInterface;
import fr.fges.models.commands.RemoveGameCommand;
import fr.fges.repositories.GameCollectionDao;
import fr.fges.repositories.HistoryDao;

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
        history.saveModification(new RemoveGameCommand(dao.findByTitle(title).get()));
        if (dao.delete(title)) {
            UI.displayMessage("Board game removed successfully.");
        } else {
            UI.displayMessage("No board game found with that title.");
        }
    }

    @Override
    public String label() {
        return label;
    }
}