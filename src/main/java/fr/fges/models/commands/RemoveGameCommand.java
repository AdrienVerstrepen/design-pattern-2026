package fr.fges.models.commands;

public record RemoveGameCommand (
        String title,
        int minPlayers,
        int maxPlayers,
        String category
) {}