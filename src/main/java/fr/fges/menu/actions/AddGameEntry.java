package fr.fges.menu.actions;
import fr.fges.formatters.MenuInterface;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDao;
import fr.fges.services.Verifications.BoardGameVerificator;

public record AddGameEntry(String label) implements MenuEntry {
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

        if (dao.save(new BoardGame(title, minPlayers, maxPlayers, category))) {
            UI.displayMessage("Board game added successfully.");
        } else {
            UI.displayMessage("An error occurred, please try again.");
        }
    }
}