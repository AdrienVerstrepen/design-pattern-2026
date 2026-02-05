package fr.fges.handlers;

import fr.fges.formatters.MenuFormatter;
import fr.fges.models.BoardGame;
import fr.fges.models.commands.RecommendCommand;
import java.util.List;

public class RecommendHandler implements CommandHandler{


    @Override
    public void execute() {
        int size = command.dao().findAll().size();
        List<BoardGame> recommendGames = command.strategy().getNRandomGame(size, command.dao());
        MenuFormatter.displayGames(recommendGames);
    }
}
