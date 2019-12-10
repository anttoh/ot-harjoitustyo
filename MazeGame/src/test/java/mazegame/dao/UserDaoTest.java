package mazegame.dao;

import java.sql.SQLException;
import mazegame.domain.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserDaoTest {

    UserDao userDao;
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

        assertEquals(true, testUser.getId() == 0);
        testUser = userDao.read(testUser);
        assertEquals(false, testUser.getId() == 0);

        assertEquals(false, userDao.read(testUser) == null);
        userDao.delete(testUser);
        assertEquals(true, userDao.read(testUser) == null);

    }

    @Test
    public void usersAveregeSolveCanBeGotten() throws SQLException {

    }
}
