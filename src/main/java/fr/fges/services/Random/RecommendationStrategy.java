package fr.fges.services.Random;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDAO;

import java.util.List;

public interface RecommendationStrategy {
    List<BoardGame> getNRandomGame(int numberOfGames, GameCollectionDAO dao);
}