/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        maze = new Maze(10, 10);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void currentCellIsRightAtStart() {
        assertEquals(maze.layout(0, 0), maze.getCurrentCell());

    }

    @Test
    public void goalIsCorrectCell() {
        assertEquals(maze.layout(9, 9), maze.getGoal());

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
        maze = new Maze(2, 2);

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
