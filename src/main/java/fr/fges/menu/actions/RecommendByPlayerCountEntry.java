package fr.fges.menu.actions;
import fr.fges.formatters.MenuFormatter;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDao;
import fr.fges.services.Recommendation.RecommendByPlayerCountStrategy;
import fr.fges.services.Recommendation.RecommendationByPlayerStrategy;
import java.util.List;

public class RecommendByPlayerCountEntry implements MenuEntry {
    private final String label;

    public RecommendByPlayerCountEntry(String label) {
        this.label = label;
    }

    @Override
    public void handle(MenuFormatter UI, GameCollectionDao dao) {
        int playerCount = UI.getNumberFromUser("Number of players");
        RecommendationByPlayerStrategy strategy = new RecommendByPlayerCountStrategy(playerCount);
        List<BoardGame> games = strategy.getGames(dao);
        if (games.isEmpty()) {
            UI.displayMessage("There is no game for this number of players.");
        } else {
            UI.displayGames(games);
        }
    }

    @Override
    public String getLabel() {
        return label;
    }
}