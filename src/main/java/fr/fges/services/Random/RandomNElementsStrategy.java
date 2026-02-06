package fr.fges.services.Random;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNElementsStrategy implements RecommendationStrategy {
    @Override
    public List<BoardGame> getNRandomGame(int numberOfGames, GameCollectionDao dao) {
        Random random = new Random();
        List<BoardGame> myGameCollection = dao.findAll();
        if (myGameCollection.isEmpty()) {
            return new ArrayList<>();
        }
        List<BoardGame> mySelectedGames = new ArrayList<>();
        for (int i = 0; i < numberOfGames; i++) {
            if (i > myGameCollection.size() - 1) {
                break;
            }
            mySelectedGames.add(myGameCollection.get(random.nextInt(myGameCollection.size())));
        }
        return mySelectedGames;
    }
}