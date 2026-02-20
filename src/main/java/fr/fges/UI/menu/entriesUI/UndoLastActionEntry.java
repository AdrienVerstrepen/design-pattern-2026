package fr.fges.UI.menu.entriesUI;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.data.repositories.history.HistoryDao;
import fr.fges.services.entriesServices.UndoLastActionService;

import static fr.fges.services.verifications.BoardGameVerificator.isEmptyList;

public record UndoLastActionEntry (String label, UndoLastActionService service, HistoryDao history, GameCollectionDao dao) implements MenuEntry {
    @Override
    public void handle(MenuInterface UI) {

        UI.displayMessage(service.Undo(dao, history));


    }

}