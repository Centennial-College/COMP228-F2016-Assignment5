package exercise1;

import java.sql.*;

/**
 * @file JDBCDatabaseManager.java
 * @author Kevin Ma | #: 300867968
 * @date December 2, 2016
 * @version 0.0.1
 * @description This class handles CRUD operations on the Game and Player tables
 *              in the database.
 *              
 */

public class JDBCDatabaseManager {

	// Instance variables
	private PreparedStatement storedProcedure;
	private Connection conn;

	// since JDBC 4.0, DriverManager automatically loads and registers all
	// drivers, thus do not need the following statment and
	// Class.forName(DRIVER)
	// private static final String DRIVER
	// ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DATABSE_URL = "jdbc:sqlserver://localhost:1433;";
	private static final String DATABASE_USER = "kevin";
	private static final String DATABASE_PASSWORD = "kevin";

	// Constructors
	public JDBCDatabaseManager() {
		// Initializes the connection with the SQL database
		try {
			conn = DriverManager.getConnection(DATABSE_URL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Public Methods
	// CUD for Game database
	public void insertIntoGame(String title) {

	}

	// CUD for Player database
	public void insertIntoPlayer(String fname, String lname, String addr, String pcode, String prov, String phone) {

	}

	// R for players and played games information
	public void displayPlayerAndPlayedGames() {

	}
}
