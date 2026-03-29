package fr.fges.services.exceptions;

public class GameNotFoundException extends BoardGameException {
    public GameNotFoundException() {
        super("No board game found with that title.");
    }
}