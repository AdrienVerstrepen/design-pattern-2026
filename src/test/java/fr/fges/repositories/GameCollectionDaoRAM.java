package fr.fges.repositories;

public class GameCollectionDaoRAM extends GameCollectionDaoTest {

    @Override
    protected GameCollectionDAO getDao() {
        return new GameCollectionDAORAM();
    }
}
