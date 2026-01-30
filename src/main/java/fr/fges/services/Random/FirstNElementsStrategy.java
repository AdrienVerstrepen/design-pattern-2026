package fr.fges.services.Random;

import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionRepository;

import java.util.ArrayList;
import java.util.List;

public class FirstNElementsStrategy implements RandomStrategy {

    @Override
    public List<BoardGame> getNRandomGame(int numberOfGames) {
        List<BoardGame> myGameCollection = GameCollectionRepository.getGames();
        List<BoardGame> mySelectedGames = new ArrayList<>();
        for (int i = 0; i < numberOfGames; i++) {
            mySelectedGames.add(myGameCollection.get(i));
        }
        return mySelectedGames;
    }
}
