package fr.fges.random;

import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDAO;
import fr.fges.repositories.GameCollectionDAORAM;
import fr.fges.repositories.GameCollectionRepository;

import static org.junit.jupiter.api.Assertions.*;
import fr.fges.services.Random.*;

import java.util.ArrayList;
import java.util.List;

public class RandomTests {
    void testFirstNElementsNegative() {
        int numberOfGames = -2;
        RecommendationStrategy strategy = new FirstNElementsStrategy();
        GameCollectionDAO dao = new GameCollectionDAORAM();

        List<BoardGame> result = strategy.getNRandomGame(numberOfGames, dao);

        assertTrue(result.isEmpty());
    }

    void testFirstNElementsPositive() {
        int numberOfGames = 2;
        RecommendationStrategy strategy = new FirstNElementsStrategy();
        GameCollectionDAO dao = new GameCollectionDAORAM();

        List<BoardGame> result = strategy.getNRandomGame(numberOfGames, dao);

        assertEquals(2, result.size());
    }

    public List<BoardGame> getNRandomGame(int numberOfGames) {
        List<BoardGame> myGameCollection = GameCollectionRepository.getGames();
        List<BoardGame> mySelectedGames = new ArrayList<>();
        for (int i = 0; i < numberOfGames; i++) {
            mySelectedGames.add(myGameCollection.get(i));
        }
        return mySelectedGames;
    }
}
