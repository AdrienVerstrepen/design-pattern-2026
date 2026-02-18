package fr.fges.services.factories;

import fr.fges.UI.menu.entriesUI.*;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.data.repositories.games.GameCollectionDaoRam;
import fr.fges.data.repositories.history.HistoryDaoRam;
import fr.fges.data.repositories.history.HistoryDao;
import fr.fges.services.entriesServices.ListAllGamesService;
import fr.fges.services.entriesServices.RecommendByPlayerCountService;
import fr.fges.services.entriesServices.SummaryService;
import fr.fges.services.recommend.RandomNElementsStrategy;

import java.util.ArrayList;
import java.util.List;

import static fr.fges.services.DateGestion.getWeekDay;
import static fr.fges.services.DateGestion.isWeekEnd;

public class MenuFactory {
    public static List<MenuEntry> create() {
        List<MenuEntry> menuEntries = new ArrayList<>();
        HistoryDao history = new HistoryDaoRam();
        GameCollectionDao gameDao = new GameCollectionDaoRam();
        RecommendByPlayerCountService recommendPlayerCountService = new RecommendByPlayerCountService(gameDao);
        ListAllGamesService listGamesService = new ListAllGamesService(gameDao);
        SummaryService summaryService = new SummaryService(gameDao);
        menuEntries.add(new AddGameEntry("Add Board Game", history));
        menuEntries.add(new RemoveGameEntry("Remove Board Game", history));
        menuEntries.add(new ListAllGamesEntry("List All Board Games", listGamesService));
        menuEntries.add(new RecommendOneGameEntry("Recommend Game", new RandomNElementsStrategy()));
        if (isWeekEnd(getWeekDay())) {
            menuEntries.add(new SummaryEntry("View Summary (Weekend Special!)", summaryService));
        }
        menuEntries.add(new RecommendByPlayerCountEntry("Recommend games for a number of players", recommendPlayerCountService));
        menuEntries.add(new UndoLastActionEntry("Undo Last Action", history));
        menuEntries.add(new ExitEntry("Exit"));
        return menuEntries;
    }
}