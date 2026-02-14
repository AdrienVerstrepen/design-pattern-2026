package fr.fges.services.factories;

import fr.fges.UI.menu.entries.*;
import fr.fges.data.repositories.HistoryDaoRam;
import fr.fges.services.recommend.RandomNElementsStrategy;

import java.util.ArrayList;
import java.util.List;

import static fr.fges.services.DateGestion.getWeekDay;
import static fr.fges.services.DateGestion.isWeekEnd;

public class MenuFactory {
    public static List<MenuEntry> create() {
        List<MenuEntry> menuEntries = new ArrayList<>();
        HistoryDaoRam history = new HistoryDaoRam();
        menuEntries.add(new AddGameEntry("Add Board Game", history));
        menuEntries.add(new RemoveGameEntry("Remove Board Game", history));
        menuEntries.add(new ListAllGamesEntry("List All Board Games"));
        menuEntries.add(new RecommendOneGameEntry("Recommend Game", new RandomNElementsStrategy()));
        if (isWeekEnd(getWeekDay())) {
            menuEntries.add(new SummaryEntry("View Summary (Weekend Special!)"));
        }
        menuEntries.add(new RecommendByPlayerCountEntry("Recommend games for a number of players"));
        menuEntries.add(new UndoLastActionEntry("Undo Last Action", history));
        menuEntries.add(new ExitEntry("Exit"));
        return menuEntries;
    }

}
