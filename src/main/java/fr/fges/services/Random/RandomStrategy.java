package fr.fges.services.Random;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDAO;

import java.util.List;

public interface RandomStrategy {
    List<BoardGame> getNRandomGame(int numberOfGames, GameCollectionDAO dao);
}