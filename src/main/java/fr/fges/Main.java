package fr.fges;
import fr.fges.services.factories.DaoFactory;
import fr.fges.UI.formatters.MenuFormatter;
import fr.fges.UI.menu.Menu;
import fr.fges.UI.menu.entriesUI.MenuEntry;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.services.factories.MenuFactory;

import java.util.List;

public class Main {
    Menu menu;
    GameCollectionDao dao;
    MenuFormatter UI;

    public Main(String[] args) {
        String storageFile = receiveArguments(args);
        verifyGivenFile(storageFile);
        this.dao = DaoFactory.create(storageFile);
        this.UI = new MenuFormatter();
        this.menu = new Menu(UI);
    }

    public static void main(String[] args) {
        Main app = new Main(args);
        app.launch();
    }

    public String receiveArguments(String[] arguments) {
        if (arguments.length < 1) {
            System.out.println("Usage: java -jar boardgamecollection.jar <storage-file>");
            System.out.println("Storage file must be .json or .csv");
            System.exit(1);
        }
        return arguments[0];
    }

    public static void verifyGivenFile(String storageFile) {
        if (!storageFile.endsWith(".json") && !storageFile.endsWith(".csv")) {
            System.out.println("Error: Storage file must have .json or .csv extension");
            System.exit(1);
        }
    }

    public void launch() {
        List<MenuEntry> entries = MenuFactory.create();
        while (true) {
            menu.handleMenu(entries);
        }
    }
}