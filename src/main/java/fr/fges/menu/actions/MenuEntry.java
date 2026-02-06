package fr.fges.menu.actions;

import fr.fges.formatters.MenuFormatter;
import fr.fges.handlers.CommandHandler;
import fr.fges.repositories.GameCollectionDao;

public interface MenuEntry {
    void handle(MenuFormatter UI, GameCollectionDao dao);
    String getLabel();
}
