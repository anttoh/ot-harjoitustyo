package mazegame.domain;

/**
 * Class responsible for containing the mazes state (layout, location of the
 * users character and the goal and visited cells). It also offers methods to
 * move in the maze. Class calls LayoutGenerators method generateMazeLayout in
 * it's constructor.
 */
public class Maze {

    private Cell[][] layout;
    private boolean[][] visitedCells;
    private Cell curCell;
    private Cell goal;

    public Maze(int width, int height) {
        this.initializeLayout(width, height); // sets this.layout
        this.visitedCells = new boolean[width][height];
        this.curCell = this.layout[0][0];
        this.goal = this.layout[width - 1][height - 1];
        new LayoutGenerator().generateMazeLayout(this.layout);
    }

    /**
     * Method gets the layout of the maze.
     *
     * @return Cell[][] layout
     */
    public Cell[][] getLayout() {
        return this.layout;
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

    private void initializeLayout(int width, int height) {
        this.layout = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.layout[x][y] = new Cell(x, y);
            }
        }
    }
}
