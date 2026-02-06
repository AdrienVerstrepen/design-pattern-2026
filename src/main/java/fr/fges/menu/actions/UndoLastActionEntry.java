package fr.fges.menu.actions;
import fr.fges.formatters.MenuInterface;
import fr.fges.repositories.GameCollectionDao;

public record UndoLastActionEntry(String label) implements MenuEntry {
    @Override
    public void handle(MenuInterface UI, GameCollectionDao dao) {
    }
}