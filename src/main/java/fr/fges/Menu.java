package fr.fges;

import fr.fges.formatters.MenuFormatter;
import fr.fges.menu.actions.*;
import fr.fges.repositories.GameCollectionDao;

import java.util.ArrayList;
import java.util.List;

import static fr.fges.services.DateGestion.getWeekDay;
import static fr.fges.services.DateGestion.isWeekEnd;

public class Menu {
    private final MenuFormatter formatter;
    private final GameCollectionDao dao;
    List<MenuEntry> menuEntries = new ArrayList<>();

    public Menu(MenuFormatter formatter, GameCollectionDao dao) {
        this.formatter = formatter;
        this.dao = dao;
        this.create();
    }

    void create() {
        menuEntries.add(new AddGameEntry("Add Board Game"));
        menuEntries.add(new RemoveGameEntry("Remove Board Game"));
        menuEntries.add(new ListAllGamesEntry("List All Board Games"));
        if (isWeekEnd(getWeekDay())) {
            menuEntries.add(new SummaryEntry("View Summary (Weekend Specia!"));
        }
        menuEntries.add(new UndoLastActionEntry("Undo Last Action"));
        menuEntries.add(new ExitEntry("Exit"));
    }

    public void handleMenu() {
        formatter.displayMenu(menuEntries);

        String input = formatter.getUserInput("");
        // Add verification function

        menuEntries.get(Integer.parseInt(input)).handle(formatter);
    }

}
