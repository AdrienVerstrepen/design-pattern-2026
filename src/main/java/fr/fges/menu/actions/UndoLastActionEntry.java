package fr.fges.menu.actions;
import fr.fges.formatters.MenuInterface;
import fr.fges.models.commands.Command;
import fr.fges.repositories.GameCollectionDao;
import fr.fges.repositories.HistoryDao;

import static fr.fges.services.Verifications.BoardGameVerificator.isEmptyList;

public record UndoLastActionEntry (String label, HistoryDao history) implements MenuEntry {

    @Override
    public void handle(MenuInterface UI, GameCollectionDao dao) {
        if (isEmptyList(history.findAll())) {
            UI.displayMessage("nothing to cancel");
            return;
        }
        Command lastCommand = history.removeLast();
        lastCommand.restore(dao);
    }

}