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
 * @date December 10, 2016
 * @version 0.6.1 implemented Tabs automatically resetting when switching to
 *          different tab
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
	GameView gv;
	Tab gameTab;

	// Tab that allows manipulation of the Player table
	// =============================================================================================
	PlayerView pv;
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
		tabbedPane.getTabs().addAll(gameTab = (gv = new GameView()).getTab(),
				playerTab = (pv = new PlayerView()).getTab(), playerAndGameTab = new Tab("Player and Game"));
		tabbedPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tabbedPane.setStyle("-fx-background-color: #1d1d1d");

		// The tabs reset to their default values w hen they are not in focus
		tabbedPane.getSelectionModel().selectedItemProperty().addListener(e -> {
			gv.resetTab();
			pv.resetTab();
		});

		// Stage and scene configuration
		Scene scene = new Scene(tabbedPane);
		scene.getStylesheets().add("exercise1/DarkTheme.css");
		window.setScene(scene);
		window.setResizable(false);
		window.show();
	}
}
