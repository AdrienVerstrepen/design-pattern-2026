package fr.fges.repositories;

public class GameCollectionDaoJSON extends GameCollectionDaoTest {
    @Override
    protected GameCollectionDAO getDao() {
        return new GameCollectionDAOJSON("unit-test.json");
    }
}
