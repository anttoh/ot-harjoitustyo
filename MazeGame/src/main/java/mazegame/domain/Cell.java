package mazegame.domain;

/**
 * Class responsible for representing a single cell of the maze. Cell knows its
 * 4 neighbours, the one above it, the one below it, and ones on its left and
 * right sides. By default these neighbours are the cell itself. If you tried to
 * get cells neighbour and it didn't have one, you would get the cell itself
 */
public class Cell {

    private int x;
    private int y;
    private Cell up;
    private Cell down;
    private Cell left;
    private Cell right;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.up = this;
        this.down = this;
        this.left = this;
        this.right = this;
    }

    /**
     * Method returns x coordinate of this cell.
     *
     * @return x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Method returns y coordinate of this cell.
     *
     * @return y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Method returns the neighbour cell that is above this one or it returns
     * this cell, if the above cell hasn't been set.
     *
     * @return neighbour cell above or this cell if this doesn't have neighbour
     * above
     */
    public Cell getUp() {
        return up;
    }

    /**
     * Method sets the neighbour cell above this one.
     *
     * @param up neighbour cell above
     */
    public void setUp(Cell up) {
        this.up = up;
    }

    /**
     * Method returns the neighbour cell that is below this one or it returns
     * this cell, if the above cell hasn't been set.
     *
     * @return neighbour cell below or this cell if this doesn't have neighbour
     * below
     */
    public Cell getDown() {
        return down;
    }

    /**
     * Method sets the neighbour cell below this one.
     *
     * @param down neighbour cell below
     */
    public void setDown(Cell down) {
        this.down = down;
    }

    /**
     * Method returns the neighbour cell to the left of this one or it returns
     * this cell, if the left cell hasn't been set.
     *
     * @return neighbour cell on the left or this cell if this doesn't have
     * neighbour on it's left
     */
    public Cell getLeft() {
        return left;
    }

    /**
     * Method sets the neighbour cell left of this one.
     *
     * @param left neighbour cell on the left
     */
    public void setLeft(Cell left) {
        this.left = left;
    }

    /**
     * Method returns the neighbour cell to the right of this one or it returns
     * this cell, if the right cell hasn't been set.
     *
     * @return neighbour cell on the right or this cell if this doesn't have
     * neighbour on it's right
     */
    public Cell getRight() {
        return right;
    }

    /**
     * Method sets the neighbour cell right of this one.
     *
     * @param right neighbour cell on the right
     */
    public void setRight(Cell right) {
        this.right = right;
    }
}
