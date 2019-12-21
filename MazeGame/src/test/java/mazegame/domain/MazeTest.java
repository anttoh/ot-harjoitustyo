package mazegame.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class MazeTest {

    Maze maze;

    public MazeTest() {
    }

    @Before
    public void setUp() {
        maze = new Maze(2, 2);
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
    public void curCellChangesWhenUserMovesToDifferentCell() {
        Cell before = maze.getCurrentCell();
        maze.moveDown();
        maze.moveRight();
        Cell after = maze.getCurrentCell();
        assertEquals(false, before.equals(after));
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

    @Test
    public void visitedReturnsFalseIfCellHasNotBeenCurCell() {
        assertEquals(false, maze.visited(maze.getLayout()[1][1]));
    }

    @Test
    public void curCellIsSetToVisitedWhenMovedFrom() {
        assertEquals(false, maze.visited(maze.getLayout()[0][0]));
        maze.moveDown();
        maze.moveRight();
        assertEquals(true, maze.visited(maze.getLayout()[0][0]));
    }
}
