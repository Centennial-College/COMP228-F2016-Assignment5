package exercise1;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @file JavaFXDatabaseGUI.java
 * @author Kevin Ma | #: 300867968
 * @date December 3, 2016
 * @version 0.0.2 added TabPane to GUI
 * @description This class implements a UI using JavaFX and allows the user to
 *              perform CRUD operations on the Player and Game tables in the
 *              database.
 * 
 */

public class JavaFXDatabaseGUI extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TabPane, JTabbedPane equivalent, configuration
		TabPane tabbedPane = new TabPane();
		Tab gameTab = new Tab();
		Tab playerTab = new Tab();
		Tab playerAndGameTab = new Tab();

		// GridPanes configuration
		GridPane gamePane = new GridPane();
		gamePane.setAlignment(Pos.CENTER);
		gamePane.setHgap(55);
		gamePane.setVgap(10);
		gamePane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

		GridPane playerPane = new GridPane();
		playerPane.setAlignment(Pos.CENTER);
		playerPane.setHgap(55);
		playerPane.setVgap(10);
		playerPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

		GridPane playerAndGamePane = new GridPane();
		playerAndGamePane.setAlignment(Pos.CENTER);
		playerAndGamePane.setHgap(55);
		playerAndGamePane.setVgap(10);
		playerAndGamePane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

		// adding tabs to tabbed pane
		tabbedPane.getTabs().add(gameTab);
		tabbedPane.getTabs().add(playerTab);
		tabbedPane.getTabs().add(playerAndGameTab);

		// configuration of the tabs
		gameTab.setContent(gamePane);
		gameTab.setText("Game");
		gameTab.setTooltip(new Tooltip("Click on this tab to add, remove and view games!"));

		playerTab.setContent(playerPane);
		playerTab.setText("Player");
		playerTab.setTooltip(new Tooltip("Click on this tab to add, remove and view players!"));

		playerAndGameTab.setContent(playerAndGamePane);
		playerAndGameTab.setText("Players and Games");
		playerAndGameTab.setTooltip(new Tooltip("Click on this view the games each player has played!"));

		// Stage configuration and adding the scene to the stage
		Scene scene = new Scene(tabbedPane);
		primaryStage.setTitle("Game Management Application");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
