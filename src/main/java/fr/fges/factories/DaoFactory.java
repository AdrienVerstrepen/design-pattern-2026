package fr.fges.factories;

import fr.fges.repositories.GameCollectionDAO;
import fr.fges.repositories.GameCollectionDAOCSV;
import fr.fges.repositories.GameCollectionDAOJSON;

public class DaoFactory {
    public static GameCollectionDAO create(String filename) {
        if (filename == null || !filename.contains(".")) {
            throw new IllegalArgumentException("Invalid filename: " + filename);
        }

        String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();

        return switch (extension) {
            case "json" -> new GameCollectionDAOJSON(filename);
            case "csv" -> new GameCollectionDAOCSV(filename);
            default -> throw new IllegalArgumentException("Unknown extension: " + extension);
        };
    }
}
