package fr.fges.models.commands;
import fr.fges.models.BoardGame;

public class RemoveGameCommand implements Command {
    private final BoardGame game;

    public RemoveGameCommand(BoardGame game) {
        this.game = game;
    }

    @Override
    public BoardGame getModifiedGame() {
        return null;
    }

    @Override
    public void restore() {

    }
}