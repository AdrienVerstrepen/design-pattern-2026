package fr.fges.menu.actions;

import fr.fges.formatters.MenuFormatter;

import java.awt.*;

public class RemoveGameEntry implements MenuEntry {
    private final String label;

    public RemoveGameEntry(String label) {
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
