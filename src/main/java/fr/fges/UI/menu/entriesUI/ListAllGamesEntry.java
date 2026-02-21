package fr.fges.UI.menu.entriesUI;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.services.entriesServices.ListAllGamesService;
import fr.fges.services.results.Result;
import fr.fges.data.models.BoardGame;
import java.util.List;

public record ListAllGamesEntry(String label, ListAllGamesService service) implements MenuEntry {
    @Override
    public void handle(MenuInterface UI) {
        Result<List<BoardGame>, String> result = service.findAllGames();
        if (result.isSuccess()) {
            UI.displayGames(result.getValue());
        } else {
            UI.displayMessage(result.getError());
        }
    }

    @Override
    public String label() {
        return label;
    }
}