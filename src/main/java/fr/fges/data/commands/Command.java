package fr.fges.data.commands;
import fr.fges.data.models.BoardGame;
import fr.fges.data.repositories.GameCollectionDao;

public interface Command {
    BoardGame getModifiedGame();
    void restore(GameCollectionDao dao);
}