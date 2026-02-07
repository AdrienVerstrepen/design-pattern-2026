package fr.fges.services.Random;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNElementsStrategy implements RecommendationStrategy {
    @Override
    public List<BoardGame> getNRandomGame(int numberOfGames, List<BoardGame> games) {
        Random random = new Random();
        if (games.isEmpty()) {
            return new ArrayList<>();
        }
        List<BoardGame> mySelectedGames = new ArrayList<>();
        for (int i = 0; i < numberOfGames; i++) {
            if (i > games.size() - 1) {
                break;
            }
            mySelectedGames.add(games.get(random.nextInt(games.size())));
        }
        return mySelectedGames;
    }
}