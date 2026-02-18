package fr.fges.services.entriesServices;

import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.services.recommend.RandomNElementsStrategy;
import fr.fges.services.recommend.RecommendationStrategy;

import java.util.List;

public class SummaryService {
    private final GameCollectionDao dao;

    public SummaryService(GameCollectionDao dao) {
        this.dao = dao;
    }

    public List<BoardGame> findAllGames() {
        return dao.findAll();
    }

    public List<BoardGame> makeSummary(){
        RecommendationStrategy myStrategy = new RandomNElementsStrategy();
        return myStrategy.getNRandomGame(3, findAllGames());
    }
}
