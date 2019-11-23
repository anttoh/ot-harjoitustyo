package mazegame.domain;

public class Game {

    private int id;
    private User user;
    private int time;

    public Game(int id, User user, int time) {
        this.id = id;
        this.user = user;
        this.time = time;
    }

    public Game(User user, int time) {
        this(0, user, time);
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

}
