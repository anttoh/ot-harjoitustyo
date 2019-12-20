package mazegame.dao;

import java.sql.SQLException;
import mazegame.domain.Difficulty;
import mazegame.domain.Result;
import mazegame.domain.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserDaoTest {

    UserDao userDao;
    ResultDao resultDao;
    DifficultyDao difficultyDao;
    User testUser;

    public UserDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        DatabaseInitializer.initDatabaseIfNotExisting();
        userDao = new UserDao();
        resultDao = new ResultDao();
        difficultyDao = new DifficultyDao();

    }

    @After
    public void tearDown() {
    }

    @Test
    public void newUserCanBeCreatedReadAndDeleted() throws SQLException {
        testUser = new User("testaajaJpOOtfenfS0Of9X4", "salasanadlemhP5eX9s8uaUH");

        assertEquals(true, userDao.read(testUser) == null);
        userDao.create(testUser);
        assertEquals(false, userDao.read(testUser) == null);

        assertEquals(0, testUser.getId());
        testUser = userDao.read(testUser);
        assertEquals(true, testUser.getId() != 0);

        assertEquals(true, userDao.read(testUser) != null);
        userDao.delete(testUser);
        assertEquals(null, userDao.read(testUser));

    }

    @Test
    public void getAverageBestAndWorstTimesForEachCategoryWorksWithResults() throws SQLException {
        // this user is created if they don't exist yet to avoid key violation
        testUser = new User("CONSTtestaajaJpOOtfenfS0Of9X41", "CONSTsalasanadlemhP5eX9s8uaUH1");
        if (userDao.read(testUser) == null) {
            userDao.create(testUser);
        }
        testUser = userDao.read(testUser);

        // create results for the user
        Difficulty difficulty = new Difficulty("very easy");
        difficulty = difficultyDao.read(difficulty);
        Result result = new Result(testUser, difficulty, 10);
        resultDao.create(result);
        result = new Result(testUser, difficulty, 5);
        resultDao.create(result);
        difficulty = difficultyDao.read(new Difficulty("easy"));
        result = new Result(testUser, difficulty, 20);
        resultDao.create(result);

        double[][] times = userDao.getAverageBestAndWorstTimesForEachCategory(testUser);

        assertEquals(7.5, times[1][1], 0.1);
        assertEquals(5.0, times[2][1], 0.1);
        assertEquals(10.0, times[3][1], 0.1);

        assertEquals(20.0, times[1][2], 0.1);
        assertEquals(20.0, times[2][2], 0.1);
        assertEquals(20.0, times[3][2], 0.1);

    }

    @Test
    public void getAverageBestAndWorstTimesForEachCategoryWorksWithoutResults() throws SQLException {
        testUser = new User("testaajaJpOOtfenfS0Of9X4", "salasanadlemhP5eX9s8uaUH");
        userDao.create(testUser);
        testUser = userDao.read(testUser);

        double[][] times = userDao.getAverageBestAndWorstTimesForEachCategory(testUser);

        assertEquals(0, times[1][1], 0.1);
        assertEquals(0, times[2][1], 0.1);
        assertEquals(0, times[3][1], 0.1);

        userDao.delete(testUser);
    }
}
