package fr.fges.repositories;

public class GameCollectionDaoCSV extends GameCollectionDaoTest {
    @Override
    protected GameCollectionDAO getDao() {
        return new GameCollectionDAOCSV("unit-test.csv");
    }
}
