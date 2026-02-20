package fr.fges.UI.menu.entriesUI;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.services.recommend.RecommendationStrategy;

public record RecommendOneGameEntry (String label, RecommendationStrategy strategy, GameCollectionDao dao) implements MenuEntry {
    @Override
    public void handle(MenuInterface UI) {
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