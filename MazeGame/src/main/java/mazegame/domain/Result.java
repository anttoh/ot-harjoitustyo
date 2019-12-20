package mazegame.domain;

/**
 * Class representing games result. It contains id, user that played the game in
 * question, games difficulty, and the time it took to finish the game.
 */
public class Result {

    private long id;
    private User user;
    private Difficulty difficulty;
    private int time;

    public Result(long id, User user, Difficulty difficulty, int time) {
        this.id = id;
        this.user = user;
        this.difficulty = difficulty;
        this.time = time;
    }

    public Result(User user, Difficulty difficulty, int time) {
        this(0, user, difficulty, time);
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getTime() {
        return time;
    }
}
