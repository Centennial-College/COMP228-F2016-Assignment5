package models;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @file GameDatabaseContext.java
 * @author Kevin Ma | #: 300867968
 * @date December 8, 2016
 * @version 0.4.4 added update functionality for GameView; deleted unused code
 * @description This class is used to bridge a connection between the database
 *              and the application.
 * 
 */

public class GameDatabaseContext {
	// INSTANCE VARIABLES
	// =============================================================================================
	public PreparedStatement pst;
	public Connection conn;
	public ResultSet rs;

	// since JDBC 4.0, DriverManager automatically loads and registers all
	// drivers, thus do not need the following statment and
	// Class.forName(DRIVER)
	// private static final String DRIVER =
	// "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DATABSE_URL = "jdbc:sqlserver://localhost:1433;";
	private static final String DATABASE_USER = "kevin";
	private static final String DATABASE_PASSWORD = "kevin";

	// CONSTRUCTOR
	// =============================================================================================
	public GameDatabaseContext() {
		// Initializes the connection with the SQL database
		try {
			conn = DriverManager.getConnection(DATABSE_URL, DATABASE_USER, DATABASE_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
