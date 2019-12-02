package mazegame.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import mazegame.domain.User;

public class UserDao implements Dao<User, Integer> {

    @Override
    public void create(User user) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./mazegame", "a", "");
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO User"
                    + " (username, password)"
                    + " VALUES (?, ?);");

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            stmt.executeUpdate();
            stmt.close();
        
    }

    @Override
    public User read(Integer key) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./mazegame", "a", "");

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User"
                + " WHERE id = ?;");

        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();

        User fullUser = null;

        if (rs.next()) {
            fullUser = new User(rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"));

        }
        stmt.close();
        rs.close();
        conn.close();

        return fullUser;
    }

    public User read(User user) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./mazegame", "a", "");

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User"
                + " WHERE username = ? AND password = ?;");

        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        ResultSet rs = stmt.executeQuery();

        User fullUser = null;

        if (rs.next()) {
            fullUser = new User(rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"));

        }
        stmt.close();
        rs.close();
        conn.close();

        return fullUser;
    }

    @Override
    public User update(User object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> list() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
