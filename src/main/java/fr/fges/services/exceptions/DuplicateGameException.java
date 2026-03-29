package fr.fges.services.exceptions;

public class DuplicateGameException extends BoardGameException {
    public DuplicateGameException() {
        super("A game with the same title already exists !");
    }
}