package fr.fges.repositories;

import fr.fges.data.repositories.GameCollectionDao;
import fr.fges.data.repositories.GameCollectionDaoJson;

public class GameCollectionDaoJsonTest extends GameCollectionDaoTest {
    @Override
    protected GameCollectionDao getDao() {
        return new GameCollectionDaoJson("unit-test.json");
    }
}