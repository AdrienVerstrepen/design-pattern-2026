package fr.fges.services;
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
}