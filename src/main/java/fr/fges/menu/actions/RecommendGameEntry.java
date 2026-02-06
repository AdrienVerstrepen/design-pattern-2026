package fr.fges.menu.actions;

import fr.fges.formatters.MenuFormatter;
import fr.fges.repositories.GameCollectionDao;

public class RecommendGameEntry implements MenuEntry {
    private final String label;

    public RecommendGameEntry(String label) {
        this.label = label;
    }

    @Override
    public void handle(MenuFormatter UI, GameCollectionDao dao) {

    }

    @Override
    public String getLabel() {
        return label;
    }
}
