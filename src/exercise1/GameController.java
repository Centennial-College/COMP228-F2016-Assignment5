package exercise1;

import javafx.collections.ObservableList;

public class GameController extends OnlineGameTrackerController {

	@Override
	ObservableList<?> selectAll() {
		ObservableList<GameModel> gameList;
		
		try{
			
		}catch(SQLException e){
			System.out.println("ERROR - Faild to select games");
		}
		
		return gameList;
	}

}
