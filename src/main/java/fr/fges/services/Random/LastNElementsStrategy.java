package fr.fges.services.Random;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDao;
import java.util.ArrayList;
import java.util.List;

public class LastNElementsStrategy implements RecommendationStrategy {
    @Override
    public List<BoardGame> getNRandomGame(int numberOfGames, List<BoardGame> games) {
        if (games.isEmpty()) {
            return new ArrayList<>();
        }

        int limit = Math.min(numberOfGames, games.size());

        List<BoardGame> mySelectedGames = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            mySelectedGames.add(games.get(games.size() - 1 -i ));
        }
        return mySelectedGames;
    }
}