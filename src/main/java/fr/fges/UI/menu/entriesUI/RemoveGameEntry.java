package fr.fges.UI.menu.entriesUI;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.services.entriesServices.RemoveGameService;
import fr.fges.services.results.Result;

public record RemoveGameEntry(String label, RemoveGameService service) implements MenuEntry {
    @Override
    public void handle(MenuInterface UI) {
        String title = UI.getGameTitle();
        Result<Void, String> result = service.removeGame(title);

        if (result.isSuccess()) {
            UI.displayMessage("Board game removed successfully.");
        } else {
            UI.displayMessage(result.getError());
        }
    }

    @Override
    public String label() {
        return label;
    }
}