package mazegame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mazegame.ui.MazeGameUi;

public class Main {

    public static void main(String[] args) {
        initDatabaseIfNotExisting();
        MazeGameUi.main(args);
    }

    private static void initDatabaseIfNotExisting() {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:./mazegame", "a", "")) {

            // uncomment these statements and run this file, if you want to reset the db
            // then comment or delete them.
            /*
            conn.prepareStatement("DROP TABLE GameDifficulty IF EXISTS;").executeUpdate();
            conn.prepareStatement("DROP TABLE UserGame IF EXISTS;").executeUpdate();
            conn.prepareStatement("DROP TABLE Game IF EXISTS;").executeUpdate();
            conn.prepareStatement("DROP TABLE Difficulty IF EXISTS;").executeUpdate();
            conn.prepareStatement("DROP TABLE User IF EXISTS;").executeUpdate();
             */
            conn.prepareStatement("CREATE TABLE IF NOT EXISTS User ("
                    + " id SERIAL,"
                    + " username VARCHAR(100) UNIQUE,"
                    + " password VARCHAR(100),"
                    + " PRIMARY KEY(id)"
                    + ");").executeUpdate();

            conn.prepareStatement("CREATE TABLE IF NOT EXISTS Difficulty ("
                    + " id SERIAL,"
                    + " name VARCHAR(100),"
                    + " PRIMARY KEY(id)"
                    + ");").executeUpdate();

            conn.prepareStatement("CREATE TABLE IF NOT EXISTS Game ("
                    + " id SERIAL,"
                    + " user_id INTEGER,"
                    + " difficulty_id INTEGER,"
                    + " time INTEGER,"
                    + " PRIMARY KEY(id),"
                    + " FOREIGN KEY (user_id) REFERENCES User(id),"
                    + " FOREIGN KEY (difficulty_id) REFERENCES Difficulty(id)"
                    + ");").executeUpdate();

            conn.prepareStatement("CREATE TABLE IF NOT EXISTS UserGame ("
                    + " user_id INTEGER,"
                    + " game_id INTEGER,"
                    + " FOREIGN KEY (user_id) REFERENCES User(id),"
                    + " FOREIGN KEY (game_id) REFERENCES Game(id)"
                    + ");").executeUpdate();

            conn.prepareStatement("CREATE TABLE IF NOT EXISTS GameDifficulty ("
                    + " difficulty_id INTEGER,"
                    + " game_id INTEGER,"
                    + " FOREIGN KEY (game_id) REFERENCES User(id),"
                    + " FOREIGN KEY (difficulty_id) REFERENCES Difficulty(id)"
                    + ");").executeUpdate();

            conn.prepareStatement("CREATE INDEX IF NOT EXISTS user_idx"
                    + " ON User (id);").executeUpdate();

            conn.prepareStatement("CREATE INDEX IF NOT EXISTS game_idx"
                    + " ON Game (id);").executeUpdate();

            conn.prepareStatement("CREATE INDEX IF NOT EXISTS game_user_idx"
                    + " ON Game (user_id);").executeUpdate();

            conn.prepareStatement("CREATE INDEX IF NOT EXISTS game_difficulty_idx"
                    + " ON Game (difficulty_id);").executeUpdate();

            conn.prepareStatement("CREATE INDEX IF NOT EXISTS usergame_user_idx"
                    + " ON UserGame (user_id);").executeUpdate();

            conn.prepareStatement("CREATE INDEX IF NOT EXISTS usergame_game_idx"
                    + " ON UserGame (game_id);").executeUpdate();

            conn.prepareStatement("CREATE INDEX IF NOT EXISTS gamedifficulty_game_idx"
                    + " ON GameDifficulty (game_id);").executeUpdate();

            conn.prepareStatement("CREATE INDEX IF NOT EXISTS gamedifficulty_difficulty_idx"
                    + " ON GameDifficulty (difficulty_id);").executeUpdate();

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
