package mazegame.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CellTest {

    @Test
    public void gettersWork() {
        Cell cell = new Cell(5, 3);

        assertEquals(5, cell.getX());
        assertEquals(3, cell.getY());
        assertEquals(cell, cell.getUp());
        assertEquals(cell, cell.getDown());
        assertEquals(cell, cell.getRight());
        assertEquals(cell, cell.getLeft());
    }

    @Test
    public void settersWork() {
        Cell cell = new Cell(1, 1);
        Cell up = new Cell(1, 0);
        Cell down = new Cell(1, 2);
        Cell right = new Cell(2, 1);
        Cell left = new Cell(0, 1);

        cell.setUp(up);
        cell.setDown(down);
        cell.setRight(right);
        cell.setLeft(left);

        assertEquals(up, cell.getUp());
        assertEquals(down, cell.getDown());
        assertEquals(right, cell.getRight());
        assertEquals(left, cell.getLeft());
    }
}
