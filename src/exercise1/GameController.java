package exercise1;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @file GameController.java
 * @author Kevin Ma | #: 300867968
 * @date December 8, 2016
 * @version 0.4.4 added update functionality for GameView; deleted unused code
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
	public ObservableList<GameModel> selectAll() {

		// temp variables
		ObservableList<GameModel> gameList;
		GameModel gameRecord;

		gameList = FXCollections.observableArrayList();

		try {
			db.pst = db.conn.prepareStatement("select * from [COMP228-F2016-OnlineGameTracker].[dbo].[Game];");
			db.rs = db.pst.executeQuery();
			while (db.rs.next()) {
				gameRecord = new GameModel(db.rs.getInt(1));
				gameRecord.setGameTitle(db.rs.getString(2));
				gameList.add(gameRecord);
			}
		} catch (SQLException e) {
			System.out.println("ERROR - Faild to select games");
		}
		return gameList;
	}

	public int insertIntoGame(String title) {
		// try {
		// pst = conn.prepareStatement(
		// "insert into [COMP228-F2016-OnlineGameTracker].[dbo].[Game]
		// (game_title) values(?)");
		// pst.setString(1, title);
		// return pst.executeUpdate();
		//
		// } catch (SQLException e) {
		// e.printStackTrace();
		// System.out.printf("ERROR - Faild to add %s to the GAME table.%n",
		// title);
		// }
		return 0;
	}

	public boolean updateGame(int id, String title, ObservableList<GameModel> gameList) {
		try {
			db.pst = db.conn.prepareStatement(
					"update [COMP228-F2016-OnlineGameTracker].[dbo].[Game] set game_title = ? where game_id = ?");
			db.pst.setString(1, title);
			db.pst.setInt(2, id);
			db.pst.executeUpdate();

			// if reached here, means the game was updated in db. so now need to
			// update in table's list

			return true;
		} catch (SQLException e) {
			return false;
		}
	}
}
