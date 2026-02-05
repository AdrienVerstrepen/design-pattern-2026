package fr.fges.models.commands;

import fr.fges.repositories.GameCollectionDao;
import fr.fges.services.Random.RecommendationStrategy;

public record RecommendCommand (
        GameCollectionDao dao,
        RecommendationStrategy strategy
) {}
