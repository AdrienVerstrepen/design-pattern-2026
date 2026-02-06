package fr.fges.menu.actions;
import fr.fges.formatters.MenuInterface;
import fr.fges.repositories.GameCollectionDao;

public interface MenuEntry {
    void handle(MenuInterface UI, GameCollectionDao dao);
    String label();
}