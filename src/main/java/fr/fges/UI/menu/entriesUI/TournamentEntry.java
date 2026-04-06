package fr.fges.UI.menu.entriesUI;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.UI.menu.Menu;
import fr.fges.data.models.BoardGame;
import fr.fges.data.models.Player;
import fr.fges.services.entriesServices.TournamentService;
import fr.fges.services.factories.TournamentFormatFactory;
import fr.fges.services.results.Result;
import fr.fges.services.tournament.TournamentFormat;

import java.util.ArrayList;
import java.util.List;

public record TournamentEntry (String label, TournamentService service) implements MenuEntry {
    @Override
    public void handle(MenuInterface UI) {
        UI.displayMessage("=== Tournament Mode ===");
//        Refacto

        BoardGame chosenGame = handleGameSelection(UI);
        List<Player> players = handlePlayerNaming(UI);

        // Gestion des formats
        TournamentFormat chosenFormat = handleFormats(UI, players);

        // Gestion des matchs
        int N = service.obtainNumberOfMatches(chosenFormat);
        // N sera le nombre de matchs à réaliser, donné par le service
        List<Player> winners = new ArrayList<>();
        for (int i = 0; i < N; i++ ) {
            // récupérer les 2 joueurs qui doivent s'affronter depuis le service
            // récupérer le choix du gagnant
            // passer l'information au service
            List<Player> duo = service.getMatchParticipants(chosenFormat);
            Player winner = getWinner(duo.get(0), duo.get(1), UI);
            Player loser = (winner.equals(duo.get(0))) ? duo.get(1) : duo.get(0);
            service.registerWinner(winner, loser, chosenFormat);
        }

        Result<List<Player>, Exception> endResults = service.obtainResults(chosenFormat);

//        Result<List<Player>, Exception> result = service.playTournament(chosenFormat, players);



//        Refacto
//        Result<List<Player>, Exception> result = service.execute(UI);
        if (endResults.isSuccess()) {
            UI.displayMessage("=== Tournament Results ===");
            UI.displayPlayers(endResults.value());
        } else {
            UI.displayMessage("Error: " + endResults.error().getMessage());
        }
    }

    private BoardGame handleGameSelection (MenuInterface UI) {
        Result<List<BoardGame>, Exception> resultGames = service.findGames();
        if (!resultGames.isSuccess()) {
            UI.displayMessage(resultGames.error().getMessage());
        }
        List<BoardGame> foundGames = resultGames.value();
        UI.displayGames(foundGames);
        int selectedGameNumber = UI.getNumberFromUser("Select game (1-" + foundGames.size() + "): ");
        return foundGames.get(selectedGameNumber - 1);
    }

    private List<Player> handlePlayerNaming(MenuInterface UI) {
        int numberOfPlayers = UI.getNumberFromUser("Number of participants (3-8): ");
        while (numberOfPlayers < 3 || numberOfPlayers > 8) {
            UI.displayMessage("The number entered is invalid, please write a valid number (3-8).");
            numberOfPlayers = UI.getNumberFromUser("Number of participants (3-8): ");
        }
        List<String> playerNames = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            playerNames.add(UI.getUserInput("Enter player " + (i + 1) + " name: "));
        }
        return service.instantiatePlayers(playerNames);
    }

    private TournamentFormat handleFormats(MenuInterface UI, List<Player> players) {
        UI.displayMessage("Choose format:");
        List<TournamentFormat> formats = service.obtainFormats();
        for (int i = 0; i < formats.size(); i++) {
            UI.displayMessage((i + 1) + ". " + formats.get(i).label());
        }
        int chosenFormatIndex = UI.getNumberFromUser("Select format (1-" + formats.size() + "): ");
        while (chosenFormatIndex < 1 || chosenFormatIndex > formats.size()) {
            UI.displayMessage("Invalid format selection. Please choose a valid number.");
            chosenFormatIndex = UI.getNumberFromUser("Select format (1-" + formats.size() + "): ");
        }
        TournamentFormat chosenFormat = formats.get(chosenFormatIndex -1 );
        chosenFormat.setPlayers(players);
        return chosenFormat;
    }

    private Player getWinner(Player player1, Player player2, MenuInterface UI) {
        List<Player> players = List.of( player1, player2);
        UI.displayMessage("===" + player1.getName() + " VS " + player2.getName() + "===");
        int winner = 0;
        while (winner != 1 && winner != 2) {
            winner = UI.getNumberFromUser("Winner (1=" + player1.getName() + ", 2=" + player2.getName() + "): ");
            if (winner != 1 && winner != 2) {
                UI.displayMessage("Invalid input. Please enter 1 or 2.");
            }
        }
        return players.get(winner - 1);
    }
}