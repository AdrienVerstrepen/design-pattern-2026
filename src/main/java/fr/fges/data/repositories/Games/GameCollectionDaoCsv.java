package fr.fges.data.repositories.Games;
import fr.fges.data.models.BoardGame;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameCollectionDaoCsv implements GameCollectionDao {
    private final String filename;

    public GameCollectionDaoCsv(String filename) {
        this.filename = filename;
    }

    @Override
    public boolean save(BoardGame newGame) {
        List<BoardGame> savedGames = this.findAll();
        savedGames.add(newGame);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("title,minPlayers,maxPlayers,category");
            writer.newLine();
            for (BoardGame game : savedGames) {
                writer.write(game.title() + "," + game.minPlayers() + "," + game.maxPlayers() + "," + game.category());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error saving to CSV: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<BoardGame> findAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            List<BoardGame> savedGames = new ArrayList<>();
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
                    savedGames.add(game);
                }
            }
            return savedGames;
        } catch (IOException e) {
            System.out.println("Error loading from CSV: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean delete(String title) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            List<BoardGame> savedGames = this.findAll();
            savedGames.removeIf(game -> game.title().equals(title));
            writer.write("title,minPlayers,maxPlayers,category");
            writer.newLine();
            for (BoardGame game : savedGames) {
                writer.write(game.title() + "," + game.minPlayers() + "," + game.maxPlayers() + "," + game.category());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error loading from CSV: " + e.getMessage());
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