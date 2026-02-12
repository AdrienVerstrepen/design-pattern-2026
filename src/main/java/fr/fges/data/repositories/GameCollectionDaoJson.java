package fr.fges.data.repositories;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.fges.data.models.BoardGame;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameCollectionDaoJson implements GameCollectionDao {
    private final String filename;

    public GameCollectionDaoJson(String filename) {
        this.filename = filename;
    }

    @Override
    public boolean save(BoardGame newGame) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<BoardGame> savedGames = this.findAll();
            savedGames.add(newGame);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(this.filename), savedGames);
            return true;
        } catch (IOException e) {
            System.out.println("Error saving to JSON: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<BoardGame> findAll() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(this.filename);
            return  mapper.readValue(file, new TypeReference<>() {});
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

    @Override
    public List<BoardGame> findByNumberOfPlayers(int numberOfPlayers) {
        return findAll().stream().filter(game -> game.minPlayers() <= numberOfPlayers && game.maxPlayers() >= numberOfPlayers).toList();
    }

    @Override
    public Optional<BoardGame> findByTitle(String title) {
        return this.findAll().stream().filter(game -> game.title().equalsIgnoreCase(title)).findFirst();
    }

    @Override
    public List<BoardGame> findAllInAlphabeticalOrder(List<BoardGame> games) {
        return games.stream().sorted((jeu1, jeu2) -> jeu1.title().compareToIgnoreCase(jeu2.title())).toList();
    }
}