package fr.fges.UI.menu.entriesUI;
import fr.fges.UI.formatters.MenuInterface;

public record ExitEntry(String label) implements MenuEntry {
    @Override
    public void handle(MenuInterface formatter) {
        formatter.displayMessage("Exiting the application. Goodbye!");
        System.exit(0);
    }
}