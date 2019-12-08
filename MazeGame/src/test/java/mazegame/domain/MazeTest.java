package mazegame.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MazeTest {

    Maze maze;
    Cell[][] layout;
    LayoutGenerator generator;

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
        layout = new Cell[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                layout[i][j] = new Cell(i, j);
            }
        }
        generator = new LayoutGenerator();
        generator.generateMazeLayout(layout);
        maze = new Maze(layout);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void currentCellIsRightAtStart() {
        assertEquals(layout[0][0], maze.getCurrentCell());

    }

    @Test
    public void goalIsCorrectCell() {
        assertEquals(layout[1][1], maze.getGoal());

    }

    @Test
    public void reachedGoalReturnsFalseWhenCurCellIsNotEqualsToGoal() {
        assertEquals(false, maze.reachedGoal());

    }

    @Test
    public void reachedGoalReturnsTrueWhenCurCellEqualsGoal() {
        /*
        There are only two possible paths from top left corner to bottom right
        corner in 2 by 2 grid: either you go right then down or down then right
        If reachedGoal returns true after one of those paths, it's working
         */
        boolean visitedGoal = false;

        maze.moveRight();
        maze.moveDown();
        if (maze.reachedGoal()) {
            visitedGoal = true;
        }

        // reset
        maze.moveUp();
        maze.moveLeft();

        maze.moveDown();
        maze.moveRight();
        if (maze.reachedGoal()) {
            visitedGoal = true;
        }

        assertEquals(true, visitedGoal);

    }
}
