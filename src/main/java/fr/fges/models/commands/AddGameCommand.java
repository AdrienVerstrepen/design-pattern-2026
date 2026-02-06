package fr.fges.models.commands;

import fr.fges.models.BoardGame;

public class AddGameCommand implements Command {
    private final BoardGame game;

    public AddGameCommand(BoardGame game) {
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