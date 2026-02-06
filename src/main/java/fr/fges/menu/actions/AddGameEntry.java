package fr.fges.menu.actions;

import fr.fges.formatters.MenuFormatter;
import fr.fges.handlers.AddGameHandler;
import fr.fges.handlers.CommandHandler;
import fr.fges.models.commands.AddGameCommand;

public class AddGameEntry implements MenuEntry {

    private final String label;

    public AddGameEntry(String label) {

        this.label = label;
    }

    @Override
    public void handle(MenuFormatter UI) {
        UI.displayMessage("> " + getLabel());
        UI.getUserInput(" ");
        // Récupération des inputs users
        // Création du jeu
        // Enregistrement dans la dao
    }

    @Override
    public String getLabel() {
        return label;
    }
}
