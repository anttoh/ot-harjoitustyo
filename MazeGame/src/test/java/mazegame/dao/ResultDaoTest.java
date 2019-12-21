package mazegame.dao;

import java.sql.SQLException;
import mazegame.domain.Difficulty;
import mazegame.domain.Result;
import mazegame.domain.User;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ResultDaoTest {

    ResultDao resultDao;
    UserDao userDao;
    DifficultyDao difficultyDao;

    public ResultDaoTest() {
        DatabaseInitializer.initDatabaseIfNotExisting();
        resultDao = new ResultDao();
        userDao = new UserDao();
        difficultyDao = new DifficultyDao();

    }

    @Test
    public void resultCanBeCreated() throws SQLException {
        User testUser = new User("testaajaMdvGWx5XzpKNY37k", "salasana9V7xW8wsqB2NbQRX");
        userDao.create(testUser);
        testUser = userDao.read(testUser);
        Difficulty difficulty = difficultyDao.read(new Difficulty("very easy"));
        Result result = new Result(testUser, difficulty, 10);

        assertEquals(0, userDao.getAverageBestAndWorstTimesForEachCategory(testUser)[1][1], 0.1);
        resultDao.create(result);
        assertEquals(10.0, userDao.getAverageBestAndWorstTimesForEachCategory(testUser)[1][1], 0.1);

        userDao.delete(testUser);
    }
}
