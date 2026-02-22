package fr.fges.data.repositories.games;

public class GameCollectionDaoCsvTest extends GameCollectionDaoTest {
    @Override
    protected GameCollectionDao getDao() {
        return new GameCollectionDaoCsv("unit-test.csv");
    }
}