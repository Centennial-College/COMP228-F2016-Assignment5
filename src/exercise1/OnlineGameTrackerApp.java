package exercise1;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.stage.Stage;
import views.GameView;
import views.PlayerView;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;

/**
 * @file OnlineGameTracker.java
 * @author Kevin Ma | #: 300867968
 * @date December 8, 2016
 * @version 0.4.4 added update functionality for GameView; deleted unused code
 * @description This is the controller for the OnlineGameTracker application.
 *              This class implements a UI using JavaFX and allows the user to
 *              perform CRUD operations on the Player and Game Models in the
 *              database.
 * 
 */

public class OnlineGameTrackerApp extends Application {
	// INSTANCE VARIABLES
	// =============================================================================================
	Stage window;
	// TabPane is main container that is assigned -> Scene -> Stage
	TabPane tabbedPane;

	// Tab that allows manipulation of the Game table
	// =============================================================================================
	Tab gameTab;

	// Tab that allows manipulation of the Player table
	// =============================================================================================
	Tab playerTab;

	// Tab that allows manipulation of the PlayerAndGame table
	// =============================================================================================
	Tab playerAndGameTab;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Online Game Tracker");

		// Initialize Tabbed Pane and Tabs
		tabbedPane = new TabPane();
		tabbedPane.getTabs().addAll(gameTab = new GameView().getTab(), playerTab = new PlayerView().getTab(),
				playerAndGameTab = new Tab("Player and Game"));
		tabbedPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tabbedPane.setStyle("-fx-background-color: #1d1d1d");

	// Stage and scene configuration
	Scene scene = new Scene(
			tabbedPane);scene.getStylesheets().add("exercise1/DarkTheme.css");window.setScene(scene);window.setResizable(false);window.show();
}
}
