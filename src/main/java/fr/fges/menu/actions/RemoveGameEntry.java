package fr.fges.menu.actions;

import fr.fges.formatters.MenuFormatter;
import fr.fges.repositories.GameCollectionDao;

import java.awt.*;

public class RemoveGameEntry implements MenuEntry {
    private final String label;

    public RemoveGameEntry(String label) {
        this.label = label;
    }

    @Override
    public void handle(MenuFormatter UI, GameCollectionDao dao) {
        /*String title = getUserInput("Title of game to remove");
        if (dao.delete(title)) {
            displayMessage("Board game removed successfully.");
        } else {
            displayMessage("No board game found with that title.");
        }*/
    }

    @Override
    public String getLabel() {
        return label;
    }
}
