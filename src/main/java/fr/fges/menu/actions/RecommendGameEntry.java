package fr.fges.menu.actions;

import fr.fges.formatters.MenuFormatter;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDao;
import fr.fges.services.Random.RecommendationStrategy;

import java.util.List;

public class RecommendGameEntry implements MenuEntry {
    private final String label;
    private final RecommendationStrategy strategy;

    public RecommendGameEntry(String label, RecommendationStrategy strategy) {
        this.label = label;
        this.strategy = strategy;
    }

    @Override
    public void handle(MenuFormatter UI, GameCollectionDao dao) {
         BoardGame game = strategy.getNRandomGame(1, dao).getFirst();
         UI.displayGame(game);
    }

    @Override
    public String getLabel() {
        return label;
    }
}
