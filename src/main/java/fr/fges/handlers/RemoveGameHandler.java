package fr.fges.handlers;

import fr.fges.models.RemoveGameCommand;
import fr.fges.repositories.GameCollectionDAO;

public class RemoveGameHandler implements CommandHandler<RemoveGameCommand>{
    private final GameCollectionDAO dao;

    public RemoveGameHandler(GameCollectionDAO dao) {
        this.dao = dao;
    }

    @Override
    public void execute(RemoveGameCommand command) {
        dao.delete(command.title());
    }
}
