package exercise1;

/**
 * @file PlayerAndGame.java
 * @author Kevin Ma | #: 300867968
 * @date December 3, 2016
 * @version 0.2.0 added models corresponding to the tables in the database
 * @description This class is a model for the PlayerAndGame table in the
 *              database.
 * 
 */

public class PlayerAndGame {
	// instance variables
	private int playerGameId;
	private int gameId;
	private int playerId;
	private String playingDate;
	private String score;

	// Public Properties
	// NOTE: only getters to prevent breaking of referential integrity
	public int getPlayerGameId() {
		return playerGameId;
	}

	public int getGameId() {
		return gameId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public String getPlayingDate() {
		return playingDate;
	}

	public String getScore() {
		return score;
	}

	// constructor
	public PlayerAndGame(int playerGameId, int gameId, int playerId, String playingDate, String score) {
		this.playerGameId = playerGameId;
		this.gameId = gameId;
		this.playerId = playerId;
		this.playingDate = playingDate;
		this.score = score;
	}

}
