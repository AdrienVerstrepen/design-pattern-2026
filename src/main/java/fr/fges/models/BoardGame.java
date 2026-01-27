package fr.fges.models;

public record BoardGame(
        String title,
        int minPlayers,
        int maxPlayers,
        String category
) {
}