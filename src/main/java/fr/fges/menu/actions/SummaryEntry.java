package fr.fges.menu.actions;

import fr.fges.formatters.MenuFormatter;
import fr.fges.repositories.GameCollectionDao;

public class SummaryEntry implements MenuEntry {
    private final String label;

    public SummaryEntry(String label) {
        this.label = label;
    }

    @Override
    public void handle(MenuFormatter UI, GameCollectionDao dao) {
        /*if (dao.findAll().size() <= 3){
            GameCollectionFormatter.viewAllGames(dao);
        } else {
            RecommendationStrategy myStrategy = new RandomNElementsStrategy();
            List<BoardGame> randomGames = myStrategy.getNRandomGame(3, dao);
            displayGames(randomGames);
        }*/
    }

    @Override
    public String getLabel() {
        return label;
    }
}
