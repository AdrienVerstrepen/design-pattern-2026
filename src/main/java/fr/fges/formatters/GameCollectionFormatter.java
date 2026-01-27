package fr.fges.formatters;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionRepository;
import java.util.Comparator;
import java.util.List;

public class GameCollectionFormatter {
    public static void viewAllGames() {
        // This method prints all games to the user interface
        List<BoardGame> games = GameCollectionRepository.getGames();
        if (games.isEmpty()) {
            System.out.println("No board games in collection.");
            return;
        }

        // Sort the games by their title alphabetically
        List<BoardGame> sortedGames = games.stream()
                .sorted(Comparator.comparing(BoardGame::title))
                .toList();

        for (BoardGame game : sortedGames) {
            System.out.println("Game: " + game.title() + " (" + game.minPlayers() + "-" + game.maxPlayers() + " players) - " + game.category());
        }
    }
}