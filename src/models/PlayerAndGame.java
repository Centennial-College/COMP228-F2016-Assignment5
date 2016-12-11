package models;

/**
 * @file PlayerAndGame.java
 * @author Kevin Ma | #: 300867968
 * @date December 10, 2016
 * @version 0.5.1 updated Models comments
 * @description This class is a model for the PlayerAndGame table in the
 *              database.
 * 
 */

public class PlayerAndGame {
	// INSTANCE VARIABLES
	// =============================================================================================
	private int playerGameId;
	private int gameId;
	private int playerId;
	private String playingDate;
	private String score;
	private Player player; // used to extract information to display
	private Game game; // used to extract information to display

	// PUBLIC PROPERTIES
	// =============================================================================================
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

	// CONSTRUCTOR
	// =============================================================================================
	public PlayerAndGame(int playerGameId, int gameId, int playerId, String playingDate, String score) {
		this.playerGameId = playerGameId;
		this.gameId = gameId;
		this.playerId = playerId;
		this.playingDate = playingDate;
		this.score = score;
	}

}
