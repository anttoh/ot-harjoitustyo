package mazegame.domain;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mazegame.dao.DifficultyDao;
import mazegame.dao.GameDao;
import mazegame.dao.UserDao;

public class MazeGameService {

    private UserDao userDao;
    private GameDao gameDao;
    private DifficultyDao difficultyDao;
    private User loggedIn;
    private LayoutGenerator generator;
    private Cell[][] layout;
    private Maze maze;
    private int width;
    private int height;
    private String gameType;
    private boolean gameOngoing;
    double[] averegeSolveTimes;

    public MazeGameService() {
        this.gameType = null;
        this.loggedIn = null;
        this.userDao = new UserDao();
        this.difficultyDao = new DifficultyDao();
        this.gameDao = new GameDao();
        this.generator = new LayoutGenerator();
        this.gameOngoing = false;
        this.averegeSolveTimes = null;
    }

    public User getLoggedInUser() {
        return this.loggedIn;
    }

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

    public boolean login(String username, String password) {
        User user = new User(username, password);
        try {
            user = this.userDao.read(user);
        } catch (SQLException ex) {
            user = null;
            Logger.getLogger(MazeGameService.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.loggedIn = user;
        this.getLoggedInUsersAvereges();
        return user != null;
    }

    public void logout() {
        this.loggedIn = null;
    }

    public double[] getLoggedInUsersAveregeSolveTimes() {
        return this.averegeSolveTimes;
    }

    public void startGame(int width, int height) {
        if (width == height) {
            switch (width) {
                case 5:
                    this.gameType = "very easy";
                    break;
                case 10:
                    this.gameType = "easy";
                    break;
                case 20:
                    this.gameType = "medium";
                    break;
                case 40:
                    this.gameType = "hard";
                    break;
                case 80:
                    this.gameType = "ultra hard";
                    break;

            }
        }
        this.gameOngoing = true;
        this.width = width;
        this.height = height;
        this.initializeLayout();
        this.generator.generateMazeLayout(this.layout);
        this.maze = new Maze(this.layout);
    }

    public void endGame() {
        this.gameOngoing = false;
    }

    public void exitGame(int time) {
        if (this.goalReached() && this.gameType != null) {
            try {
                Difficulty difficulty = new Difficulty(this.gameType);
                if (this.difficultyDao.read(difficulty) == null) {
                    this.difficultyDao.create(difficulty);
                }
                difficulty = this.difficultyDao.read(difficulty);
                Game game = new Game(loggedIn, difficulty, time);
                this.gameDao.create(game);
            } catch (SQLException ex) {
                Logger.getLogger(MazeGameService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.getLoggedInUsersAvereges();
        this.gameType = null;
    }

    public boolean gameEnded() {
        return !this.gameOngoing;
    }

    public Cell getCellAtPos(int x, int y) {
        return this.layout[x][y];
    }

    public Cell mazeCurrentCell() {
        return this.maze.getCurrentCell();
    }

    public Cell mazeGoal() {
        return this.maze.getGoal();
    }

    public boolean goalReached() {
        return this.mazeCurrentCell() == this.mazeGoal();
    }

    public void goUp() {
        this.maze.moveUp();
    }

    public void goDown() {
        this.maze.moveDown();
    }

    public void goLeft() {
        this.maze.moveLeft();
    }

    public void goRight() {
        this.maze.moveRight();
    }

    public CellTypeForDrawing[][] getLayoutForDrawing() {
        CellTypeForDrawing[][] layoutForDrawing = new CellTypeForDrawing[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
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

    private void initializeLayout() {
        this.layout = new Cell[this.width][this.height];
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                this.layout[x][y] = new Cell(x, y);
            }
        }
    }

    private void getLoggedInUsersAvereges() {
        if (this.loggedIn != null) {
            try {
                this.averegeSolveTimes = this.userDao.getAveregeSolveTimesForEachDifficultyFromEasiest(loggedIn);
            } catch (SQLException ex) {
                Logger.getLogger(MazeGameService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
