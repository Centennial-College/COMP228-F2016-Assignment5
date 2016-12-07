package exercise1;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @file 		GameDatabaseContext.java
 * @author 		Kevin Ma | #: 300867968
 * @date 		December 4, 2016
 * @version 	0.3.3 selecting a row in the gameTable populates the modify/remove
 *          	textfields
 * @description This class handles CRUD operations on the Game, Player, and
 *              PlayerAndGame tables in the database.
 * 
 */

public class GameDatabaseContext {

	// Instance variables
	public PreparedStatement pst;
	public Connection conn;
	public ResultSet rs;
	
	private ObservableList<GameModel> gameList;
	private GameModel gameRecord;

	// since JDBC 4.0, DriverManager automatically loads and registers all
	// drivers, thus do not need the following statment and
	// Class.forName(DRIVER)
	// private static final String DRIVER =
	// "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DATABSE_URL = "jdbc:sqlserver://localhost:1433;";
	private static final String DATABASE_USER = "kevin";
	private static final String DATABASE_PASSWORD = "kevin";

	// Constructors
	public GameDatabaseContext() {
		// Initializes the connection with the SQL database
		try {
			gameList = FXCollections.observableArrayList();
			conn = DriverManager.getConnection(DATABSE_URL, DATABASE_USER, DATABASE_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Public Methods
	// CRUD for Game database
	public ObservableList<GameModel> selectAllFromGame() {
		gameList.clear();
		try {
			pst = conn.prepareStatement("select * from [COMP228-F2016-OnlineGameTracker].[dbo].[Game];");
			rs = pst.executeQuery();
			while (rs.next()) {
				gameRecord = new GameModel(rs.getInt(1));
				gameRecord.setGameTitle(rs.getString(2));
				gameList.add(gameRecord);
			}
		} catch (SQLException e) {
			System.out.println("ERROR - Faild to select games");
		}
		return gameList;
	}

	// public Game selectAGame(int id) {
	// Game g = null;
	// try {
	// pst = conn.prepareStatement(
	// "select * from [COMP228-F2016-OnlineGameTracker].[dbo].[Game] where
	// [game_id] = ?");
	// pst.setInt(1, id);
	// rs = pst.executeQuery();
	// rs.next();
	// g = new Game(rs.getInt(1));
	// g.setGameTitle(rs.getString(1));
	// } catch (SQLException e) {
	// System.out.println("ERROR - Faild to select game with id " + id);
	// }
	// return g;
	// }

	public boolean updateGame(int id, String title) {
		try {
			pst = conn.prepareStatement(
					"update [COMP228-F2016-OnlineGameTracker].[dbo].[Game] set game_title = ? where game_id = ?");
			pst.setString(1, title);
			pst.setInt(2, id);
			pst.executeUpdate();
			return true;
			// return title;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.printf("ERROR - Faild to update %s in the GAME table.%n", title);
		}
		return false;
	}

	public int insertIntoGame(String title) {
		try {
			pst = conn.prepareStatement(
					"insert into [COMP228-F2016-OnlineGameTracker].[dbo].[Game] (game_title) values(?)");
			pst.setString(1, title);
			return pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.printf("ERROR - Faild to add %s to the GAME table.%n", title);
		}
		return 0;
	}

	// CRUD for Player database
	public void insertIntoPlayer(String fname, String lname, String addr, String pcode, String prov, String phone) {

	}

	// R for players and played games information
	public void displayPlayerAndPlayedGames() {

	}
}
