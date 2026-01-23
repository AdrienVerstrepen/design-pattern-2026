package fr.fges;

import fr.fges.formatters.GameCollectionFormatter;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionRepository;
import fr.fges.services.GameCollectionLoader;
import fr.fges.services.GameCollectionSaver;
import java.util.List;

public class GameCollection {
    // Stockage des jeux
    private static final List<BoardGame> games = GameCollectionRepository.getGames();

    public static List<BoardGame> getGames() {
        return games;
    }

    // Gestion des jeux
    public static void addGame(BoardGame game) {
        games.add(game);
        GameCollectionSaver.saveToFile(storageFile, games);
    }

    public static void removeGame(BoardGame game) {
        games.remove(game);
        GameCollectionSaver.saveToFile(storageFile, games);
    }

    // Affichage des jeux

    public static void viewAllGames() {
        GameCollectionFormatter.viewAllGames(games);
    }

    // Chargement des jeux
    public static void loadFromFile() {
        GameCollectionLoader.loadFromFile(storageFile, games);
    }

    // Sauvegarde des jeux
    private static String storageFile = "";

    public static void setStorageFile(String file) {
        storageFile = file;
    }

}