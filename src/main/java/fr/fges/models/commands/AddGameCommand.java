package fr.fges.models.commands;

public record AddGameCommand (
        String title,
        int minPlayers,
        int maxPlayers,
        String category
) {}