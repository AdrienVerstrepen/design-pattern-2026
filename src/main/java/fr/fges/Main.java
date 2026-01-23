package fr.fges;

import fr.fges.services.MenuService;

public class Main {
    public static void main(String[] args) {
        String storageFile = ArgumentReceiver(args);
        ExtensionVerification(storageFile);
        AllocateMemory(storageFile);
        LaunchApplication();
    }

    public static String ArgumentReceiver(String[] arguments) {
        if (arguments.length < 1) {
            System.out.println("Usage: java -jar boardgamecollection.jar <storage-file>");
            System.out.println("Storage file must be .json or .csv");
            System.exit(1);
        }
        return arguments[0];
    }

    public static void ExtensionVerification(String storageFile) {
        if (!storageFile.endsWith(".json") && !storageFile.endsWith(".csv")) {
            System.out.println("Error: Storage file must have .json or .csv extension");
            System.exit(1);
        }
    }

    public static void AllocateMemory(String storageFile) {
        GameCollection.setStorageFile(storageFile);
        GameCollection.loadFromFile();
        System.out.println("Using storage file: " + storageFile);
    }


    public static void LaunchApplication() {
        while (true) {
            MenuService.handleMenu();
        }
    }
}
