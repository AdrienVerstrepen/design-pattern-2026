package fr.fges.data.repositories;

import fr.fges.data.repositories.Games.GameCollectionDao;
import fr.fges.data.repositories.Games.GameCollectionDaoCsv;

public class GameCollectionDaoCsvTest extends GameCollectionDaoTest {
    @Override
    protected GameCollectionDao getDao() {
        return new GameCollectionDaoCsv("unit-test.csv");
    }
}