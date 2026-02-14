package fr.fges.data.repositories;

public class GameCollectionDaoRamTest extends GameCollectionDaoTest {
    @Override
    protected GameCollectionDao getDao() {
        return new GameCollectionDaoRam();
    }
}