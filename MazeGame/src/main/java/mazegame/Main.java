package mazegame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mazegame.ui.MazeGameUi;

public class Main {

    public static void main(String[] args) {
        //initDB();
        MazeGameUi.main(args);
    }

    private static void initDB() {

        try (Connection conn = DriverManager.getConnection("jdbc:h2:./mazegame", "a", "")) {

            conn.prepareStatement("DROP TABLE UserGame IF EXISTS;").executeUpdate();
            conn.prepareStatement("DROP TABLE Game IF EXISTS;").executeUpdate();
            conn.prepareStatement("DROP TABLE User IF EXISTS;").executeUpdate();

            conn.prepareStatement("CREATE TABLE User ("
                    + " id SERIAL,"
                    + " username VARCHAR(100),"
                    + " password VARCHAR(100),"
                    + " PRIMARY KEY(id)"
                    + ");").executeUpdate();

            conn.prepareStatement("CREATE TABLE Game ("
                    + " id SERIAL,"
                    + " user_id INTEGER,"
                    + " time INTEGER,"
                    + " PRIMARY KEY(id),"
                    + " FOREIGN KEY (user_id) REFERENCES User(id)"
                    + ");").executeUpdate();

            conn.prepareStatement("CREATE TABLE UserGame ("
                    + " user_id INTEGER,"
                    + " game_id INTEGER,"
                    + " time INTEGER,"
                    + " FOREIGN KEY (user_id) REFERENCES User(id),"
                    + " FOREIGN KEY (game_id) REFERENCES Game(id)"
                    + ");").executeUpdate();

            conn.prepareStatement("CREATE INDEX user_idx"
                    + " ON User (id);").executeUpdate();

            conn.prepareStatement("CREATE INDEX game_idx"
                    + " ON Game (id);").executeUpdate();

            conn.prepareStatement("CREATE INDEX game_user_idx"
                    + " ON Game (user_id);").executeUpdate();

            conn.prepareStatement("CREATE INDEX usergame_user_idx"
                    + " ON UserGame (user_id);").executeUpdate();

            conn.prepareStatement("CREATE INDEX usergame_game_idx"
                    + " ON UserGame (game_id);").executeUpdate();

        } catch (SQLException ex) {

            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
