package mazegame.domain;

public class Maze {

    private Cell[][] layout;
    private Cell curCell;
    private Cell goal;

    public Maze(Cell[][] layout) {
        this.layout = layout;
        this.curCell = this.layout[0][0];
        this.goal = this.layout[this.layout.length - 1][this.layout[0].length - 1];

    }

    public Cell getCurrentCell() {
        return this.curCell;
    }

    public Cell getGoal() {
        return this.goal;
    }

    public boolean reachedGoal() {
        return this.goal == this.curCell;
    }

    public Cell getCellAt(int x, int y) {
        return this.layout[x][y];
    }

    public void moveUp() {
        this.curCell = this.curCell.getUp();
    }

    public void moveDown() {
        this.curCell = this.curCell.getDown();
    }

    public void moveRight() {
        this.curCell = this.curCell.getRight();
    }

    public void moveLeft() {
        this.curCell = this.curCell.getLeft();
    }

}
