package fr.fges.services.exceptions;

public class GameStorageException extends BoardGameException {
    public GameStorageException() {
        super("An error occured while saving game collection state, please try again.");
    }
}
