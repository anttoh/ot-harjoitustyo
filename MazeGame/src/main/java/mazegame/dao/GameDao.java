package mazegame.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mazegame.domain.Game;

public class GameDao {

    public void create(Game game) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./mazegame", "a", "");
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Game"
                + " (user_id, difficulty_id, time)"
                + " VALUES (?, ?, ?);");

        stmt.setLong(1, game.getUser().getId());
        stmt.setLong(2, game.getDifficulty().getId());
        stmt.setInt(3, game.getTime());

        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    public Game read(Long key) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./mazegame", "a", "");

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User"
                + " WHERE id = ?;");

        stmt.setLong(1, key);
        ResultSet rs = stmt.executeQuery();

        stmt.close();
        rs.close();
        conn.close();

        return null;
    }
}
