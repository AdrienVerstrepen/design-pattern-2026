package fr.fges.menu.actions;

import fr.fges.formatters.MenuFormatter;
import fr.fges.handlers.AddGameHandler;
import fr.fges.handlers.CommandHandler;
import fr.fges.models.BoardGame;
import fr.fges.models.commands.AddGameCommand;
import fr.fges.repositories.GameCollectionDao;
import fr.fges.services.Verifications.BoardGameVerificator;

public class AddGameEntry implements MenuEntry {

    private final String label;

    public AddGameEntry(String label) {

        this.label = label;
    }

    @Override
    public void handle(MenuFormatter UI, GameCollectionDao dao) {
        UI.displayMessage("> " + getLabel());
        String title = UI.getGameTitle();
        int minPlayers = UI.getMinimumPlayers();
        int maxPlayers = UI.getMaximumPlayers();
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

    @Override
    public String getLabel() {
        return label;
    }
}
