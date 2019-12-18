package mazegame.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    // note: this method is only used in tests to delete test users. 
    public void delete(User user) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./mazegame", "a", "");
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM User"
                + " WHERE id = ?;");

        stmt.setLong(1, user.getId());
        stmt.executeUpdate();

        stmt.close();
        conn.close();

    }

    public double[][] getAveregeSolveTimesForEachDifficultyFromEasiestAndBestAndWorstTimes(User user) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./mazegame", "a", "");
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT * FROM Result"
                + " WHERE user_id = " + user.getId()
                + " ORDER BY difficulty_id;");

        double[][] times = this.calculateAveregesAndFindBestAndWorstTimes(rs);

        stmt.close();
        rs.close();
        conn.close();

        return times;
    }

    private double[][] calculateAveregesAndFindBestAndWorstTimes(ResultSet rs) throws SQLException {
        double[][] times = new double[4][6];
        for (int i = 0; i < 6; i++) {
            times[1][i] = times[2][i] = times[3][i] = 0;
        }

        this.calculateAveregeTimes(times[1], rs);

        rs.beforeFirst();
        this.findBestTimes(times[2], rs);

        rs.beforeFirst();
        this.findWorstTimes(times[3], rs);

        return times;
    }

    private void calculateAveregeTimes(double[] times, ResultSet rs) throws SQLException {
        int prevId = 1, sum = 0, games = 0, curId, time;
        while (rs.next()) {
            curId = rs.getInt(3);
            time = rs.getInt(4);
            if (prevId != curId) {
                if (games != 0) {
                    times[prevId] = Math.round((1.0 * sum / games) * 10) / 10.0;
                }
                sum = games = 0;
            }
            sum += time;
            games++;
            prevId = curId;
        }
        if (games != 0) {
            times[prevId] = Math.round((1.0 * sum / games) * 10) / 10.0;
        }
    }

    private void findBestTimes(double[] times, ResultSet rs) throws SQLException {
        int curId, time;
        while (rs.next()) {
            curId = rs.getInt(3);
            time = rs.getInt(4);
            if (times[curId] == 0) {
                times[curId] = time;
            } else {
                times[curId] = Math.min(times[curId], time);
            }
        }
    }

    private void findWorstTimes(double[] times, ResultSet rs) throws SQLException {
        int curId, time;
        while (rs.next()) {
            curId = rs.getInt(3);
            time = rs.getInt(4);
            if (times[curId] == 0) {
                times[curId] = time;
            } else {
                times[curId] = Math.max(times[curId], time);
            }
        }
    }
}
