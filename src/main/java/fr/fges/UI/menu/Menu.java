package fr.fges.UI.menu;
import fr.fges.UI.formatters.MenuFormatter;
import fr.fges.UI.menu.actions.*;
import fr.fges.data.repositories.GameCollectionDao;
import fr.fges.data.repositories.HistoryDaoRam;
import fr.fges.services.Random.RandomNElementsStrategy;
import java.util.ArrayList;
import java.util.List;
import static fr.fges.services.DateGestion.getWeekDay;
import static fr.fges.services.DateGestion.isWeekEnd;

public class Menu {
    private final MenuFormatter formatter;
    private final GameCollectionDao dao;

    public Menu(MenuFormatter formatter, GameCollectionDao dao) {
        this.formatter = formatter;
        this.dao = dao;
    }

    public void handleMenu(List<MenuEntry> menuEntries) {
        formatter.displayMenu(menuEntries);
        int choice = formatter.getNumberFromUser("Please select an option (1-" + menuEntries.size() + "): ");
        while(choice > menuEntries.size()) {
            formatter.displayMessage("The number entered is invalid, please write a correct number.");
            choice = formatter.getNumberFromUser("Please select an option (1-" + menuEntries.size() + "): ");
        }
        formatter.displayMessage("");
        menuEntries.get(choice - 1).handle(formatter, dao);
        formatter.displayMessage("");
    }
}