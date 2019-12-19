package mazegame.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import mazegame.domain.Result;

public class ResultDao {

    public void create(Result result) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./mazegame", "a", "");
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Result"
                + " (user_id, difficulty_id, time)"
                + " VALUES (?, ?, ?);");

        stmt.setLong(1, result.getUser().getId());
        stmt.setLong(2, result.getDifficulty().getId());
        stmt.setInt(3, result.getTime());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }
}
