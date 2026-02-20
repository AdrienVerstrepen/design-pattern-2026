package fr.fges.services.entriesServices;

import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.services.recommend.RandomNElementsStrategy;
import fr.fges.services.recommend.RecommendationStrategy;

public class RecommendOneGameService {
    private final RecommendationStrategy strategy;

    public RecommendOneGameService(RecommendationStrategy strategy) {
        this.strategy = strategy;
    }

    public BoardGame recommendOneGame(int numberOfPlayers, GameCollectionDao gamesDao) {
        return strategy.getNRandomGame(1, gamesDao.findByNumberOfPlayers(numberOfPlayers)).getFirst();
    }
}
