package exercise1;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @file GameController.java
 * @author Kevin Ma | #: 300867968
 * @date December 8, 2016
 * @version 0.4.2 added viewing functionality to the GameView table
 * @description This class defines the behaviors of the Game view for this
 *              application at a micro level.
 */

public class GameController extends OnlineGameTrackerController {

	// PUBLIC METHODS
	// =============================================================================================
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

}
