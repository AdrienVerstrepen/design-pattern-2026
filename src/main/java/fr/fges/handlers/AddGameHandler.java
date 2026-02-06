package fr.fges.handlers;

import fr.fges.models.commands.AddGameCommand;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDao;

public class AddGameHandler implements CommandHandler {
    private final GameCollectionDao dao;
    private final AddGameCommand command;

    public AddGameHandler(GameCollectionDao dao, AddGameCommand command) {
        this.dao = dao;
        this.command = command;
    }

    @Override
    public void execute() {
        BoardGame game = new BoardGame(
                command.title,
                command.minPlayers,
                command.maxPlayers,
                command.category
        );
        dao.save(game);
    }
}
