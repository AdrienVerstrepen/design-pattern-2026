package fr.fges.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.fges.models.BoardGame;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameCollectionDAOJSON implements GameCollectionDAO {

    private final String filename;

    public GameCollectionDAOJSON(String filename) {
        this.filename = filename;
    }

    @Override
    public void save(BoardGame game) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<BoardGame> savedGames = this.findAll();
            savedGames.add(game);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(this.filename), savedGames);
        } catch (IOException e) {
            System.out.println("Error saving to JSON: " + e.getMessage());
        }
    }

    @Override
    public List<BoardGame> findAll() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(this.filename);
            return  mapper.readValue(file, new TypeReference<List<BoardGame>>() {});
        } catch (IOException e) {
            System.out.println("Error loading from JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean delete(String title) {
        try {
            List<BoardGame> savedGames = this.findAll();
            savedGames.removeIf(game -> game.title().equals(title));
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(this.filename), savedGames);
            // appel au formatter
            return true;
        } catch (IOException e) {
            System.out.println("Error saving to JSON: " + e.getMessage());
            return false;
        }
    }

}
