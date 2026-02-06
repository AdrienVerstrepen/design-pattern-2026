package fr.fges.menu.actions;
import fr.fges.formatters.MenuFormatter;
import fr.fges.repositories.GameCollectionDao;

public class RemoveGameEntry implements MenuEntry {
    private final String label;

    public RemoveGameEntry(String label) {
        this.label = label;
    }

    @Override
    public void handle(MenuFormatter UI, GameCollectionDao dao) {
        String title = UI.getGameTitle();
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