package fr.fges.menu.actions;
import fr.fges.formatters.MenuInterface;
import fr.fges.repositories.GameCollectionDao;

public class ExitEntry implements MenuEntry {
    private final String label;

    public ExitEntry(String label) {
        this.label = label;
    }

    @Override
    public void handle(MenuInterface formatter, GameCollectionDao dao) {
        formatter.displayMessage("Exiting the application. Goodbye!");
        System.exit(0);
    }

    @Override
    public String label() {
        return label;
    }
}