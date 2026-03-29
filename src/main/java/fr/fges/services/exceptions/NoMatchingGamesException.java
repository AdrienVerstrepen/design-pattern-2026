package fr.fges.services.exceptions;

public class NoMatchingGamesException extends BoardGameException {
    public NoMatchingGamesException() {
        super("No games available for this number of players");
    }
}