package fr.fges.data.repositories.games;

public class GameCollectionDaoRamTest extends GameCollectionDaoTest {
    @Override
    protected GameCollectionDao getDao() {
        return new GameCollectionDaoRam();
    }
}