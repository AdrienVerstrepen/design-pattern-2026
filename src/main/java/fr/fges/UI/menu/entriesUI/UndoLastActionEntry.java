package fr.fges.UI.menu.entriesUI;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.commands.AddGameCommand;
import fr.fges.data.commands.Command;
import fr.fges.services.entriesServices.UndoLastActionService;
import fr.fges.services.results.Result;

public record UndoLastActionEntry (String label, UndoLastActionService service) implements MenuEntry {
    @Override
    public void handle(MenuInterface UI) {
        Result<Command, Exception> result = service.undo();
        if (result.isSuccess()) {
            if (result.value() instanceof AddGameCommand) {
                UI.displayMessage("Undone : Removed " + result.value().getModifiedGame().title() + " from collection");
            } else {
                UI.displayMessage("Undone : Added " + result.value().getModifiedGame().title() + " to collection");
            }
        } else {
            UI.displayMessage(result.error().getMessage());
        }
    }
}