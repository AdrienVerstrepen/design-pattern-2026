package fr.fges.menu.actions;

import fr.fges.formatters.MenuFormatter;

public class ExitEntry implements MenuEntry {
    private final String label;

    public ExitEntry(String label) {
        this.label = label;
    }

    @Override
    public void handle(MenuFormatter formatter) {
        formatter.displayMessage("Exiting the application. Goodbye!");
        System.exit(0);
    }

    @Override
    public String getLabel() {
        return label;
    }
}
