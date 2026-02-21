package fr.fges.services.entriesServices;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.Player;
import fr.fges.services.factories.TournamentFormatFactory;
import fr.fges.services.results.Result;
import fr.fges.services.results.Success;
import fr.fges.services.tournament.TournamentFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TournamentService {
    public Result<List<Player>, String> execute(MenuInterface UI) {
        List<Player> players = getPlayers(UI);
        TournamentFormat format = selectFormat(UI);
        List<Player> endResults = playTournament(format, players);
        List<Player> sortedResults = sortResults(endResults);
        return new Success<>(sortedResults);
    }

    private List<Player> getPlayers(MenuInterface UI) {
        int numberOfPlayers = getNumberOfParticipants(UI);
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            String playerName = getPlayerName(UI, i);
            players.add(new Player(playerName, 0, 0));
        }
        return players;
    }

    private int getNumberOfParticipants(MenuInterface UI) {
        int numberOfPlayers = UI.getNumberFromUser("Number of participants (3-8): ");
        while (numberOfPlayers < 3 || numberOfPlayers > 8) {
            UI.displayMessage("The number entered is invalid, please write a valid number (3-8).");
            numberOfPlayers = UI.getNumberFromUser("Number of participants (3-8): ");
        }
        return numberOfPlayers;
    }

    private String getPlayerName(MenuInterface UI, int index) {
        return UI.getUserInput("Enter player " + (index + 1) + " name: ");
    }

    private TournamentFormat selectFormat(MenuInterface UI) {
        UI.displayMessage("Choose format:");
        List<TournamentFormat> formats = TournamentFormatFactory.create(UI);
        for (int i = 0; i < formats.size(); i++) {
            UI.displayMessage((i + 1) + ". " + formats.get(i).label());
        }
        int chosenFormat = UI.getNumberFromUser("Select format (1-" + formats.size() + "): ");
        while (chosenFormat < 1 || chosenFormat > formats.size()) {
            UI.displayMessage("Invalid format selection. Please choose a valid number.");
            chosenFormat = UI.getNumberFromUser("Select format (1-" + formats.size() + "): ");
        }
        return formats.get(chosenFormat - 1);
    }

    private List<Player> playTournament(TournamentFormat format, List<Player> players) {
        format.setPlayers(players);
        return format.playTournament();
    }

    private List<Player> sortResults(List<Player> players) {
        players.sort(Comparator.comparingInt(Player::getPoints).reversed());
        return players;
    }
}