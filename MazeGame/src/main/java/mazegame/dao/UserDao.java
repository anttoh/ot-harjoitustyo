package mazegame.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mazegame.domain.User;

public class UserDao {

    public void create(User user) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./mazegame", "a", "");
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO User"
                + " (username, password)"
                + " VALUES (?, ?);");

        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());

        stmt.executeUpdate();
        stmt.close();
        conn.close();

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

    public double[] getAveregeSolveTimesForEachDifficultyFromEasiest(User user) throws SQLException {
        double[] times = new double[6];
        for (int i = 0; i < 6; i++) {
            times[i] = 0;
        }

        Connection conn = DriverManager.getConnection("jdbc:h2:./mazegame", "a", "");

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Game"
                + " WHERE user_id = ?"
                + " ORDER BY difficulty_id;");

        stmt.setLong(1, user.getId());
        ResultSet rs = stmt.executeQuery();

        int prev_id = 1;
        int cur_id;
        int time;
        int sum = 0;
        int games = 0;
        while (rs.next()) {
            cur_id = rs.getInt(3);
            time = rs.getInt(4);
            if (prev_id != cur_id) {
                if (games != 0) {
                    double t = (1.0 * sum / games);
                    times[prev_id] = Math.round(t * 10) / 10.0;
                }
                sum = 0;
                games = 0;
            }
            sum += time;
            games++;
            prev_id = cur_id;
        }
        if (games != 0) {
            double t = (1.0 * sum / games);
            times[prev_id] = Math.round(t * 10) / 10.0;
        }

        stmt.close();
        rs.close();
        conn.close();

        return times;
    }
}
