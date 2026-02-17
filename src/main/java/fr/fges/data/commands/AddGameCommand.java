package fr.fges.data.commands;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.Games.GameCollectionDao;

public class AddGameCommand implements Command {
    private final BoardGame game;

    public AddGameCommand(BoardGame game) {
        this.game = game;
    }

    @Override
    public BoardGame getModifiedGame() {
        return game;
    }

    @Override
    public void restore(GameCollectionDao dao) {
        dao.delete(game.title());
    }
}