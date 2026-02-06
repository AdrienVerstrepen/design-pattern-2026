package fr.fges.models.commands;

import fr.fges.models.BoardGame;

public interface Command {
    BoardGame getModifiedGame();
    void restore();
}