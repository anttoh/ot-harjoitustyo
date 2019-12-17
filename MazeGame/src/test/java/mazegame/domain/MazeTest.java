package mazegame.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MazeTest {

    Maze maze;

    public MazeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        maze = new Maze(2, 2);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void currentCellIsRightAtStart() {
        assertEquals(maze.getLayout()[0][0], maze.getCurrentCell());

    }

    @Test
    public void goalIsCorrectCell() {
        assertEquals(maze.getLayout()[1][1], maze.getGoal());

    }

    @Test
    public void curCellCanBeEqualToGoal() {
        /*
        There are only two possible paths from top left corner to bottom right
        corner in 2 by 2 grid: either you go right then down or down then right
        If reachedGoal returns true after one of those paths, it's working
         */
        boolean visitedGoal = false;

        maze.moveRight();
        maze.moveDown();
        if (maze.getCurrentCell() == maze.getGoal()) {
            visitedGoal = true;
        }

        // reset
        maze.moveUp();
        maze.moveLeft();

        maze.moveDown();
        maze.moveRight();
        if (maze.getCurrentCell() == maze.getGoal()) {
            visitedGoal = true;
        }

        assertEquals(true, visitedGoal);

    }
}
