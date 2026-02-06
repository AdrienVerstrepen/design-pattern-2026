package fr.fges.services.Recommendation;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDao;
import java.util.List;

public interface RecommendationByPlayerStrategy {
    List<BoardGame> getGames(GameCollectionDao dao);
}