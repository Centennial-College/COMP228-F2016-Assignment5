package exercise1;

import javafx.scene.control.Tab;

public class GameView extends OnlineGameTrackerView {
	public GameView() {
		super("Game");
	}

	@Override
	void resetTab() {
		// TODO Auto-generated method stub
		
	}

	public Tab getTab() {
		return this.tab;
	}
}
