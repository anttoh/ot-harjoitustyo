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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Cell getUp() {
        return up;
    }

    public void setUp(Cell up) {
        this.up = up;
    }

    public Cell getDown() {
        return down;
    }

    public void setDown(Cell down) {
        this.down = down;
    }

    public Cell getLeft() {
        return left;
    }

    public void setLeft(Cell left) {
        this.left = left;
    }

    public Cell getRight() {
        return right;
    }

    public void setRight(Cell right) {
        this.right = right;
    }
}
