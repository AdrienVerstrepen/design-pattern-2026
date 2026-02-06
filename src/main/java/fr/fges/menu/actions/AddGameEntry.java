package fr.fges.menu.actions;

import fr.fges.formatters.MenuFormatter;
import fr.fges.handlers.AddGameHandler;
import fr.fges.handlers.CommandHandler;
import fr.fges.models.BoardGame;
import fr.fges.models.commands.AddGameCommand;
import fr.fges.repositories.GameCollectionDao;
import fr.fges.services.MenuLogic;

import static fr.fges.formatters.MenuFormatter.displayMessage;
import static fr.fges.services.MenuLogic.isValidString;

public class AddGameEntry implements MenuEntry {

    private final String label;

    public AddGameEntry(String label) {

        this.label = label;
    }

    @Override
    public void handle(MenuFormatter UI, GameCollectionDao dao) {
        UI.displayMessage("> " + getLabel());
        UI.getUserInput(" ");
        // Les vérifications doivent basculer dans l'UI car c'est de l'entrée / sortie
        /*String title = MenuLogic.verificationValidString(getUserInput("Title: "));
        while (!isValidString(title)) {
            title = MenuLogic.verificationValidString(getUserInput("Title: "));
        }
        String category = MenuLogic.verificationValidString(getUserInput("Category (e.g., fantasy, cooperative, family, strategy): "));
        int minPlayers = MenuLogic.verificationValidNumber(getUserInput("Minimum Players: "), this);
        int maxPlayers = MenuLogic.verificationValidNumber(getUserInput("Maximum Players: "), this);
        if (MenuLogic.isADuplicate(title, dao)) {
            displayMessage("A game with the same title already exists !");
            return;
        }
        if (dao.save(new BoardGame(title, minPlayers, maxPlayers, category))) {
            displayMessage("Board game added successfully.");
        } else {
            displayMessage("Game already exists.");
        }*/
        // Récupération des inputs users
        // Création du jeu
        // Enregistrement dans la dao
    }

    @Override
    public String getLabel() {
        return label;
    }
}
