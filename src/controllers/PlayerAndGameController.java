package controllers;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Game;
import models.Player;
import models.PlayerAndGame;

/**
 * @file PlayerAndGameController.java
 * @author Kevin Ma | #: 300867968
 * @date December 10, 2016
 * @version 0.6.0 implemented Delete Player functionality, finished PlayerView
 * @description This class defines the behaviors of the PlayerAndGame view for
 *              this application at a micro level.
 */

public class PlayerAndGameController extends OnlineGameTrackerController {

	// PUBLIC METHODS
	// =============================================================================================
	public boolean insertIntoPlayerAndGame(int gameId, int playerId, String playingDate, String score) {
		try {
			db.pst = db.conn.prepareStatement(
					"insert into [COMP228-F2016-OnlineGameTracker].[dbo].[PlayerAndGame] (game_id,player_id,playing_date,score) values(?,?,?,?)");
			db.pst.setInt(1, gameId);
			db.pst.setInt(2, playerId);
			db.pst.setString(3, playingDate);
			db.pst.setString(4, score);
			db.pst.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Returns all records in the Player table as an ObservableList
	 */
	@Override
	public ObservableList<PlayerAndGame> selectAll() {
		// temp variables
		ObservableList<PlayerAndGame> playerAndGameList;
		PlayerAndGame playerAndGameRecord;

		playerAndGameList = FXCollections.observableArrayList();

		try {
			// db.pst = db.conn.prepareStatement("select * from
			// [COMP228-F2016-OnlineGameTracker].[dbo].[PlayerAndGame];");
			db.pst = db.conn.prepareStatement("SELECT * FROM [COMP228-F2016-OnlineGameTracker].[dbo].[PlayerAndGame];");
			db.rs = db.pst.executeQuery();
			while (db.rs.next()) {
				// System.out.println("ID #3 " + db.rs.getInt(3));
				playerAndGameRecord = new PlayerAndGame(db.rs.getInt(2), db.rs.getInt(3));
				playerAndGameRecord.setPlayingDate(db.rs.getString(4));
				playerAndGameRecord.setScore(db.rs.getString(5));
				// playerAndGameRecord.setGame(this.selectAGame(playerAndGameRecord.getGameId()));
				// playerAndGameRecord.setPlayer(this.selectAPlayer(playerAndGameRecord.getPlayerId()));
				playerAndGameList.add(playerAndGameRecord);
			}
			return playerAndGameList;
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Selects the player with the matching player_id from the parameters
	 * 
	 * @param id
	 *            the player's id
	 * @return the matching player, or null if not found
	 */
	private Player selectAPlayer(int id) {
		try {
			db.pst = db.conn.prepareStatement(
					"select * from [COMP228-F2016-OnlineGameTracker].[dbo].[Player] where player_id = ?");
			db.pst.setInt(1, id);
			db.rs = db.pst.executeQuery();
			while (db.rs.next()) {
				Player tmpPlayer = new Player(id);
				tmpPlayer.setFirstName(db.rs.getString(2));
				tmpPlayer.setLastName(db.rs.getString(3));
				tmpPlayer.setAddress(db.rs.getString(4));
				tmpPlayer.setPostalCode(db.rs.getString(5));
				tmpPlayer.setProvince(db.rs.getString(6));
				tmpPlayer.setPhoneNumber(db.rs.getString(7));
				return tmpPlayer;
			}
			return null;
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Selects the game with the matching game_id from the parameters
	 * 
	 * @param id
	 *            the game's id
	 * @return the matching game, or null if not found
	 */
	private Game selectAGame(int id) {
		try {
			db.pst = db.conn
					.prepareStatement("select * from [COMP228-F2016-OnlineGameTracker].[dbo].[Game] where game_id = ?");
			db.pst.setInt(1, id);
			db.rs = db.pst.executeQuery();
			while (db.rs.next()) {
				Game tmpGame = new Game(id);
				tmpGame.setGameTitle(db.rs.getString(2));
				return tmpGame;
			}
			return null;
		} catch (SQLException e) {
			return null;
		}
	}
}
