package fr.fges.data.commands;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.Games.GameCollectionDao;

public class RemoveGameCommand implements Command {
    private final BoardGame game;

    public RemoveGameCommand(BoardGame game) {
        this.game = game;
    }

    @Override
    public BoardGame getModifiedGame() {
        return game;
    }

    @Override
    public void restore(GameCollectionDao dao) {
        dao.save(new BoardGame(game.title(), game.minPlayers(), game.maxPlayers(), game.category()));
    }
}