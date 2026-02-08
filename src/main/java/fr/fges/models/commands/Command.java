package fr.fges.models.commands;
import fr.fges.models.BoardGame;
import fr.fges.repositories.GameCollectionDao;

public interface Command {
    BoardGame getModifiedGame();
    void restore(GameCollectionDao dao);
}