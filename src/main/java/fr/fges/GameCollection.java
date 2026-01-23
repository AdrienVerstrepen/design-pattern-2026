//package fr.fges;
//
//import fr.fges.formatters.GameCollectionFormatter;
//import fr.fges.models.BoardGame;
//import fr.fges.repositories.GameCollectionRepository;
//import fr.fges.services.GameCollectionLoader;
//import fr.fges.services.GameCollectionSaver;
//import java.util.List;
//
//public class GameCollection {
////    // Stockage des jeux | Fait dans repository
//////    private static final List<BoardGame> games = GameCollectionRepository.getGames();
//////
//////    public static List<BoardGame> getGames() {
//////        return games;
//////    }
////
////    // Gestion des jeux | FAIT DANS Repository
////    public static void addGame(BoardGame game) {
////        GameCollectionRepository.addGame(game);
////        GameCollectionSaver.saveToFile(storageFile, GameCollectionRepository.getGames());
////    }
////
////    public static void removeGame(BoardGame game) {
////        GameCollectionRepository.removeGame(game);
////        GameCollectionSaver.saveToFile(storageFile, GameCollectionRepository.getGames());
////    }
////
////    // Affichage des jeux | FAIT DANS Formatter
////
////    public static void viewAllGames() {
////        GameCollectionFormatter.viewAllGames(GameCollectionRepository.getGames());
////    }
////
////    // Chargement des jeux | Fait dans Services / Loader
////    public static void loadFromFile() {
////        GameCollectionLoader.loadFromFile(storageFile, games);
////    }
////
////    // Sauvegarde des jeux | Fait dans Services / Saver
////    private static String storageFile = "";
////
////    public static void setStorageFile(String file) {
////        storageFile = file;
////    }
////
//}