package fr.fges.UI.menu.entriesUI;
import fr.fges.UI.formatters.MenuInterface;

public interface MenuEntry {
    void handle(MenuInterface UI);
    String label();
}