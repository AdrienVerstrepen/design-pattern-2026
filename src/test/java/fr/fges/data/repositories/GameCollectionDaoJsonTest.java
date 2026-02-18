package fr.fges.data.repositories;

import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.data.repositories.games.GameCollectionDaoJson;

public class GameCollectionDaoJsonTest extends GameCollectionDaoTest {
    @Override
    protected GameCollectionDao getDao() {
        return new GameCollectionDaoJson("unit-test.json");
    }
}