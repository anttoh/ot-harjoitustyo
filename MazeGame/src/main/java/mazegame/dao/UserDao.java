package mazegame.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import mazegame.domain.User;

/**
 * Class responsible for handling database actions concerning user.
 */
public class UserDao {

    /**
     * Method saves user to database.
     *
     * @param user user to be saved.
     *
     */
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

    /**
     * Method finds and returns user.
     *
     * @param user user with fields missing
     *
     * @return user with all fields set or null if user wasn't found
     */
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

    /**
     * Method deletes user and all their results from database
     *
     * @param user user to be deleted along with their results
     *
     */
    // note: this method is currently only used in tests to delete test users and their results, but it can be used in the actual application as well. 
    public void delete(User user) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./mazegame", "a", "");

        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Result"
                + " WHERE user_id = ?;");

        stmt.setLong(1, user.getId());
        stmt.executeUpdate();

        stmt = conn.prepareStatement("DELETE FROM User"
                + " WHERE id = ?;");

        stmt.setLong(1, user.getId());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    /**
     * Method calculates and returns single user's average, best, and word solve
     * times.
     *
     * @param user user who's solve times are wanted
     *
     * @return double[][] where first [1][difficulty] contains averages,
     * [2][difficulty] best and [3][difficulty] worst times, where difficulty is
     * int representing difficulty 1 = very easy and 6 = ultra hard
     */
    public double[][] getAverageBestAndWorstTimesForEachCategory(User user) throws SQLException {
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
