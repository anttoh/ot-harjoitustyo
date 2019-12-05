package mazegame.domain;

public class Difficulty {

    private int id;
    private String name;

    public Difficulty(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Difficulty(String name) {
        this(0, name);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
