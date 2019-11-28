package mazegame.domain;

public class MazeGameService {

    private LayoutGenerator generator;
    private Cell[][] layout;
    private Maze maze;
    private int width;
    private int height;
    private boolean gameOngoing;

    public MazeGameService() {
        this.generator = new LayoutGenerator();
        this.gameOngoing = false;
    }

    public void startGame(int width, int height) {
        this.gameOngoing = true;
        this.width = width;
        this.height = height;
        this.initializeLayout();
        this.generator.generateMazeLayout(this.layout);
        this.maze = new Maze(this.layout);
    }

    public void endGame() {
        this.gameOngoing = false;
    }

    public boolean gameEnded() {
        return !this.gameOngoing;
    }

    public Cell getCellAtPos(int x, int y) {
        return this.layout[x][y];
    }

    public Cell mazeCurrentCell() {
        return this.maze.getCurrentCell();
    }

    public Cell mazeGoal() {
        return this.maze.getGoal();
    }

    public boolean goalReached() {
        return this.mazeCurrentCell() == this.mazeGoal();
    }

    public void goUp() {
        this.maze.moveUp();
    }

    public void goDown() {
        this.maze.moveDown();
    }

    public void goLeft() {
        this.maze.moveLeft();
    }

    public void goRight() {
        this.maze.moveRight();
    }

    public int[][] getLayoutAsIntsForDrawing() {
        int[][] intLayout = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell curCell = this.getCellAtPos(i, j);
                if (curCell.getRight() == curCell.getDown()) {
                    intLayout[i][j] = 0;
                } else if (curCell.getRight() != curCell) {
                    if (curCell.getDown() != curCell) {
                        intLayout[i][j] = 3;
                    } else {
                        intLayout[i][j] = 1;
                    }
                } else {
                    intLayout[i][j] = 2;
                }

            }
        }
        return intLayout;
    }

    private void initializeLayout() {
        this.layout = new Cell[this.width][this.height];
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                this.layout[x][y] = new Cell(x, y);
            }
        }
    }
}
