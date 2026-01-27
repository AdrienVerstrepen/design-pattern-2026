package fr.fges.services;
import fr.fges.formatters.GameCollectionFormatter;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionRepository;

public class GameService {
    public static boolean removeGame(String title) {
        var games = GameCollectionRepository.getGames();
        for (BoardGame game : games) {
            if (game.title().equals(title)) {
                GameCollectionRepository.removeGame(game);
                return true;
            }
        }
        return false;
    }

    public static void listAllGames() {
        GameCollectionFormatter.viewAllGames();
    }

    public static void addGame(String title, Integer minPlayers, Integer maxPlayers, String category){
        BoardGame game = new BoardGame(title, minPlayers, maxPlayers, category);
        GameCollectionRepository.addGame(game);
        GameCollectionSaver.saveToFile(GameCollectionRepository.getGames());
    }
}