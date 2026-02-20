package fr.fges.UI.menu.entriesUI;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.data.repositories.history.HistoryDao;
import fr.fges.services.entriesServices.AddGameService;

public record AddGameEntry(String label, AddGameService service, HistoryDao history, GameCollectionDao dao) implements MenuEntry {
    @Override
    public void handle(MenuInterface UI) {
        UI.displayMessage("> " + label());
        String title = UI.getGameTitle();
        int minPlayers = UI.getNumberFromUser("Minimum Players: ");
        int maxPlayers = UI.getNumberFromUser("Maximum Players: ");
        String category = UI.getGameCategory();

        // Transmission au service
        String result = service.addGame(
                new BoardGame(title, minPlayers, maxPlayers, category),
                dao,
                history
                );

        UI.displayMessage(result);
    }
}