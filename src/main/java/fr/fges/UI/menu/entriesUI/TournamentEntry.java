package fr.fges.UI.menu.entriesUI;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.Player;
import fr.fges.services.entriesServices.TournamentService;
import fr.fges.services.results.Result;
import java.util.List;

public record TournamentEntry (String label, TournamentService service) implements MenuEntry {
    @Override
    public void handle(MenuInterface UI) {
        UI.displayMessage("=== Tournament Mode ===");
        Result<List<Player>, String> result = service.execute(UI);
        if (result.isSuccess()) {
            UI.displayMessage("=== Tournament Results ===");
            UI.displayPlayers(result.value());
        } else {
            UI.displayMessage("Error: " + result.error());
        }
    }
}