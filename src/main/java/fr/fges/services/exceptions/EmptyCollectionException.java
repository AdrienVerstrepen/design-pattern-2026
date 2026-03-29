package fr.fges.services.exceptions;

public class EmptyCollectionException extends BoardGameException {
    public EmptyCollectionException() {
        super("No games found in the collection.");
    }
}