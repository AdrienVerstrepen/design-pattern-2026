package fr.fges.services.exceptions;

public class SummaryGenerationException extends BoardGameException {
    public SummaryGenerationException() {
        super("Unable to generate a summary of games.");
    }
}
