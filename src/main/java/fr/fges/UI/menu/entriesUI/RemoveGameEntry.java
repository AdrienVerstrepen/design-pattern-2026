package fr.fges.UI.menu.entriesUI;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.BoardGame;
import fr.fges.data.commands.RemoveGameCommand;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.data.repositories.history.HistoryDao;
import fr.fges.services.entriesServices.RemoveGameService;

import java.util.Optional;

public record RemoveGameEntry (String label, HistoryDao historyDao, GameCollectionDao gamesDao,RemoveGameService service) implements MenuEntry {

    @Override
    public void handle(MenuInterface UI) {
        String title = UI.getGameTitle();
        String result = service.removeGame(title, historyDao, gamesDao);
        UI.displayMessage(result);
    }

    @Override
    public String label() {
        return label;
    }
}