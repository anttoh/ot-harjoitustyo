package mazegame.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mazegame.domain.Difficulty;

public class DifficultyDao {

    public Difficulty read(Difficulty difficulty) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./mazegame", "a", "");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Difficulty"
                + " WHERE name = ?;");
        stmt.setString(1, difficulty.getName());
        ResultSet rs = stmt.executeQuery();

        Difficulty fullDifficulty = null;

        if (rs.next()) {
            fullDifficulty = new Difficulty(rs.getInt("id"),
                    rs.getString("name"));

        }

        stmt.close();
        rs.close();
        conn.close();

        return fullDifficulty;
    }
}
