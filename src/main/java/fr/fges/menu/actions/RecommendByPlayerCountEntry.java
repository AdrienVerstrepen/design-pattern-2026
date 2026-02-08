package fr.fges.menu.actions;
import fr.fges.formatters.MenuInterface;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDao;
import java.util.List;

public record RecommendByPlayerCountEntry(String label) implements MenuEntry {
    @Override
    public void handle(MenuInterface UI, GameCollectionDao dao) {
        int playerCount = UI.getNumberFromUser("Number of players: ");
        List<BoardGame> games = dao.findByNumberOfPlayers(playerCount);
        games = dao.alphabeticalOrder(games);
        if (games.isEmpty()) {
            UI.displayMessage("There is no game for this number of players.");
        } else {
            UI.displayGames(games);
        }
    }
}