package fr.fges.models.commands;

import fr.fges.handlers.CommandHandler;

public interface Command {

    String getLabel();
    CommandHandler getHandler();
}
