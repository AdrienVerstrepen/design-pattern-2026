package fr.fges.repositories;

import fr.fges.data.repositories.GameCollectionDao;
import fr.fges.data.repositories.GameCollectionDaoCsv;

public class GameCollectionDaoCsvTest extends GameCollectionDaoTest {
    @Override
    protected GameCollectionDao getDao() {
        return new GameCollectionDaoCsv("unit-test.csv");
    }
}