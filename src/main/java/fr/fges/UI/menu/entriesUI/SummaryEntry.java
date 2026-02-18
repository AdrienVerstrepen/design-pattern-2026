package fr.fges.UI.menu.entriesUI;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.BoardGame;
import fr.fges.services.entriesServices.SummaryService;
import java.util.List;

public record SummaryEntry(String label, SummaryService service) implements MenuEntry {
    @Override
    public void handle(MenuInterface UI) {
        if (service.findAllGames().size() <= 3){
            UI.displayGames(service.findAllGames());
        } else {
            List<BoardGame> randomGames = service.makeSummary();
            UI.displayGames(randomGames);
        }
    }

    @Override
    public String label() {
        return label;
    }
}