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
 * @date December 11, 2016
 * @version 1.0.0 initial release; can no longer add the same guy twice to same
 *          player
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
			db.pst = db.conn.prepareStatement("SELECT * FROM [COMP228-F2016-OnlineGameTracker].[dbo].[PlayerAndGame];");
			db.rs = db.pst.executeQuery();
			while (db.rs.next()) {
				playerAndGameRecord = new PlayerAndGame(db.rs.getInt(2), db.rs.getInt(3));
				playerAndGameRecord.setPlayingDate(db.rs.getString(4));
				playerAndGameRecord.setScore(db.rs.getString(5));
				playerAndGameList.add(playerAndGameRecord);
			}

			return playerAndGameList;
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Selects and returns games which have not already been added to the player
	 * passed in as parameter
	 * 
	 * @param pid
	 *            player_id
	 * @return list of all games not yet added to the player
	 */
	public ObservableList<Game> selectAllUnaddedGames(int pid) {
		// temp variables
		ObservableList<Game> gameList;
		Game g;

		gameList = FXCollections.observableArrayList();

		try {
			db.pst = db.conn.prepareStatement(
					"select game_id, game_title from [COMP228-F2016-OnlineGameTracker].[dbo].[Game] where game_id not in (select b.game_id from [COMP228-F2016-OnlineGameTracker].[dbo].[PlayerAndGame] as a join [COMP228-F2016-OnlineGameTracker].[dbo].[Game] as b on a.game_id = b.game_id where player_id = ?);");
			db.pst.setInt(1, pid);
			db.rs = db.pst.executeQuery();
			while (db.rs.next()) {
				g = new Game(db.rs.getInt(1));
				g.setGameTitle(db.rs.getString(2));
				gameList.add(g);
			}

			return gameList;
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
	public Player selectAPlayer(int id) {
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
	public Game selectAGame(int id) {
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

	/**
	 * Updates the row in the PlayerAndGame table that has matching details as
	 * the parameters.
	 * 
	 * @param date
	 *            playing_date
	 * @param score
	 *            score
	 * @param pid
	 *            playerid
	 * @param gid
	 *            gameid
	 * @return
	 */
	public boolean updatePlayerAndGame(String date, String score, int pid, int gid) {
		try {
			db.pst = db.conn.prepareStatement(
					"update [COMP228-F2016-OnlineGameTracker].[dbo].[PlayerAndGame] set score= ?, playing_date =? where player_id = ? and game_id=?");
			db.pst.setString(1, score);
			db.pst.setString(2, date);
			db.pst.setInt(3, pid);
			db.pst.setInt(4, gid);
			db.pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * Deletes the record matching the parameters from the PlayerAndGame table
	 * 
	 * @param pid
	 *            player_id
	 * @param gid
	 *            game_id
	 * @return true if successful, false otherwise
	 */
	public boolean deletePlayerAndGame(int pid, int gid) {
		try {
			db.pst = db.conn.prepareStatement(
					"delete from [COMP228-F2016-OnlineGameTracker].[dbo].[PlayerAndGame] where player_id = ? and game_id = ?");
			db.pst.setInt(1, pid);
			db.pst.setInt(2, gid);
			db.pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
}
