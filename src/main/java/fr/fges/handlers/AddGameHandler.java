package fr.fges.handlers;

import fr.fges.models.AddGameCommand;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDAO;

public class AddGameHandler implements CommandHandler<AddGameCommand> {
    private final GameCollectionDAO dao;

    public AddGameHandler(GameCollectionDAO dao) {
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
