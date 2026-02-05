package fr.fges.models.commands;

import fr.fges.handlers.AddGameHandler;
import fr.fges.handlers.CommandHandler;
import fr.fges.handlers.RemoveGameHandler;

public class AddGameCommand implements Command {

    public final String title;
    public final int minPlayers;
    public final int maxPlayers;
    public final String category;
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