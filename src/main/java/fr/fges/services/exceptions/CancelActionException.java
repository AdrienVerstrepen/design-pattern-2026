package fr.fges.services.exceptions;

public class CancelActionException extends BoardGameException {
    public CancelActionException() {
        super("Nothing to cancel.");
    }
}
