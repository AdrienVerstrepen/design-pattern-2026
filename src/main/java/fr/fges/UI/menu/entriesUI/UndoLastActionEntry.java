package fr.fges.UI.menu.entriesUI;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.commands.AddGameCommand;
import fr.fges.data.commands.Command;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.data.repositories.history.HistoryDao;

import static fr.fges.services.verifications.BoardGameVerificator.isEmptyList;

public record UndoLastActionEntry (String label, HistoryDao history) implements MenuEntry {
    @Override
    public void handle(MenuInterface UI, GameCollectionDao dao) {
        if (isEmptyList(history.findAll())) {
            UI.displayMessage("nothing to cancel");
            return;
        }
        Command lastCommand = history.removeLast();
        lastCommand.restore(dao);
        if (lastCommand instanceof AddGameCommand) {
            UI.displayMessage("Undone : Removed " + lastCommand.getModifiedGame().title() + " from collection");
        } else {
            UI.displayMessage("Undone : Added " + lastCommand.getModifiedGame().title() + " to collection");
        }
    }

}