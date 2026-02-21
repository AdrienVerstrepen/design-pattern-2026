package fr.fges.data.repositories;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.data.repositories.games.GameCollectionDaoRam;

public class GameCollectionDaoRamTest extends GameCollectionDaoTest {
    @Override
    protected GameCollectionDao getDao() {
        return new GameCollectionDaoRam();
    }
}