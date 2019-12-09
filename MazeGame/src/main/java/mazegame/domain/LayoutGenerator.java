package mazegame.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class responsible for generating the layout for the maze
 */
public class LayoutGenerator {

    private int width; // 2 <= width <= 90
    private int height; // 2 <= height <= 90
    private Cell[][] cells;
    private boolean[][] visited;

    public LayoutGenerator() {

    }

    /**
     * Method creates the mazes layout in the given 2d array of Cells. This is
     * done by connecting adjacent cells using recursive backtracking algorithm.
     * Method doesn't return anything, it simply changes the cells it receives
     * as parameter.
     *
     * @param cells 2d array of cells, which are not yet connected
     */
    public void generateMazeLayout(Cell[][] cells) {
        this.cells = cells;
        this.width = this.cells.length;
        this.height = this.cells[0].length;

        this.visited = new boolean[this.width][this.height];
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                this.visited[x][y] = false;
            }
        }

        this.generateMazeRecursively(this.getRandomCell());
    }

    private void generateMazeRecursively(Cell c) {
        List neighbours = this.getNeighbours(c);

        Collections.shuffle(neighbours);

        for (Object neighbourObj : neighbours) {
            Cell neighbour = (Cell) neighbourObj;
            if (!visited[neighbour.getX()][neighbour.getY()]) {
                this.connectCells(c, neighbour);
                this.visited[neighbour.getX()][neighbour.getY()] = true;
                this.generateMazeRecursively(neighbour);
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

    private Cell getRandomCell() {
        int randX = (int) (Math.random() * this.width);
        int randY = (int) (Math.random() * this.height);

        return this.cells[randX][randY];
    }
}
