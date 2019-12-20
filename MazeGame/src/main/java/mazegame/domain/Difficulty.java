package mazegame.domain;

/**
 * Class representing games difficulty level. It has an id and name.
 */

public class Difficulty {

    private long id;
    private String name;

    public Difficulty(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Difficulty(String name) {
        this(0, name);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
