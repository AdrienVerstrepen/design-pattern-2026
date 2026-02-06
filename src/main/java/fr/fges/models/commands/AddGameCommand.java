package fr.fges.models.commands;
import fr.fges.models.BoardGame;

public class AddGameCommand implements Command {
    public AddGameCommand(BoardGame game) {
    }

    @Override
    public BoardGame getModifiedGame() {
        return null;
    }

    @Override
    public void restore() {

    }
}