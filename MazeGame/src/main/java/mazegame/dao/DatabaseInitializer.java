package mazegame.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mazegame.Main;

/**
 * Class responsible for initalizing the database.
 */
public class DatabaseInitializer {

    /**
     * Method initializes the database if it doesn't exist. It creates the
     * tables, indexes and predefined difficulty levels.
     */
    public static void initDatabaseIfNotExisting() {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:./mazegame;mode=MySQL", "a", "")) {

            // uncomment these statements if you want to reset the db
            // then comment or delete them.
            /*
            conn.prepareStatement("DROP TABLE Result IF EXISTS;").executeUpdate();
            conn.prepareStatement("DROP TABLE User IF EXISTS;").executeUpdate();
            conn.prepareStatement("DROP TABLE Difficulty IF EXISTS;").executeUpdate();
             */
            conn.prepareStatement("CREATE TABLE IF NOT EXISTS User ("
                    + " id IDENTITY,"
                    + " username VARCHAR(100) UNIQUE,"
                    + " password VARCHAR(100),"
                    + " PRIMARY KEY(id)"
                    + ");").executeUpdate();

            conn.prepareStatement("CREATE TABLE IF NOT EXISTS Difficulty ("
                    + " id IDENTITY,"
                    + " name VARCHAR(100),"
                    + " PRIMARY KEY(id)"
                    + ");").executeUpdate();

            conn.prepareStatement("CREATE TABLE IF NOT EXISTS Result ("
                    + " id IDENTITY,"
                    + " user_id BIGINT,"
                    + " difficulty_id BIGINT,"
                    + " time INT,"
                    + " PRIMARY KEY(id),"
                    + " FOREIGN KEY (user_id) REFERENCES User(id),"
                    + " FOREIGN KEY (difficulty_id) REFERENCES Difficulty(id)"
                    + ");").executeUpdate();

            conn.prepareStatement("CREATE INDEX IF NOT EXISTS user_idx"
                    + " ON User (id);").executeUpdate();

            conn.prepareStatement("CREATE INDEX IF NOT EXISTS difficulty_idx"
                    + " ON Difficulty (id);").executeUpdate();

            conn.prepareStatement("CREATE INDEX IF NOT EXISTS result_idx"
                    + " ON Result (id);").executeUpdate();

            conn.prepareStatement("CREATE INDEX IF NOT EXISTS result_user_idx"
                    + " ON Result (user_id);").executeUpdate();

            conn.prepareStatement("CREATE INDEX IF NOT EXISTS result_difficulty_idx"
                    + " ON Result (difficulty_id);").executeUpdate();

            // predefined game difficulties
            conn.prepareStatement("INSERT INTO Difficulty"
                    + " (id, name)"
                    + " VALUES (1, 'very easy')"
                    + " ON DUPLICATE KEY UPDATE id = id;").executeUpdate();

            conn.prepareStatement("INSERT INTO Difficulty"
                    + " (id, name)"
                    + " VALUES (2, 'easy')"
                    + " ON DUPLICATE KEY UPDATE id = id;").executeUpdate();

            conn.prepareStatement("INSERT INTO Difficulty"
                    + " (id, name)"
                    + " VALUES (3, 'medium')"
                    + " ON DUPLICATE KEY UPDATE id = id;").executeUpdate();

            conn.prepareStatement("INSERT INTO Difficulty"
                    + " (id, name)"
                    + " VALUES (4, 'hard')"
                    + " ON DUPLICATE KEY UPDATE id = id;").executeUpdate();

            conn.prepareStatement("INSERT INTO Difficulty"
                    + " (id, name)"
                    + " VALUES (5, 'ultra hard')"
                    + " ON DUPLICATE KEY UPDATE id = id;").executeUpdate();

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
