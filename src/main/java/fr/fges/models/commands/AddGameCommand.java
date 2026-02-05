package fr.fges.models.commands;

import fr.fges.handlers.AddGameHandler;
import fr.fges.handlers.CommandHandler;
import fr.fges.handlers.RemoveGameHandler;

public class AddGameCommand implements Command {

    private final String title;
    private final int minPlayers;
    private final int maxPlayers;
    private final String category;
    private final AddGameHandler handler;
    private final String label;


    public AddGameCommand(String title, int minPlayers, int maxPlayers, String category, AddGameHandler handler, String label) {
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