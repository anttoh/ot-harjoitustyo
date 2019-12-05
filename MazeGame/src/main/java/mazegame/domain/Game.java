package mazegame.domain;

public class Game {

    private int id;
    private User user;
    private int time;
    private Difficulty difficulty;

    public Game(int id, User user, int time, Difficulty difficulty) {
        this.id = id;
        this.user = user;
        this.time = time;
        this.difficulty = difficulty;
    }

    public Game(User user, int time, Difficulty difficulty) {
        this(0, user, time, difficulty);
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public int getTime() {
        return time;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

}
