package fr.fges.menu.actions;

import fr.fges.formatters.MenuFormatter;
import fr.fges.handlers.CommandHandler;

public interface MenuEntry {
    void handle(MenuFormatter UI);
    String getLabel();
}
