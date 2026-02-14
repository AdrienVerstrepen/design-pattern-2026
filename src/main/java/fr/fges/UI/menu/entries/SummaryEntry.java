package fr.fges.UI.menu.entries;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.GameCollectionDao;
import fr.fges.services.Random.RandomNElementsStrategy;
import fr.fges.services.Random.RecommendationStrategy;
import java.util.List;

public class SummaryEntry implements MenuEntry {
    private final String label;

    public SummaryEntry(String label) {
        this.label = label;
    }

    @Override
    public void handle(MenuInterface UI, GameCollectionDao dao) {
        if (dao.findAll().size() <= 3){
            UI.displayGames(dao.findAll());
        } else {
            RecommendationStrategy myStrategy = new RandomNElementsStrategy();
            List<BoardGame> randomGames = myStrategy.getNRandomGame(3, dao.findAll());
            UI.displayGames(randomGames);
        }
    }

    @Override
    public String label() {
        return label;
    }
}