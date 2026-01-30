package fr.fges.services.Random;

import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionRepository;

import java.util.ArrayList;
import java.util.List;

public class LastNElementsStrategy implements RandomStrategy {

    @Override
    public List<BoardGame> getNRandomGame(int numberOfGames) {
        List<BoardGame> myGameCollection = GameCollectionRepository.getGames();
        List<BoardGame> mySelectedGames = new ArrayList<>();
        for (int i = 1; i < numberOfGames; i++) {
            mySelectedGames.add(myGameCollection.get(myGameCollection.size()-i));
        }
        return mySelectedGames;
    }
}
