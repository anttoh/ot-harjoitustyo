package mazegame.domain;

import java.sql.SQLException;
import mazegame.dao.UserDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MazeGameServiceTest {

    MazeGameService service;
    UserDao userDao;
    User testUser;
    String username;
    String password;

    public MazeGameServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        service = new MazeGameService();
        userDao = new UserDao();
        username = "testaajaUpNG2x3tthKryTKY";
        password = "salasanaRJua5G7fKRU4RbAL";
        testUser = new User(username, password);

    }

    @After
    public void tearDown() throws SQLException {
        service.register(username, password); // new user is created only if it wasn't created in the test case. This is done to avoi exeption, but it doesn't alter the results of the tests cases.
        userDao.delete(userDao.read(testUser));
    }

    @Test
    public void gameEndedReturnsTrueIfGameIsNotOngoing() {
        assertEquals(true, service.gameEnded());
    }

    @Test
    public void startGameSetsGameToOngoing() {
        service.startGame(2, 2);
        assertEquals(false, service.gameEnded());

    }

    @Test
    public void startGameCreatesCorrectSizeLayout() {
        service.startGame(10, 5);
        CellTypeForDrawing[][] layout = service.getLayoutForDrawing();
        assertEquals(10, layout.length);
        assertEquals(5, layout[0].length);

    }

    @Test
    public void setDifficultyWorks() {
        service.startGame(10, 5);
        assertEquals("custom", service.getDifficultyName());

        service.startGame(5, 5);
        assertEquals("very easy", service.getDifficultyName());

        service.startGame(10, 10);
        assertEquals("easy", service.getDifficultyName());

        service.startGame(20, 20);
        assertEquals("medium", service.getDifficultyName());

        service.startGame(40, 40);
        assertEquals("hard", service.getDifficultyName());

        service.startGame(80, 80);
        assertEquals("ultra hard", service.getDifficultyName());

    }

    @Test
    public void goalReachedWorks() {
        service.startGame(2, 2);
        boolean visitedGoal = false;

        service.goRight();
        service.goDown();
        if (service.mazeCurrentCell() == service.mazeGoal()) {
            visitedGoal = true;
        }

        // reset
        service.goUp();
        service.goLeft();

        service.goDown();
        service.goRight();
        if (service.mazeCurrentCell() == service.mazeGoal()) {
            visitedGoal = true;
        }

        assertEquals(true, visitedGoal);
    }

    @Test
    public void newUserCanBeCreatedIfUsernameIsAvailable() throws SQLException {
        assertEquals(true, userDao.read(testUser) == null);
        assertEquals(true, service.register(username, password));
        assertEquals(false, userDao.read(testUser) == null);
    }

    @Test
    public void newUserCanNotBeCreatedIfUsernameIsTaken() throws SQLException {
        assertEquals(true, service.register(username, password));
        assertEquals(false, service.register(username, password));
    }

    @Test
    public void loginIsSuccesfulIfUsernameAndPasswordAreCorrect() throws SQLException {
        service.register(username, password);
        assertEquals(true, service.login(username, password));
        assertEquals(true, service.getLoggedInUser().equals(testUser));
    }

    @Test
    public void loginIsUnsuccesfulIfUsernameOrPasswordIsIncorrenct() throws SQLException {
        service.register(username, password);
        String wrongPassword = "incorrectPassword";
        assertEquals(false, service.login(username, wrongPassword));
        assertEquals(true, service.getLoggedInUser() == null);

        String wrongUsername = "nonExistingUsername";
        assertEquals(false, service.login(wrongUsername, password));
        assertEquals(true, service.getLoggedInUser() == null);
    }
}
