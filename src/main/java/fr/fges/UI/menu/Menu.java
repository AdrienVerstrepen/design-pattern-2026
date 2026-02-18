package fr.fges.UI.menu;
import fr.fges.UI.formatters.MenuFormatter;
import fr.fges.UI.menu.entriesUI.*;
import fr.fges.data.repositories.games.GameCollectionDao;
import java.util.List;

public class Menu {
    private final MenuFormatter formatter;

    public Menu(MenuFormatter formatter) {
        this.formatter = formatter;
    }

    public void handleMenu(List<MenuEntry> menuEntries) {
        formatter.displayMenu(menuEntries);
        int choice = formatter.getNumberFromUser("Please select an option (1-" + menuEntries.size() + "): ");
        while(choice > menuEntries.size()) {
            formatter.displayMessage("The number entered is invalid, please write a correct number.");
            choice = formatter.getNumberFromUser("Please select an option (1-" + menuEntries.size() + "): ");
        }
        formatter.displayMessage("");
        menuEntries.get(choice - 1).handle(formatter);
        formatter.displayMessage("");
    }
}