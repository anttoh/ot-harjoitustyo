package mazegame.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MazeGenerator {

    private int width; // max 100
    private int height; // max 100
    private Cell[][] cells;
    private boolean[][] visited;

    public MazeGenerator(int width, int height) {
        this.width = width < 100 ? width : 100;
        this.height = height < 100 ? height : 100;
        this.cells = new Cell[this.width][this.height];
        this.visited = new boolean[this.width][this.height];
    }

    public Cell[][] generateAndReturnMazeLayout() {
        this.initializeCells();
        this.generateMaze(this.getRandomCell());
        return this.cells;
    }

    private Cell getRandomCell() {
        int randX = (int) (Math.random() * this.width);
        int randY = (int) (Math.random() * this.height);

        return this.cells[randX][randY];
    }

    private void generateMaze(Cell c) {
        List neighbours = this.getNeighbours(c);

        Collections.shuffle(neighbours);

        for (Object neighbourObj : neighbours) {
            Cell neighbour = (Cell) neighbourObj;
            if (!visited[neighbour.getX()][neighbour.getY()]) {
                this.connectCells(c, neighbour);
                this.visited[neighbour.getX()][neighbour.getY()] = true;
                this.generateMaze(neighbour);
            }
        }
    }

    private void initializeCells() {
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                this.cells[x][y] = new Cell(x, y);
            }
        }
    }

    private void connectCells(Cell c1, Cell c2) {
        if (c1.getX() == c2.getX()) {
            if (c1.getY() < c2.getY()) {
                c1.setDown(c2);
                c2.setUp(c1);
            } else {
                c2.setDown(c1);
                c1.setUp(c2);
            }
        } else if (c1.getY() == c2.getY()) {
            if (c1.getX() < c2.getX()) {
                c1.setRight(c2);
                c2.setLeft(c1);
            } else {
                c2.setRight(c1);
                c1.setLeft(c2);
            }
        }
    }

    private List<Cell> getNeighbours(Cell c) {
        List<Cell> neighbours = new ArrayList<>();
        int x = c.getX();
        int y = c.getY();

        if (x > 0) {
            neighbours.add(this.cells[x - 1][y]);
        }
        if (x < this.width - 1) {
            neighbours.add(this.cells[x + 1][y]);
        }
        if (y > 0) {
            neighbours.add(this.cells[x][y - 1]);
        }
        if (y < this.height - 1) {
            neighbours.add(this.cells[x][y + 1]);
        }

        return neighbours;
    }
}
