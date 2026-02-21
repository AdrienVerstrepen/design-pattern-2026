package fr.fges.UI.menu.entriesUI;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.services.entriesServices.UndoLastActionService;

public record UndoLastActionEntry (String label, UndoLastActionService service) implements MenuEntry {
    @Override
    public void handle(MenuInterface UI) {
        UI.displayMessage(service.Undo());
    }
}