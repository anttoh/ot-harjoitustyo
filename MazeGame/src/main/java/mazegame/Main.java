package mazegame;

import mazegame.dao.DatabaseInitializer;
import mazegame.ui.MazeGameUi;

public class Main {

    public static void main(String[] args) {
        DatabaseInitializer.initDatabaseIfNotExisting();
        MazeGameUi.main(args);
    }
}
