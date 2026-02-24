package fr.fges.services.factories;
import fr.fges.UI.menu.entriesUI.*;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.data.repositories.history.HistoryDao;
import fr.fges.services.entriesServices.*;
import fr.fges.services.recommend.RandomNElementsStrategy;
import java.util.ArrayList;
import java.util.List;
import static fr.fges.services.DateGestion.getWeekDay;
import static fr.fges.services.DateGestion.isWeekEnd;

public class MenuFactory {
    public static List<MenuEntry> create(HistoryDao historyDao, GameCollectionDao gamesDao) {
        List<MenuEntry> menuEntries = new ArrayList<>();
        RecommendByPlayerCountService recommendPlayerCountService = new RecommendByPlayerCountService(gamesDao);
        ListAllGamesService listGamesService = new ListAllGamesService(gamesDao);
        SummaryService summaryService = new SummaryService(gamesDao);

        menuEntries.add(
                new AddGameEntry(
                        "Add Board Game",
                        new AddGameService(gamesDao, historyDao)
                )
        );
        menuEntries.add(
                new RemoveGameEntry(
                        "Remove Board Game",
                        new RemoveGameService(gamesDao, historyDao)
                )
        );
        menuEntries.add(new ListAllGamesEntry("List All Board Games", listGamesService));
        menuEntries.add(
                new RecommendOneGameEntry(
                        "Recommend Game",
                        gamesDao,
                        new RecommendOneGameService(new RandomNElementsStrategy())
                )
        );
        if (isWeekEnd(getWeekDay())) {
            menuEntries.add(new SummaryEntry("View Summary (Weekend Special!)", summaryService));
        }
        menuEntries.add(new RecommendByPlayerCountEntry("Recommend games for a number of players", recommendPlayerCountService));
        menuEntries.add(
                new UndoLastActionEntry(
                        "Undo Last Action",
                        new UndoLastActionService(gamesDao, historyDao)
                )
        );
        menuEntries.add(
                new TournamentEntry(
                        "Tournament Mode",
                        new TournamentService(gamesDao)
                )
        );
        menuEntries.add(new ExitEntry("Exit"));
        return menuEntries;
    }
}