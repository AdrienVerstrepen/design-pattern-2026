package fr.fges;
import fr.fges.factories.DaoFactory;
import fr.fges.formatters.MenuFormatter;
import fr.fges.handlers.CommandHandler;
import fr.fges.repositories.*;
import fr.fges.services.MenuService;

import java.util.List;

import static fr.fges.formatters.MenuFormatter.displayMessage;

public class Main {
    MenuService menu;
    GameCollectionDao dao;
    MenuFormatter UI;
    List<CommandHandler> menuCommands;

    public Main(String[] args) {
        String storageFile = receiveArguments(args);
        this.dao = DaoFactory.create(storageFile);
        this.UI = new MenuFormatter();
        this.menu = new MenuService(dao, UI);
        // Dynamically build the menuCommands ArrayList
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

//    public static void ExtensionVerification(String storageFile) {
//        if (!storageFile.endsWith(".json") && !storageFile.endsWith(".csv")) {
//            System.out.println("Error: Storage file must have .json or .csv extension");
//            System.exit(1);
//        }
//    }

    public void launch() {
        while (true) {
            menu.handleMenu();
        }
    }
}