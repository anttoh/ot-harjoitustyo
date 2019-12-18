package mazegame.domain;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import mazegame.dao.DifficultyDao;
import mazegame.dao.ResultDao;
import mazegame.dao.UserDao;

/**
 * Class responsible for acting as an interface for the UI, offering public
 * methods handling all the applications logic.
 */
public class MazeGameService {

    private final UserDao userDao;
    private final ResultDao resultDao;
    private final DifficultyDao difficultyDao;
    private User loggedIn;
    private Maze maze;
    private Difficulty difficulty;
    private boolean gameOngoing;
    private boolean showPath;
    private double[][] solveTimes;

    public MazeGameService() {
        this.userDao = new UserDao();
        this.resultDao = new ResultDao();
        this.difficultyDao = new DifficultyDao();
    }

    /**
     * Method returns user that is currently logged in.
     *
     * @return logged in user
     */
    public User getLoggedInUser() {
        return this.loggedIn;
    }

    /**
     * Method creates a new user and return true if succeeded and false
     * otherwise
     *
     * @param username users chosen username
     * @param password users chosen password
     *
     * @return false if username was taken and true if it was available
     */
    public boolean register(String username, String password) {
        User user = new User(username, password);
        try {
            this.userDao.create(user);
        } catch (SQLException ex) {
            // username was taken
            return false;
        }
        return true;
    }

    /**
     * Method logs in to the application if given username and password are
     * correct
     *
     * @param username user given username
     * @param password user given password for the username
     *
     * @return true if username and password were correct and false otherwise
     */
    public boolean login(String username, String password) {
        User user = new User(username, password);
        try {
            user = this.userDao.read(user);
        } catch (SQLException ex) {
            user = null;
            Logger.getLogger(MazeGameService.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.loggedIn = user;
        this.getLoggedInUsersAveregeBestAndWorstTimes();
        return user != null;
    }

    /**
     * Method logs the user out of the application. Sets the logged in user to
     * null
     */
    public void logout() {
        this.loggedIn = null;
    }

    /**
     * Method returns an array that contains average times it took the user to
     * solve mazes of certain difficulty. Array contains average solve time for
     * each of the predefined difficulty settings.
     *
     * @return double[] that contains the average solve times for each
     * difficulty. 1 = very easy and 5 = ultra hard
     */
    public double[] getLoggedInUsersAveregeSolveTimes() {
        return this.solveTimes[1];
    }

    /**
     * Method returns an array that contains best times it took the user to
     * solve mazes of certain difficulty. Array contains best solve time for
     * each of the predefined difficulty settings.
     *
     * @return double[] that contains the best solve times for each difficulty.
     * 1 = very easy and 5 = ultra hard
     */
    public double[] getLoggedInUsersBestSolveTimes() {
        return this.solveTimes[2];
    }

    /**
     * Method returns an array that contains worst times it took the user to
     * solve mazes of certain difficulty. Array contains worst solve time for
     * each of the predefined difficulty settings.
     *
     * @return double[] that contains the worst solve times for each difficulty.
     * 1 = very easy and 5 = ultra hard
     */
    public double[] getLoggedInUsersWorstSolveTimes() {
        return this.solveTimes[3];
    }

    /**
     * Method sets showPath to given boolean value. If it's set to true, then
     * the user will be show the cells they have already visited.
     *
     * @param bool users chosen boolean value
     */
    public void setShowPath(boolean bool) {
        this.showPath = bool;
    }

    /**
     * Method starts a new game. It creates a maze of given size and sets game
     * to ongoing.
     *
     * @param width width of the maze
     * @param height height of the maze
     */
    public void startGame(int width, int height) {
        this.gameOngoing = true;
        this.maze = new Maze(width, height);
        this.setDifficulty(width, height);
    }

    /**
     * Method ends the ongoing game but doesn't exit the game. This method
     * simply sets boolean value to false, which indicates whether or not the
     * game is ongoing.
     */
    public void endGame() {
        this.gameOngoing = false;
    }

    /**
     * Method exits the game and saves the time it took to finish the maze to
     * the database if the goal of the maze was reached and the difficulty was
     * one of the predefined ones.
     *
     * @param time time it took to finish the maze
     */
    public void exitGame(int time) {
        if (this.goalReached() && this.difficulty != null) {
            try {
                this.difficulty = this.difficultyDao.read(this.difficulty);
                Result result = new Result(this.loggedIn, this.difficulty, time);
                this.resultDao.create(result);
            } catch (SQLException ex) {
                Logger.getLogger(MazeGameService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.getLoggedInUsersAveregeBestAndWorstTimes();
        this.difficulty = null;
    }

    /**
     * Method tells whether or not the game has ended.
     *
     * @return false if the game is ongoing and true otherwise
     */
    public boolean gameEnded() {
        return !this.gameOngoing;
    }

    /**
     * Method gets a Cell in given x and y coordinates of the maze.
     *
     * @param x x coordinate. 0 is leftmost coordinate
     * @param y y coordinate. 0 is topmost coordinate
     *
     * @return Cell in the given coordinates
     */
    public Cell getCellAtPos(int x, int y) {
        return this.maze.getLayout()[x][y];
    }

    /**
     * Method gets the Cell that the players character is currently on.
     *
     * @return Cell which the player is currently on
     */
    public Cell mazeCurrentCell() {
        return this.maze.getCurrentCell();
    }

    /**
     * Method returns the goal of the maze. Goal is the bottom right corner of
     * the maze.
     *
     * @return Cell that is the goal.
     */
    public Cell mazeGoal() {
        return this.maze.getGoal();
    }

    /**
     * Method tells whether or not the goal of the maze has been reached
     *
     * @return true if the users character is on the goal Cell and false
     * otherwise
     */
    public boolean goalReached() {
        return this.mazeCurrentCell() == this.mazeGoal();
    }

    /**
     * Method returns true, if the cell given as parameter has been visited by
     * the player and showPath is true (player chose to have visited cells be
     * highlighted).
     *
     * @param cell the cell in question
     *
     * @return true if the cell has been visited and showPath is true, and false
     * otherwise.
     */
    public boolean hasCellBeenVisited(Cell cell) {
        return this.showPath && this.maze.visited(cell);
    }

    /**
     * Method moves the users character up one Cell if possible.
     */
    public void goUp() {
        this.maze.moveUp();
    }

    /**
     * Method moves the users character down one Cell if possible.
     */
    public void goDown() {
        this.maze.moveDown();
    }

    /**
     * Method moves the users character left one Cell if possible.
     */
    public void goLeft() {
        this.maze.moveLeft();
    }

    /**
     * Method moves the users character right one Cell if possible.
     */
    public void goRight() {
        this.maze.moveRight();
    }

    /**
     * Method returns a layout of the maze as a 2d array of enum type
     * CellTypeForDrawing. This layout makes it simple to draw the maze in UI.
     *
     * @return maze's layout in a format that makes it easy for UI to draw the
     * maze
     */
    public CellTypeForDrawing[][] getLayoutForDrawing() {
        CellTypeForDrawing[][] layoutForDrawing = new CellTypeForDrawing[this.maze.getLayout().length][this.maze.getLayout()[0].length];
        for (int i = 0; i < this.maze.getLayout().length; i++) {
            for (int j = 0; j < this.maze.getLayout()[0].length; j++) {
                Cell curCell = this.getCellAtPos(i, j);
                if (curCell.getRight() == curCell.getDown()) {
                    layoutForDrawing[i][j] = CellTypeForDrawing.HAS_NEITHER_RIGHT_NOR_DOWN_NEIGHBOUR;
                } else if (curCell.getRight() != curCell) {
                    if (curCell.getDown() != curCell) {
                        layoutForDrawing[i][j] = CellTypeForDrawing.HAS_BOTH_RIGHT_AND_DOWN_NEIGHBOUR;
                    } else {
                        layoutForDrawing[i][j] = CellTypeForDrawing.HAS_RIGHT_NEIGHBOUR;
                    }
                } else {
                    layoutForDrawing[i][j] = CellTypeForDrawing.HAS_DOWN_NEIGHBOUR;
                }
            }
        }
        return layoutForDrawing;
    }

    /**
     * Method returns the name of the current difficulty or "custom", if current
     * difficulty is null.
     *
     * @return difficulty settings name or "custom", if it's null
     */
    public String getDifficultyName() {
        return this.difficulty == null ? "custom" : this.difficulty.getName();
    }

    private void getLoggedInUsersAveregeBestAndWorstTimes() {
        if (this.loggedIn != null) {
            try {
                this.solveTimes = this.userDao.getAveregeSolveTimesForEachDifficultyFromEasiestAndBestAndWorstTimes(loggedIn);
            } catch (SQLException ex) {
                Logger.getLogger(MazeGameService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void setDifficulty(int width, int height) {
        if (width == height) {
            if (width == 5) {
                this.difficulty = new Difficulty("very easy");
            } else if (width == 10) {
                this.difficulty = new Difficulty("easy");
            } else if (width == 20) {
                this.difficulty = new Difficulty("medium");
            } else if (width == 40) {
                this.difficulty = new Difficulty("hard");
            } else if (width == 80) {
                this.difficulty = new Difficulty("ultra hard");
            }
        }
    }
}
