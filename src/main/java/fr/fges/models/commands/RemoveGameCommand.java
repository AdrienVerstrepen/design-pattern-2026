package fr.fges.models.commands;

import fr.fges.handlers.AddGameHandler;
import fr.fges.handlers.CommandHandler;
import fr.fges.handlers.RemoveGameHandler;

public class RemoveGameCommand implements Command {
    public final String title;
    public final int minPlayers;
    public final int maxPlayers;
    public final String category;
    private final RemoveGameHandler handler;
    private final String label;

    public RemoveGameCommand(String title, int minPlayers, int maxPlayers, String category, RemoveGameHandler handler, String label) {
        this.title = title;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.category = category;
        this.handler = handler;
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public CommandHandler getHandler() {
        return handler;
    }
}