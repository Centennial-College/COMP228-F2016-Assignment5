package exercise1;

import javafx.application.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * @file JavaFXDatabaseGUI.java
 * @author Kevin Ma | #: 300867968
 * @date December 3, 2016
 * @version 0.2.2 implemented selectFromGame functionality
 * @description This class implements a UI using JavaFX and allows the user to
 *              perform CRUD operations on the Player and Game tables in the
 *              database.
 * 
 */

public class JavaFXDatabaseGUI extends Application {
	// INSTANCE VARIABLES
	// =============================================================================================
	// TabPane is main container that is assigned -> Scene -> primaryStage
	TabPane tabbedPane;
	GameDatabaseContext db;

	// Tabs -> TabPane
	// Variables for Game View of Application
	// ---------------------------------------------------------------------------------------------
	Tab gameTab;
	GridPane gamePane;

	TitledPane insertGameTitledPane;
	GridPane insertGameGridPane;
	Button insertGameBtn;
	TextField gameTitleTextField;
	Label insertGameLabel;

	TitledPane removeGameTitledPane;

	TitledPane viewGameTitledPane;
	GridPane viewGameGridPane;
	TableView<Game> gameTable;
	TableColumn<Game, Integer> gameIdTableColumn;
	TableColumn<Game, String> gameTitleTableColumn;
	TableColumn gameUpdateTableColumn;

	// Variables for Player View of Application
	// ---------------------------------------------------------------------------------------------
	Tab playerTab;
	GridPane playerPane;
	TitledPane insertPlayerTitledPane;
	TitledPane removePlayerTitledPane;
	TitledPane viewPlayerTitledPane;

	// Variables for PlayerAndGame View of Application
	// ---------------------------------------------------------------------------------------------
	Tab playerAndGameTab;
	GridPane playerAndGamePane;
	TitledPane viewPlayerAndGameTitledPane;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// UI SETUP
		// =============================================================================================
		// TabPane, JTabbedPane equivalent, configuration
		tabbedPane = new TabPane();
		gameTab = new Tab();
		playerTab = new Tab();
		playerAndGameTab = new Tab();
		db = new GameDatabaseContext();

		// GridPanes configuration
		gamePane = new GridPane();
		gamePane.setAlignment(Pos.CENTER);
		gamePane.setHgap(55);
		gamePane.setVgap(10);
		gamePane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

		playerPane = new GridPane();
		playerPane.setAlignment(Pos.CENTER);
		playerPane.setHgap(55);
		playerPane.setVgap(10);
		playerPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

		playerAndGamePane = new GridPane();
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
		gameTab.setText("Games");
		gameTab.setTooltip(new Tooltip("Click on this tab to add, remove and view games!"));

		playerTab.setContent(playerPane);
		playerTab.setText("Players");
		playerTab.setTooltip(new Tooltip("Click on this tab to add, remove and view players!"));

		playerAndGameTab.setContent(playerAndGamePane);
		playerAndGameTab.setText("Players and Games");
		playerAndGameTab.setTooltip(new Tooltip("Click on this view the games each player has played!"));

		// prevent closing of tabs
		tabbedPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		// GAME PANE
		// =============================================================================================
		// creating and configuring titled panes
		// add titled pane
		// ---------------------------------------------------------------------------------------------
		insertGameTitledPane = new TitledPane();
		insertGameTitledPane.setTooltip(new Tooltip("Open this section to add a new game to the database!"));
		insertGameTitledPane.setExpanded(false);
		insertGameTitledPane.setText("Add New Game");

		// configuring insert game grid pane
		insertGameTitledPane.setContent(insertGameGridPane = new GridPane());
		insertGameGridPane.setAlignment(Pos.CENTER);
		insertGameGridPane.setHgap(5);
		insertGameGridPane.setVgap(5);
		insertGameGridPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

		// adding contents of insert game grid pane
		insertGameGridPane.add(new Label("Game Title: "), 0, 0);
		insertGameGridPane.add(gameTitleTextField = new TextField(), 1, 0);
		insertGameGridPane.add(insertGameBtn = new Button("Add Game"), 2, 0);
		insertGameGridPane.add(insertGameLabel = new Label(), 0, 1);
		insertGameLabel.setManaged(false);
		insertGameGridPane.setColumnSpan(insertGameLabel, 3);

		// adding event listeners
		insertGameBtn.setTooltip(
				new Tooltip("Click on the button to add the game with the entered title into the database!"));
		insertGameBtn.setOnAction(e -> {
			// insertGameLabel.setText("Added new game!");
			insertGameLabel
					.setText(String.format("Added %d new game!", db.insertIntoGame(gameTitleTextField.getText())));

			insertGameLabel.setManaged(true);
		});

		// remove titled pane
		// ---------------------------------------------------------------------------------------------
		removeGameTitledPane = new TitledPane();
		removeGameTitledPane.setTooltip(new Tooltip("Open this section to remove a game from the database!"));
		removeGameTitledPane.setExpanded(false);
		removeGameTitledPane.setText("Remove A Game");
		removeGameTitledPane.setContent(new Label("TEST"));

		// view titled pane
		// ---------------------------------------------------------------------------------------------
		viewGameTitledPane = new TitledPane();
		viewGameTitledPane.setTooltip(new Tooltip("Open this section to view games in the database!"));
		viewGameTitledPane.setExpanded(false);
		viewGameTitledPane.setText("View All Games");

		// configuring insert game grid pane
		viewGameTitledPane.setContent(viewGameGridPane = new GridPane());
		viewGameGridPane.setAlignment(Pos.CENTER);
		viewGameGridPane.setHgap(5);
		viewGameGridPane.setVgap(5);
		viewGameGridPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

		// adding contents of view game grid pane
		viewGameGridPane.add(gameTable = new TableView(), 0, 0);
		// configuring TableView
		gameTable.setMinWidth(317);
		gameTable.getColumns().addAll(gameIdTableColumn = new TableColumn("Game Id"),
				gameTitleTableColumn = new TableColumn("Game Title"),
				gameUpdateTableColumn = new TableColumn("Game Update"));

		// gameid column
		gameIdTableColumn.setCellValueFactory(new PropertyValueFactory<Game, Integer>("gameId"));

		// gametitle column
		gameTitleTableColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("gameTitle"));

		// gameUpdateTableColumn.setCellValueFactory(new
		// PropertyValueFactory<Game, Button>());

		// allows editing
		gameTable.setEditable(true);
		gameTitleTableColumn.setEditable(true);
		gameTitleTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		gameTitleTableColumn.setOnEditCommit(new EventHandler<CellEditEvent<Game, String>>() {
			@Override
			public void handle(CellEditEvent<Game, String> g) {
				((Game) g.getTableView().getItems().get(g.getTablePosition().getRow())).setGameTitle(g.getNewValue());
			}
		});

		// gameTab.

		// data src binding
		gameTable.setItems(db.selectFromGame());

		// adding event handlers for GAME PANE
		// ---------------------------------------------------------------------------------------------
		// Collapse other titled panes when expanding current one
		insertGameTitledPane.expandedProperty().addListener(e -> {
			if (insertGameTitledPane.isExpanded()) {
				removeGameTitledPane.setExpanded(false);
				viewGameTitledPane.setExpanded(false);
			}
		});

		removeGameTitledPane.expandedProperty().addListener(e -> {
			if (removeGameTitledPane.isExpanded()) {
				insertGameTitledPane.setExpanded(false);
				viewGameTitledPane.setExpanded(false);
			}
		});

		viewGameTitledPane.expandedProperty().addListener(e -> {
			if (viewGameTitledPane.isExpanded()) {
				gameTable.setItems(db.selectFromGame());

				removeGameTitledPane.setExpanded(false);
				insertGameTitledPane.setExpanded(false);
			}
		});

		// collapse all panes when selecting another tab
		gameTab.selectedProperty().addListener(e -> {
			if (!gameTab.isSelected()) {
				insertGameTitledPane.setExpanded(false);
				removeGameTitledPane.setExpanded(false);
				viewGameTitledPane.setExpanded(false);
			}
		});

		// adding titled panes
		gamePane.add(viewGameTitledPane, 0, 0);
		gamePane.add(insertGameTitledPane, 0, 1);
		gamePane.add(removeGameTitledPane, 0, 2);

		// PLAYER PANE
		// ===========================================================================================
		// creating and configuring titled panes
		insertPlayerTitledPane = new TitledPane();
		insertPlayerTitledPane.setTooltip(new Tooltip("Open this section to add a new player to the database!"));
		insertPlayerTitledPane.setExpanded(false);
		insertPlayerTitledPane.setText("Add New Player");
		insertPlayerTitledPane.setContent(new Label("TEST"));

		removePlayerTitledPane = new TitledPane();
		removePlayerTitledPane.setTooltip(new Tooltip("Open this section to remove a player from the database!"));
		removePlayerTitledPane.setExpanded(false);
		removePlayerTitledPane.setText("Remove A Player");
		removePlayerTitledPane.setContent(new Label("TEST"));

		viewPlayerTitledPane = new TitledPane();
		viewPlayerTitledPane.setTooltip(new Tooltip("Open this section to view players in the database!"));
		viewPlayerTitledPane.setExpanded(false);
		viewPlayerTitledPane.setText("View All Players");
		viewPlayerTitledPane.setContent(new Label("TEST"));

		// adding event handlers for PLAYER PANE
		// ---------------------------------------------------------------------------------------------
		// Collapse other titled panes when expanding current one
		insertPlayerTitledPane.expandedProperty().addListener(e -> {
			if (insertPlayerTitledPane.isExpanded()) {
				removePlayerTitledPane.setExpanded(false);
				viewPlayerTitledPane.setExpanded(false);
			}
		});

		removePlayerTitledPane.expandedProperty().addListener(e -> {
			if (removePlayerTitledPane.isExpanded()) {
				insertPlayerTitledPane.setExpanded(false);
				viewPlayerTitledPane.setExpanded(false);
			}
		});

		viewPlayerTitledPane.expandedProperty().addListener(e -> {
			if (viewPlayerTitledPane.isExpanded()) {
				removePlayerTitledPane.setExpanded(false);
				insertPlayerTitledPane.setExpanded(false);
			}
		});

		// collapse all panes when selecting another tab
		playerTab.selectedProperty().addListener(e -> {
			if (!playerTab.isSelected()) {
				insertPlayerTitledPane.setExpanded(false);
				removePlayerTitledPane.setExpanded(false);
				viewPlayerTitledPane.setExpanded(false);
			}
		});

		// adding titled panes
		playerPane.add(viewPlayerTitledPane, 0, 0);
		playerPane.add(insertPlayerTitledPane, 0, 1);
		playerPane.add(removePlayerTitledPane, 0, 2);

		// PLAYER AND GAME PANE
		// ===================================================================================
		// creating and configuring titled panes
		viewPlayerAndGameTitledPane = new TitledPane();
		viewPlayerAndGameTitledPane.setTooltip(
				new Tooltip("Open this section to view the games and players who played them\nin the database!"));
		viewPlayerAndGameTitledPane.setExpanded(false);
		viewPlayerAndGameTitledPane.setText("View Games and its Players");
		viewPlayerAndGameTitledPane.setContent(new Label("TEST"));

		// collapse all panes when selecting another tab
		playerAndGameTab.selectedProperty().addListener(e -> {
			if (!playerAndGameTab.isSelected()) {
				viewPlayerAndGameTitledPane.setExpanded(false);
			}
		});

		// adding titled panes
		playerAndGamePane.add(viewPlayerAndGameTitledPane, 0, 0);

		// Stage configuration and adding the scene to the stage
		Scene scene = new Scene(tabbedPane);
		primaryStage.setTitle("Game Management Application");
		primaryStage.setScene(scene);
		primaryStage.setHeight(600);
		primaryStage.setWidth(800);
		// primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
