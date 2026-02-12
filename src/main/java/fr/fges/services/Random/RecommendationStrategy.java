package fr.fges.services.Random;
import fr.fges.data.models.BoardGame;

import java.util.List;

public interface RecommendationStrategy {
    List<BoardGame> getNRandomGame(int numberOfGames, List<BoardGame> games);
}