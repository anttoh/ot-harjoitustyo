package mazegame.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class LayoutGeneratorTest {

    LayoutGenerator generator;
    Cell[][] layout;

    public LayoutGeneratorTest() {
        layout = new Cell[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                layout[i][j] = new Cell(i, j);
            }
        }
        generator = new LayoutGenerator();
        generator.generateMazeLayout(layout);
    }

    @Test
    public void generatedMazeLayoutIsRightSize() {
        assertEquals(2, layout.length);
        assertEquals(2, layout[0].length);
    }

    @Test
    public void generatedMazeLayoutIsSolvable() {
        assertEquals(true, layout[0][0].getDown() != layout[0][0].getRight());
        assertEquals(true, layout[1][1].getUp() != layout[1][1].getLeft());
    }
}
