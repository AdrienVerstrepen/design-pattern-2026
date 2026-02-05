package fr.fges.handlers;

import fr.fges.models.commands.RemoveGameCommand;
import fr.fges.repositories.GameCollectionDao;

public class RemoveGameHandler implements CommandHandler<RemoveGameCommand>{
    private final GameCollectionDao dao;

    public RemoveGameHandler(GameCollectionDao dao) {
        this.dao = dao;
    }

    @Override
    public void execute(RemoveGameCommand command) {
        dao.delete(command.title());
    }
}
