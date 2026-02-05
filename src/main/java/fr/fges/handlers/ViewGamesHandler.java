package fr.fges.handlers;

import fr.fges.formatters.GameCollectionFormatter;
import fr.fges.models.commands.ViewGamesCommand;

public class ViewGamesHandler implements CommandHandler<ViewGamesCommand> {

    @Override
    public void execute(ViewGamesCommand command) {
        GameCollectionFormatter.viewAllGames(command.dao());
    }
}
