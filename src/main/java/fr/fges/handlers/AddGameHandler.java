package fr.fges.handlers;

import fr.fges.models.AddGameCommand;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDao;

public class AddGameHandler implements CommandHandler<AddGameCommand> {
    private final GameCollectionDao dao;

    public AddGameHandler(GameCollectionDao dao) {
        this.dao = dao;
    }

    @Override
    public void execute(AddGameCommand command) {
        BoardGame game = new BoardGame(
                command.title(),
                command.minPlayers(),
                command.maxPlayers(),
                command.category()
        );
        dao.save(game);
    }
}
