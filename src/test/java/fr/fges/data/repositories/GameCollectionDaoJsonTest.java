package fr.fges.data.repositories;

import fr.fges.data.repositories.Games.GameCollectionDao;
import fr.fges.data.repositories.Games.GameCollectionDaoJson;

public class GameCollectionDaoJsonTest extends GameCollectionDaoTest {
    @Override
    protected GameCollectionDao getDao() {
        return new GameCollectionDaoJson("unit-test.json");
    }
}