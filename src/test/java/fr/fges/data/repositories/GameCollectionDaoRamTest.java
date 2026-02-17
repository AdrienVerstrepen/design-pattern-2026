package fr.fges.data.repositories;

import fr.fges.data.repositories.Games.GameCollectionDao;
import fr.fges.data.repositories.Games.GameCollectionDaoRam;

public class GameCollectionDaoRamTest extends GameCollectionDaoTest {
    @Override
    protected GameCollectionDao getDao() {
        return new GameCollectionDaoRam();
    }
}