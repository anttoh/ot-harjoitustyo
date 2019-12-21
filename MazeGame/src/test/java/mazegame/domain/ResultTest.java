package mazegame.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ResultTest {

    @Test
    public void gettersWork() {
        User user = new User("username", "password");
        Difficulty difficulty = new Difficulty("easy");
        Result result = new Result(user, difficulty, 10);

        assertEquals(0, result.getId());
        assertEquals(user, result.getUser());
        assertEquals(difficulty, result.getDifficulty());
        assertEquals(10, result.getTime());

        result = new Result(12345, user, difficulty, 10);

        assertEquals(12345, result.getId());
    }
}
