package fr.fges.UI.menu.entries;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.BoardGame;
import fr.fges.data.commands.AddGameCommand;
import fr.fges.data.repositories.Games.GameCollectionDao;
import fr.fges.data.repositories.History.HistoryDao;
import fr.fges.services.Verifications.BoardGameVerificator;

public record AddGameEntry(String label, HistoryDao history) implements MenuEntry {
    @Override
    public void handle(MenuInterface UI, GameCollectionDao dao) {
        UI.displayMessage("> " + label());
        String title = UI.getGameTitle();
        int minPlayers = UI.getNumberFromUser("Minimum Players: ");
        int maxPlayers = UI.getNumberFromUser("Maximum Players: ");
        String category = UI.getGameCategory();
        if (BoardGameVerificator.isADuplicate(title, dao)) {
            UI.displayMessage("A game with the same title already exists !");
            return;
        }
        BoardGame game = new BoardGame(title, minPlayers, maxPlayers, category);

        if (dao.save(game)) {
            history.saveModification(new AddGameCommand(game));
            UI.displayMessage("Board game added successfully.");
        } else {
            UI.displayMessage("An error occurred, please try again.");
        }
    }
}