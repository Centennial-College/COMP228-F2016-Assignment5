package controllers;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Game;

/**
 * @file GameController.java
 * @author Kevin Ma | #: 300867968
 * @date December 9, 2016
 * @version 0.5.4 added prevention of adding multiple games with the same title
 * @description This class defines the behaviors of the Game view for this
 *              application at a micro level.
 */

public class GameController extends OnlineGameTrackerController {

	// PUBLIC METHODS
	// =============================================================================================
	/**
	 * Returns all records in the Game table as an ObservableList
	 */
	@Override
	public ObservableList<Game> selectAll() {
		// temp variables
		ObservableList<Game> gameList;
		Game gameRecord;

		gameList = FXCollections.observableArrayList();

		try {
			db.pst = db.conn.prepareStatement("select * from [COMP228-F2016-OnlineGameTracker].[dbo].[Game];");
			db.rs = db.pst.executeQuery();
			while (db.rs.next()) {
				gameRecord = new Game(db.rs.getInt(1));
				gameRecord.setGameTitle(db.rs.getString(2));
				gameList.add(gameRecord);
			}
		} catch (SQLException e) {
			System.out.println("ERROR - Faild to select games");
		}
		return gameList;
	}

	/**
	 * Checks the Game table to see if a record with the parameter title already
	 * exists.
	 * 
	 * @param title
	 *            the title of the game to be searched for
	 * @return true if a game is found with the same title, false otherwise
	 */
	private boolean checkIfAGameExists(String title) {
		try {
			db.pst = db.conn.prepareStatement(
					"select * from [COMP228-F2016-OnlineGameTracker].[dbo].[Game] where game_title = ?");
			db.pst.setString(1, title);
			db.rs = db.pst.executeQuery();
			while (db.rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * Inserts a row into the Game table. The game_id is auto generated by a
	 * sequence, and the row's game_title is taken from the parameter. Game
	 * titles must be unique.
	 * 
	 * @param title
	 *            the game_title to be inserted into the game
	 * @return true if the insert was successful, false otherwise
	 */
	public boolean insertIntoGame(String title) {
		try {
			if (!this.checkIfAGameExists(title)) {
				db.pst = db.conn.prepareStatement(
						"insert into [COMP228-F2016-OnlineGameTracker].[dbo].[Game] (game_title) values(?)");
				db.pst.setString(1, title);
				db.pst.executeUpdate();
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Updates the row in the Game table that has a matching game_id and
	 * game_title as the parameters. Game titles must be unique.
	 * 
	 * @param id
	 *            the game_id of the row to be updated
	 * @param title
	 *            the game_title of the row to be updated
	 * @return true if the update was successful, false otherwise
	 */
	public boolean updateGame(int id, String title) {
		try {
			if (!this.checkIfAGameExists(title)) {

				db.pst = db.conn.prepareStatement(
						"update [COMP228-F2016-OnlineGameTracker].[dbo].[Game] set game_title = ? where game_id = ?");
				db.pst.setString(1, title);
				db.pst.setInt(2, id);
				db.pst.executeUpdate();
				return true;
			}
			return false;
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * Deletes the row in the Game table that has a matching game_id as the
	 * parameter. Don't need game_title because game_id is the primary key.
	 * 
	 * @param id
	 *            the game_id of the row to be updated
	 * @return true if the deletion was successful, false otherwise
	 */
	public boolean deleteGame(int id) {
		try {
			db.pst = db.conn
					.prepareStatement("delete from [COMP228-F2016-OnlineGameTracker].[dbo].[Game] where game_id = ?");
			db.pst.setInt(1, id);
			db.pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
}