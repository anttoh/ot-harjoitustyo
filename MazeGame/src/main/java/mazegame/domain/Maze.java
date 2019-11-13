package mazegame.domain;

public class Maze {

    private Cell[][] layout;
    private Cell curCell;

    public Maze(int width, int height) {
        MazeGenerator generator = new MazeGenerator(width, height);
        this.layout = generator.generateAndReturnMazeLayout();
        this.curCell = this.layout(0, 0);

    }

    public Cell getCurrentCell() {
        return this.curCell;
    }

    public Cell layout(int x, int y) {
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
