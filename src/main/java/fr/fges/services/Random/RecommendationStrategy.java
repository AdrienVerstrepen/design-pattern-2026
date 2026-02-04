package fr.fges.services.Random;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDao;

import java.util.List;

public interface RecommendationStrategy {
    List<BoardGame> getNRandomGame(int numberOfGames, GameCollectionDao dao);
}