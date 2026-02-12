package fr.fges.data.models;

public record BoardGame(
        String title,
        int minPlayers,
        int maxPlayers,
        String category
) {
}