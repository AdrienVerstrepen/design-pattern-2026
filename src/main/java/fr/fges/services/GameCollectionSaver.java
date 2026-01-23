package fr.fges.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.fges.models.BoardGame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GameCollectionSaver {

    public static void saveToFile(String storageFile, List<BoardGame> games) {
        if (storageFile.endsWith(".json")) {
            saveToJson(storageFile, games);
        } else if (storageFile.endsWith(".csv")) {
            saveToCsv(storageFile, games);
        }
    }

    private static void saveToJson(String storageFile, List<BoardGame> games) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(storageFile), games);
        } catch (IOException e) {
            System.out.println("Error saving to JSON: " + e.getMessage());
        }
    }

    private static void saveToCsv(String storageFile, List<BoardGame> games) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(storageFile))) {
            writer.write("title,minPlayers,maxPlayers,category");
            writer.newLine();
            for (BoardGame game : games) {
                writer.write(game.title() + "," + game.minPlayers() + "," + game.maxPlayers() + "," + game.category());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to CSV: " + e.getMessage());
        }
    }

}
