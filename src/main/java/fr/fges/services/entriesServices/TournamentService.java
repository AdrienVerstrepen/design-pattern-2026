package fr.fges.services.entriesServices;

import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.Player;
import fr.fges.services.factories.TournamentFormatFactory;
import fr.fges.services.tournament.TournamentFormat;

import java.util.ArrayList;
import java.util.List;

public class TournamentService {
    public void execute(MenuInterface UI, int NumberOfPlayers) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < NumberOfPlayers - 1; i++) {
            String playerName = UI.getUserInput("Enter player " + i + " name: ");
            players.add(new Player(playerName, 0, 0));
        }
        UI.displayMessage("Choose format:");

        List<TournamentFormat> formats = TournamentFormatFactory.create(UI);
        for (int i = 0; i < formats.size() - 1; i++) {
            UI.displayMessage(i + ". " + formats.get(i).label());
        }

        int chosenFormat = UI.getNumberFromUser("Select format (1-" + formats.size() + "): ");
        TournamentFormat format = formats.get(chosenFormat - 1);
        format.setPlayers(players);
        List<Player> endResults = format.playTournament();
        UI.displayMessage("=== Tournament Results ===");
        UI.displayPlayers(endResults);
    }
}