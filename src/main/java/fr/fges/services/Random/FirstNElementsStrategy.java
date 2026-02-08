package fr.fges.services.Random;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDao;
import java.util.ArrayList;
import java.util.List;

public class FirstNElementsStrategy implements RecommendationStrategy {
    @Override
    public List<BoardGame> getNRandomGame(int numberOfGames, List<BoardGame> games) {
        if (games.isEmpty()) {
            return new ArrayList<>();
        }
        List<BoardGame> mySelectedGames = new ArrayList<>();
        for (int i = 0; i < games.size(); i++) {
            if (numberOfGames == i) {
                break;
            }
            mySelectedGames.add(games.get(i));
        }
        return mySelectedGames;
    }
}