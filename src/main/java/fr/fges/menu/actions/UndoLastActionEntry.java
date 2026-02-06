package fr.fges.menu.actions;

import fr.fges.formatters.MenuFormatter;

public class UndoLastActionEntry implements MenuEntry {
    private final String label;

    public UndoLastActionEntry(String label) {
        this.label = label;
    }

    @Override
    public void handle(MenuFormatter UI) {

    }

    @Override
    public String getLabel() {
        return label;
    }
}
