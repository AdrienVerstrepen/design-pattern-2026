package fr.fges.menu.actions;
import fr.fges.formatters.MenuInterface;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDao;
import fr.fges.services.Random.RecommendationStrategy;

public class RecommendOneGameEntry implements MenuEntry {
    private final String label;
    private final RecommendationStrategy strategy;

    public RecommendOneGameEntry(String label, RecommendationStrategy strategy) {
        this.label = label;
        this.strategy = strategy;
    }

    @Override
    public void handle(MenuInterface UI, GameCollectionDao dao) {
        int numberOfPlayers = UI.getNumberFromUser("How many players?: ");

        BoardGame game = strategy.getNRandomGame(1, dao.findByNumberOfPlayers(numberOfPlayers)).getFirst();
        UI.displayMessage("Recommended game: ");
        UI.displayGame(game);
    }

    @Override
    public String label() {
        return label;
    }
}