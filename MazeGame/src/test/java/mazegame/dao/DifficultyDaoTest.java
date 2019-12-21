package mazegame.dao;

import java.sql.SQLException;
import mazegame.domain.Difficulty;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DifficultyDaoTest {

    DifficultyDao difficultyDao;

    public DifficultyDaoTest() {
        DatabaseInitializer.initDatabaseIfNotExisting();
        difficultyDao = new DifficultyDao();

    }

    @Test
    public void difficultyCanBeRead() throws SQLException {
        assertEquals(1, difficultyDao.read(new Difficulty("very easy")).getId());
        assertEquals(2, difficultyDao.read(new Difficulty("easy")).getId());
        assertEquals(3, difficultyDao.read(new Difficulty("medium")).getId());
        assertEquals(4, difficultyDao.read(new Difficulty("hard")).getId());
        assertEquals(5, difficultyDao.read(new Difficulty("ultra hard")).getId());
    }
}
