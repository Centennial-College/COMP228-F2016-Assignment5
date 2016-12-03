package exercise1;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @file JavaFXDatabaseGUI.java
 * @author Kevin Ma | #: 300867968
 * @date December 3, 2016
 * @version 0.0.3 added TitledPanes to every TabPane
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

		// GAME PANE
		// creating and configuring titled panes
		TitledPane insertGameTitledPane = new TitledPane();
		insertGameTitledPane.setTooltip(new Tooltip("Open this section to add a new game to the database!"));
		insertGameTitledPane.setExpanded(false);
		insertGameTitledPane.setText("Add New Game");
		insertGameTitledPane.setContent(new Label("TEST"));

		TitledPane removeGameTitledPane = new TitledPane();
		removeGameTitledPane.setTooltip(new Tooltip("Open this section to remove a game from the database!"));
		removeGameTitledPane.setExpanded(false);
		removeGameTitledPane.setText("Remove A Game");
		removeGameTitledPane.setContent(new Label("TEST"));

		TitledPane viewGameTitledPane = new TitledPane();
		viewGameTitledPane.setTooltip(new Tooltip("Open this section to view games in the database!"));
		viewGameTitledPane.setExpanded(false);
		viewGameTitledPane.setText("View All Games");
		viewGameTitledPane.setContent(new Label("TEST"));

		// adding titled panes
		gamePane.add(insertGameTitledPane, 0, 0);
		gamePane.add(removeGameTitledPane, 0, 1);
		gamePane.add(viewGameTitledPane, 0, 2);

		// PLAYER PANE
		// creating and configuring titled panes
		TitledPane insertPlayerTitledPane = new TitledPane();
		insertPlayerTitledPane.setTooltip(new Tooltip("Open this section to add a new player to the database!"));
		insertPlayerTitledPane.setExpanded(false);
		insertPlayerTitledPane.setText("Add New Player");
		insertPlayerTitledPane.setContent(new Label("TEST"));

		TitledPane removePlayerTitledPane = new TitledPane();
		removePlayerTitledPane.setTooltip(new Tooltip("Open this section to remove a player from the database!"));
		removePlayerTitledPane.setExpanded(false);
		removePlayerTitledPane.setText("Remove A Player");
		removePlayerTitledPane.setContent(new Label("TEST"));

		TitledPane viewPlayerTitledPane = new TitledPane();
		viewPlayerTitledPane.setTooltip(new Tooltip("Open this section to view players in the database!"));
		viewPlayerTitledPane.setExpanded(false);
		viewPlayerTitledPane.setText("View All Players");
		viewPlayerTitledPane.setContent(new Label("TEST"));

		// adding titled panes
		playerPane.add(insertPlayerTitledPane, 0, 0);
		playerPane.add(removePlayerTitledPane, 0, 1);
		playerPane.add(viewPlayerTitledPane, 0, 2);

		// PLAYER AND GAME PANE
		// creating and configuring titled panes
		TitledPane viewPlayerAndGameTitledPane = new TitledPane();
		viewPlayerAndGameTitledPane.setTooltip(
				new Tooltip("Open this section to view the games and players who played them\nin the database!"));
		viewPlayerAndGameTitledPane.setExpanded(false);
		viewPlayerAndGameTitledPane.setText("View Games and its Players");
		viewPlayerAndGameTitledPane.setContent(new Label("TEST"));

		// adding titled panes
		playerAndGamePane.add(viewPlayerAndGameTitledPane, 0, 0);

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
