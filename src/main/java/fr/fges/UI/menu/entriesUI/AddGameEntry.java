package fr.fges.UI.menu.entriesUI;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.BoardGame;
import fr.fges.services.entriesServices.AddGameService;
import fr.fges.services.results.Result;

public record AddGameEntry(String label, AddGameService service) implements MenuEntry {
    @Override
    public void handle(MenuInterface UI) {
        UI.displayMessage("> " + label());
        String title = UI.getGameTitle();
        int minPlayers = UI.getNumberFromUser("Minimum Players: ");
        int maxPlayers = UI.getNumberFromUser("Maximum Players: ");
        String category = UI.getGameCategory();
        BoardGame game = new BoardGame(title, minPlayers, maxPlayers, category);
        Result<Void, String> result = service.addGame(game);
        if (result.isSuccess()) {
            UI.displayMessage("Board game added successfully.");
        } else {
            UI.displayMessage(result.getError());
        }
    }
}