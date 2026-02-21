package fr.fges.data.repositories;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.data.repositories.games.GameCollectionDaoCsv;

public class GameCollectionDaoCsvTest extends GameCollectionDaoTest {
    @Override
    protected GameCollectionDao getDao() {
        return new GameCollectionDaoCsv("unit-test.csv");
    }
}