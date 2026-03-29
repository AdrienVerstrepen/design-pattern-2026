package fr.fges.services.exceptions;

public class InvalidPlayerCountException extends BoardGameException {
    public InvalidPlayerCountException() {
        super("Number of players must be greater than zero.");
    }
}