package mazegame.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MazeGameServiceTest {

    MazeGameService service;

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
    }

    @After
    public void tearDown() {
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

}
