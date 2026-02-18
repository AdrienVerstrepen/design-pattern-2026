package fr.fges.services.entriesServices;

public class RecommendByPlayerCountService {
    private final GameCollectionDao dao;

    public RecommendByPlayerCountService(GameCollectionDao dao) {
        this.dao = dao;
    }

    public List<BoardGame> recommendByPlayerCount(int playerCount) {
        List<BoardGame> games = dao.findByNumberOfPlayers(playerCount);
        return dao.findAllInAlphabeticalOrder(games);
    }
}