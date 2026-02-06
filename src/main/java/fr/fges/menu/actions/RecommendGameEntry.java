package fr.fges.menu.actions;
import fr.fges.formatters.MenuInterface;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDao;
import fr.fges.services.Random.RecommendationStrategy;

public class RecommendGameEntry implements MenuEntry {
    private final String label;
    private final RecommendationStrategy strategy;

    public RecommendGameEntry(String label, RecommendationStrategy strategy) {
        this.label = label;
        this.strategy = strategy;
    }

    @Override
    public void handle(MenuInterface UI, GameCollectionDao dao) {
         BoardGame game = strategy.getNRandomGame(1, dao).getFirst();
         UI.displayMessage("Recommended game: ");
         UI.displayGame(game);
    }

    @Override
    public String label() {
        return label;
    }
}