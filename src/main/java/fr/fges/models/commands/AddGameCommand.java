package fr.fges.models;

public record AddGameCommand (
        String title,
        int minPlayers,
        int maxPlayers,
        String category
) {}