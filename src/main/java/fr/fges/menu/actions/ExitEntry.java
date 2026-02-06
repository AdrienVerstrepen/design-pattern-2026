package fr.fges.menu.actions;
import fr.fges.formatters.MenuFormatter;
import fr.fges.repositories.GameCollectionDao;

public class ExitEntry implements MenuEntry {
    private final String label;

    public ExitEntry(String label) {
        this.label = label;
    }

    @Override
    public void handle(MenuFormatter formatter, GameCollectionDao dao) {
        formatter.displayMessage("Exiting the application. Goodbye!");
        System.exit(0);
    }

    @Override
    public String getLabel() {
        return label;
    }
}