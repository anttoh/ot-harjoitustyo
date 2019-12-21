package mazegame.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DifficultyTest {

    @Test
    public void gettersWork() {
        Difficulty difficulty = new Difficulty("easy");

        assertEquals(0, difficulty.getId());
        assertEquals("easy", difficulty.getName());

        difficulty = new Difficulty(2, "easy");

        assertEquals(2, difficulty.getId());
    }
}
