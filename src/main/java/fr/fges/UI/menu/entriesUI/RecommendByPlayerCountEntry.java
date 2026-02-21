package fr.fges.UI.menu.entriesUI;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.BoardGame;
import fr.fges.services.entriesServices.RecommendByPlayerCountService;
import java.util.List;
import fr.fges.services.results.Result;

public record RecommendByPlayerCountEntry(String label, RecommendByPlayerCountService service) implements MenuEntry {
    @Override
    public void handle(MenuInterface UI) {
        int playerCount = UI.getNumberFromUser("Number of players: ");
        Result<List<BoardGame>, String> result = service.recommendByPlayerCount(playerCount);
        if (result.isSuccess()) {
            UI.displayGames(result.value());
        } else {
            UI.displayMessage(result.error());
        }
    }
}