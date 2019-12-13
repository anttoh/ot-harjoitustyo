package mazegame.domain;

/**
 * Class responsible for containing the mazes state (layout, location of the
 * users character and the goal). It also offers methods to move in the maze.
 */
public class Maze {

    private Cell[][] layout;
    private boolean[][] visitedCells;
    private Cell curCell;
    private Cell goal;

    public Maze(Cell[][] layout) {
        this.layout = layout;
        this.visitedCells = new boolean[this.layout.length][this.layout[0].length];
        this.curCell = this.layout[0][0];
        this.goal = this.layout[this.layout.length - 1][this.layout[0].length - 1];

    }

    /**
     * Method gets the Cell that the players character is currently on.
     *
     * @return Cell which the player is currently on
     */
    public Cell getCurrentCell() {
        return this.curCell;
    }

    /**
     * Method returns the goal of the maze. Goal is the bottom right corner of
     * the maze.
     *
     * @return Cell that is the goal.
     */
    public Cell getGoal() {
        return this.goal;
    }

    /**
     * Method tells whether or not a cell has been visited.
     *
     * @param cell cell in question
     *
     * @return true if the cell has been visited and false if not.
     */
    public boolean visited(Cell cell) {
        return this.visitedCells[cell.getX()][cell.getY()];
    }

    /**
     * Method tells whether or not the goal of the maze has been reached
     *
     * @return true if the cell, which the user is on, is the same cell, that
     * the goal is, and false otherwise
     */
    public boolean reachedGoal() {
        return this.goal == this.curCell;
    }

    /**
     * Method sets the cell on which the player is on to the cell one above the
     * current one.
     */
    public void moveUp() {
        this.setCurCellToVisited();
        this.curCell = this.curCell.getUp();
    }

    /**
     * Method sets the cell on which the player is on to the cell below the
     * current one.
     */
    public void moveDown() {
        this.setCurCellToVisited();
        this.curCell = this.curCell.getDown();
    }

    /**
     * Method sets the cell on which the player is on to the cell right of the
     * current one.
     */
    public void moveRight() {
        this.setCurCellToVisited();
        this.curCell = this.curCell.getRight();
    }

    /**
     * Method sets the cell on which the player is on to the cell left of the
     * current one.
     */
    public void moveLeft() {
        this.setCurCellToVisited();
        this.curCell = this.curCell.getLeft();
    }

    private void setCurCellToVisited() {
        this.visitedCells[this.curCell.getX()][this.curCell.getY()] = true;
    }

}
