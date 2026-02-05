package fr.fges.handlers;

import fr.fges.models.commands.RemoveGameCommand;
import fr.fges.repositories.GameCollectionDao;

public class RemoveGameHandler implements CommandHandler {
    private final GameCollectionDao dao;
    private final RemoveGameCommand command;

    public RemoveGameHandler(GameCollectionDao dao, RemoveGameCommand command) {
        this.dao = dao;
        this.command = command;
    }

    @Override
    public void execute() {
        dao.delete(command.title);
    }
}
