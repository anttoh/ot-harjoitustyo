package mazegame.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class MazeGeneratorTest {

    LayoutGenerator generator;
    Cell[][] layout;

    public MazeGeneratorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void generatedMazeIsRightSize() {
        generator = new LayoutGenerator(10, 10);
        layout = generator.generateAndReturnMazeLayout();
        assertEquals(10, layout.length);
        assertEquals(10, layout[0].length);

        generator = new LayoutGenerator(50, 20);
        layout = generator.generateAndReturnMazeLayout();
        assertEquals(50, layout.length);
        assertEquals(20, layout[0].length);
    }

    @Test
    public void generatedMazeIs90By90AtMax() {
        generator = new LayoutGenerator(300, 300);
        layout = generator.generateAndReturnMazeLayout();
        assertEquals(90, layout.length);
        assertEquals(90, layout[0].length);
    }

    @Test
    public void generatedMazeIs2By2AtMin() {
        generator = new LayoutGenerator(1, 1);
        layout = generator.generateAndReturnMazeLayout();
        assertEquals(2, layout.length);
        assertEquals(2, layout[0].length);

        generator = new LayoutGenerator(-100, -100);
        layout = generator.generateAndReturnMazeLayout();
        assertEquals(2, layout.length);
        assertEquals(2, layout[0].length);
    }
}
