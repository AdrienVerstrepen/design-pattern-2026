package fr.fges.UI.menu.entriesUI;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.services.entriesServices.ListAllGamesService;

public record ListAllGamesEntry(String label, ListAllGamesService service) implements MenuEntry {
    @Override
    public void handle(MenuInterface UI) {
        UI.displayGames(service.findAllGames());
    }

    @Override
    public String label() {
        return label;
    }
}