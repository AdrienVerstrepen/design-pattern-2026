package fr.fges.services.factories;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.data.repositories.games.GameCollectionDaoCsv;
import fr.fges.data.repositories.games.GameCollectionDaoJson;

public class GamesDaoFactory {
    public static GameCollectionDao create(String filename) {
        if (filename == null || !filename.contains(".")) {
            throw new IllegalArgumentException("Invalid filename: " + filename);
        }
        String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        return switch (extension) {
            case "json" -> new GameCollectionDaoJson(filename);
            case "csv" -> new GameCollectionDaoCsv(filename);
            default -> throw new IllegalArgumentException("Unknown extension: " + extension);
        };
    }
}