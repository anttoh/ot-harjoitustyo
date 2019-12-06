package mazegame.domain;

public class Game {

    private long id;
    private User user;
    private Difficulty difficulty;
    private int time;

    public Game(long id, User user, Difficulty difficulty, int time) {
        this.id = id;
        this.user = user;
        this.difficulty = difficulty;
        this.time = time;
    }

    public Game(User user, Difficulty difficulty, int time) {
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
