package mazegame.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import mazegame.domain.Difficulty;

public class DifficultyDao implements Dao<Difficulty, Long> {

    @Override
    public void create(Difficulty difficulty) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./mazegame", "a", "");
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Difficulty"
                + " (name)"
                + " VALUES (?);");

        stmt.setString(1, difficulty.getName());

        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    @Override
    public Difficulty read(Long key) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./mazegame", "a", "");

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Difficulty"
                + " WHERE id = ?;");

        stmt.setLong(1, key);
        ResultSet rs = stmt.executeQuery();

        Difficulty difficulty = null;

        if (rs.next()) {
            difficulty = new Difficulty(rs.getInt("id"),
                    rs.getString("name"));

        }
        stmt.close();
        rs.close();
        conn.close();

        return difficulty;
    }

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

    @Override
    public Difficulty update(Difficulty object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Difficulty> list() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
