package fr.fges.UI.menu.entries;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.repositories.Games.GameCollectionDao;

public interface MenuEntry {
    void handle(MenuInterface UI, GameCollectionDao dao);
    String label();
}