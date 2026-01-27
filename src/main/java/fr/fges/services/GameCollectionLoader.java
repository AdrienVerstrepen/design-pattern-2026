package fr.fges.services;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class GameCollectionLoader {
    public static void loadFromFile() {
        // This method loads the games contained in the storageFile defined in the GameCollectionSaver
        String storageFile = GameCollectionSaver.getStorageFile();
        File file = new File(storageFile);
        if (!file.exists()) {
            return;
        }

        if (storageFile.endsWith(".json")) {
            loadFromJson(storageFile);
        } else if (storageFile.endsWith(".csv")) {
            loadFromCsv(storageFile);
        }
    }

    private static void loadFromJson(String storageFile) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(storageFile);
            List<BoardGame> loadedGames = mapper.readValue(file, new TypeReference<List<BoardGame>>() {});
            GameCollectionRepository.getGames().clear();
            GameCollectionRepository.getGames().addAll(loadedGames);
        } catch (IOException e) {
            System.out.println("Error loading from JSON: " + e.getMessage());
        }
    }

    private static void loadFromCsv(String storageFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(storageFile))) {
            GameCollectionRepository.getGames().clear();
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // skip header
                }
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    BoardGame game = new BoardGame(
                            parts[0],
                            Integer.parseInt(parts[1]),
                            Integer.parseInt(parts[2]),
                            parts[3]
                    );
                    GameCollectionRepository.getGames().add(game);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading from CSV: " + e.getMessage());
        }
    }
}