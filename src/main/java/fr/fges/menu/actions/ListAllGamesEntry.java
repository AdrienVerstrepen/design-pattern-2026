package fr.fges.menu.actions;

import fr.fges.formatters.MenuFormatter;
import fr.fges.repositories.GameCollectionDao;

public class ListAllGamesEntry implements MenuEntry {

    private final String label;

    public ListAllGamesEntry(String label) {
        this.label = label;
    }

    @Override
    public void handle(MenuFormatter UI, GameCollectionDao dao) {
        UI.displayGames(dao.findAll());
    }

    @Override
    public String getLabel() {
        return label;
    }
}
