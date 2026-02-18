package fr.fges.UI.menu.entries;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.repositories.Games.GameCollectionDao;

public class ListAllGamesEntry implements MenuEntry {
    private final String label;

    public ListAllGamesEntry(String label) {
        this.label = label;
    }

    @Override
    public void handle(MenuInterface UI, GameCollectionDao dao) {
        UI.displayGames(dao.findAll());
    }

    @Override
    public String label() {
        return label;
    }
}