package fr.fges.data.repositories.games;

public class GameCollectionDaoJsonTest extends GameCollectionDaoTest {
    @Override
    protected GameCollectionDao getDao() {
        return new GameCollectionDaoJson("unit-test.json");
    }
}