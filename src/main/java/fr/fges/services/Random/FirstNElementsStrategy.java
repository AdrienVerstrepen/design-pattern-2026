package fr.fges.services.Random;

import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDAO;

import java.util.ArrayList;
import java.util.List;

public class FirstNElementsStrategy implements RandomStrategy {

    @Override
    public List<BoardGame> getNRandomGame(int numberOfGames, GameCollectionDAO dao) {
        List<BoardGame> myGameCollection = dao.findAll();
        if (myGameCollection.isEmpty()) {
            return new ArrayList<>();
        }
        List<BoardGame> mySelectedGames = new ArrayList<>();
        for (int i = 0; i < myGameCollection.size(); i++) {
            if (numberOfGames == i) {
                break;
            }
            mySelectedGames.add(myGameCollection.get(i));
        }
        return mySelectedGames;
    }
}
