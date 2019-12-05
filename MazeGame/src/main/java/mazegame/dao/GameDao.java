package mazegame.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import mazegame.domain.Game;

public class GameDao implements Dao<Game, Integer> {

    @Override
    public void create(Game game) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./mazegame", "a", "");
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Game"
                + " (user_id, difficulty_id, time)"
                + " VALUES (?, ?, ?);");

        stmt.setInt(1, game.getUser().getId());
        stmt.setInt(2, game.getDifficulty().getId());
        stmt.setInt(3, game.getTime());

        stmt.executeUpdate();
        Integer game_id = stmt.getGeneratedKeys().getInt(1);
        stmt.close();

        stmt = conn.prepareStatement("INSERT INTO UserGame"
                + " (user_id, game_id)"
                + " VALUES (?, ?);");

        stmt.setInt(1, game.getUser().getId());
        stmt.setInt(2, game_id);

        stmt.executeUpdate();
        stmt.close();

        stmt = conn.prepareStatement("INSERT INTO GameDifficulty"
                + " (game_id, difficulty_id)"
                + " VALUES (?, ?);");

        stmt.setInt(1, game_id);
        stmt.setInt(2, game.getDifficulty().getId());

        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    @Override
    public Game read(Integer key) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./mazegame", "a", "");

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User"
                + " WHERE id = ?;");

        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();

        stmt.close();
        rs.close();
        conn.close();

        return null;
    }

    @Override
    public Game update(Game object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Game> list() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
