package fr.fges.services.Recommendation;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDao;
import java.util.ArrayList;
import java.util.List;

public class RecommendByPlayerCountStrategy implements RecommendationByPlayerStrategy {
    private final int playerCount;

    public RecommendByPlayerCountStrategy(int playerCount) {
        this.playerCount = playerCount;
    }

    @Override
    public List<BoardGame> getGames(GameCollectionDao dao) {
        List<BoardGame> games = dao.findAll();
        List<BoardGame> result = new ArrayList<>();
        for (BoardGame game : games) {
            if (game.minPlayers() <= playerCount && game.maxPlayers() >= playerCount) {
                result.add(game);
            }
        }
        return result;
    }
}