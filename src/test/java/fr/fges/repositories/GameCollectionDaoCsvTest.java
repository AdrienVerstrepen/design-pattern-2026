package fr.fges.repositories;

public class GameCollectionDaoCsvTest extends GameCollectionDaoTest {
    @Override
    protected GameCollectionDao getDao() {
        return new GameCollectionDaoCsv("unit-test.csv");
    }
}