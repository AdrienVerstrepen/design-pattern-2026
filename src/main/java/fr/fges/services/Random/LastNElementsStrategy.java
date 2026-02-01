package fr.fges.services.Random;

import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDAO;

import java.util.ArrayList;
import java.util.List;

public class LastNElementsStrategy implements RandomStrategy {

    @Override
    public List<BoardGame> getNRandomGame(int numberOfGames, GameCollectionDAO dao) {
        List<BoardGame> myGameCollection = dao.findAll();
        if (myGameCollection.isEmpty()) {
            return new ArrayList<>();
        }

        int limit = Math.min(numberOfGames, myGameCollection.size());

        List<BoardGame> mySelectedGames = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            mySelectedGames.add(myGameCollection.get(myGameCollection.size() - 1 -i ));
        }
        return mySelectedGames;
    }
}
