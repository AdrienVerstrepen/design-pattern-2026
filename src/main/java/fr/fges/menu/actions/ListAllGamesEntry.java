package fr.fges.menu.actions;

import fr.fges.formatters.MenuFormatter;

public class ListAllGamesEntry implements MenuEntry {

    private final String label;

    public ListAllGamesEntry(String label) {
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
