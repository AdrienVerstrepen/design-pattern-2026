package fr.fges.menu.actions;
import fr.fges.formatters.MenuFormatter;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDao;
import fr.fges.services.Random.RandomNElementsStrategy;
import fr.fges.services.Random.RecommendationStrategy;
import java.util.List;

public class SummaryEntry implements MenuEntry {
    private final String label;

    public SummaryEntry(String label) {
        this.label = label;
    }

    @Override
    public void handle(MenuFormatter UI, GameCollectionDao dao) {
        if (dao.findAll().size() <= 3){
            UI.displayGames(dao.findAll());
        } else {
            RecommendationStrategy myStrategy = new RandomNElementsStrategy();
            List<BoardGame> randomGames = myStrategy.getNRandomGame(3, dao);
            UI.displayGames(randomGames);
        }
    }

    @Override
    public String label() {
        return label;
    }
}