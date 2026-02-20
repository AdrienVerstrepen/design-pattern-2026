package fr.fges.UI.menu.entriesUI;

import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.services.entriesServices.TournamentService;

import java.util.List;

public record TournamentEntry (String label, GameCollectionDao gamesDao, TournamentService service) implements MenuEntry {
    @Override
    public void handle(MenuInterface UI) {
        // Afficher les jeux disponibles
        // Récupèrer le choix du jeu
        // Demander le nombre de participants
        // Demander le nom par participants
        // Demander le choix du format
        UI.displayMessage("=== Tournament Mode ===");
        List<BoardGame> twoPlayerGames = gamesDao.findByNumberOfPlayers(2);
        UI.displayGames(twoPlayerGames);
        int max = twoPlayerGames.size();
        UI.getNumberFromUser("Select game (1-" + max + "): ");
        int numberOfPlayers = UI.getNumberFromUser("Number of participants (3-8): ");

        service.execute(UI, numberOfPlayers);
    }
}
