package fr.fges.models.commands;

import fr.fges.repositories.GameCollectionDao;

public record ViewGamesCommand (
        GameCollectionDao dao
) {}
