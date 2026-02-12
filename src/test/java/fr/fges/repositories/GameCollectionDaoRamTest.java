package fr.fges.repositories;

import fr.fges.data.repositories.GameCollectionDao;
import fr.fges.data.repositories.GameCollectionDaoRam;

public class GameCollectionDaoRamTest extends GameCollectionDaoTest {
    @Override
    protected GameCollectionDao getDao() {
        return new GameCollectionDaoRam();
    }
}