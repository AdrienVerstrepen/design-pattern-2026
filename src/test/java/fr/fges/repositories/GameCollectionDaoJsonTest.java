package fr.fges.repositories;

public class GameCollectionDaoJsonTest extends GameCollectionDaoTest {
    @Override
    protected GameCollectionDao getDao() {
        return new GameCollectionDaoJson("unit-test.json");
    }
}
