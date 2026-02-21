package fr.fges.UI.menu.entriesUI;

import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.BoardGame;
import fr.fges.services.entriesServices.SummaryService;
import fr.fges.services.results.Result;
import java.util.List;

public record SummaryEntry(String label, SummaryService service) implements MenuEntry {
    @Override
    public void handle(MenuInterface UI) {
        Result<List<BoardGame>, String> result;
        if (service.findAllGames().isSuccess() && service.findAllGames().getValue().size() <= 3) {
            result = service.findAllGames();
        } else {
            result = service.makeSummary();
        }
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