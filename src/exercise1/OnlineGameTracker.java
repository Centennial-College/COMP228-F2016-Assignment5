package exercise1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @file OnlineGameTracker.java
 * @author Kevin Ma | #: 300867968
 * @date December 4, 2016
 * @version 0.3.2 added event listeners for the add/delete/update buttons
 * @description This class implements a UI using JavaFX and allows the user to
 *              perform CRUD operations on the Player and Game tables in the
 *              database.
 * 
 */

public class OnlineGameTracker extends Application {
	// INSTANCE VARIABLES
	// =============================================================================================
	Stage window;
	// TabPane is main container that is assigned -> Scene -> Stage
	TabPane tabbedPane;
	GameDatabaseContext db; // used to connect to the database

	// Tab that allows manipulation of the Game table
	// =============================================================================================
	Tab gameTab;
	VBox gameVBox; // contents of game tab in a vertical column

	// Used for CRUD operations on the Game Table
	TableView<Game> gameTable;
	TableColumn<Game, Integer> gameIdColum;
	TableColumn<Game, String> gameTitleColumn;

	TitledPane updateOrDeleteGameTitledPane;
	// ---------------------------------------------------------------------------------------------
	VBox gameModifyMainBox;

	HBox gameModifyHBox;
	TextField gameIdModifyTF;
	TextField gameTitleModifyTF;
	Button updateBtn;
	Button deleteBtn;

	HBox gameModifyMessageHBox;
	Label gameModifyLabel;

	TitledPane addGameTitledPane;
	// ---------------------------------------------------------------------------------------------
	VBox gameInputMainBox;

	HBox gameInputHBox;
	TextField gameTitleInput;
	Button addBtn;

	HBox gameInputMessageHBox;
	Label gameInputLabel;

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
		tabbedPane.getTabs().addAll(gameTab = new Tab("Game"), playerTab = new Tab("Player"),
				playerAndGameTab = new Tab("Player and Game"));
		tabbedPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		// Initialize Database Manager
		db = new GameDatabaseContext();

		// Initialize the UI
		initializeGameTab();

		// Stage and scene configuration
		Scene scene = new Scene(tabbedPane);
		window.setScene(scene);
		window.setResizable(false);
		window.show();
	}

	// GAME TAB METHODS
	// =============================================================================================
	private void initializeGameTab() {
		// Game Id Column
		gameIdColum = new TableColumn<>("Game Id");
		gameIdColum.setMinWidth(100);
		gameIdColum.setCellValueFactory(new PropertyValueFactory<>("gameId"));

		// Game Title Column
		gameTitleColumn = new TableColumn<>("Game Title");
		gameTitleColumn.setMinWidth(300);
		gameTitleColumn.setCellValueFactory(new PropertyValueFactory<>("gameTitle"));

		// Game Id Textfields - don't need for adding game because sequence
		// inserts default value
		gameIdModifyTF = new TextField();
		gameIdModifyTF.setPromptText("Game Id");
		gameIdModifyTF.setMinWidth(100);
		// disabled here to prevent user from changing primary key - just to
		// show them
		gameIdModifyTF.setDisable(true);

		// Game Title Textfields
		gameTitleInput = new TextField();
		gameTitleInput.setPromptText("Game Title");
		gameTitleInput.setMinWidth(300);

		gameTitleModifyTF = new TextField();
		gameTitleModifyTF.setPromptText("Game Title");
		gameTitleModifyTF.setMinWidth(300);

		// Game Modify Label
		gameModifyLabel = new Label();

		// Game Add Label
		gameInputLabel = new Label();

		// Buttons
		addBtn = new Button("Add");
		deleteBtn = new Button("Delete");
		updateBtn = new Button("Update");

		// Button Event Handlers
		addBtn.setOnAction(e -> {
			gameInputLabel.setText("Successfully added 'Dota' to the Game table.");
			gameInputMessageHBox.setManaged(true);
		});
		deleteBtn.setOnAction(e -> {
			gameModifyLabel.setText("Successfully deleted 'Dota' from the Game table.");
			gameModifyMessageHBox.setManaged(true);
		});
		updateBtn.setOnAction(e -> {
			gameModifyLabel.setText("Successfully modified 'Dota' in the Game table.");
			gameModifyMessageHBox.setManaged(true);
		});

		// Game Table
		gameTable = new TableView<>();
		gameTable.setItems(db.selectFromGame());
		gameTable.getColumns().addAll(gameIdColum, gameTitleColumn);

		// Adding textfields and buttons to modify or delete a game from the db
		gameModifyHBox = new HBox();
		gameModifyHBox.setPadding(new Insets(10));
		gameModifyHBox.setSpacing(10);
		gameModifyHBox.getChildren().addAll(gameIdModifyTF, gameTitleModifyTF, updateBtn, deleteBtn);

		// Adding textfields and buttons to add a new game to the db
		gameInputHBox = new HBox();
		gameInputHBox.setPadding(new Insets(10));
		gameInputHBox.setSpacing(10);
		gameInputHBox.getChildren().addAll(gameTitleInput, addBtn);

		// Adding message labels
		gameModifyMessageHBox = new HBox();
		gameModifyMessageHBox.setPadding(new Insets(10));
		gameModifyMessageHBox.setSpacing(10);
		gameModifyMessageHBox.getChildren().addAll(gameModifyLabel);

		gameInputMessageHBox = new HBox();
		gameInputMessageHBox.setPadding(new Insets(10));
		gameInputMessageHBox.setSpacing(10);
		gameInputMessageHBox.getChildren().addAll(gameInputLabel);

		// Adding contents to game tab
		gameModifyMainBox = new VBox();
		gameInputMainBox = new VBox();

		gameModifyMainBox.getChildren().addAll(gameModifyHBox, gameModifyMessageHBox);
		gameInputMainBox.getChildren().addAll(gameInputHBox, gameInputMessageHBox);

		updateOrDeleteGameTitledPane = new TitledPane("Update/Remove a Game", gameModifyMainBox);
		addGameTitledPane = new TitledPane("Add a New Game", gameInputMainBox);

		gameVBox = new VBox();
		gameVBox.getChildren().addAll(gameTable, updateOrDeleteGameTitledPane, addGameTitledPane);
		gameTab.setContent(gameVBox);

		// Configuring the titled panes behavior - only one is shown at a time,
		// other is collapsed
		updateOrDeleteGameTitledPane.expandedProperty().addListener(e -> {
			if (updateOrDeleteGameTitledPane.isExpanded()) {
				addGameTitledPane.setExpanded(false);
			} else
				// hides the message when switching to another titled pane
				gameModifyMessageHBox.setManaged(false);
		});
		addGameTitledPane.expandedProperty().addListener(e -> {
			if (addGameTitledPane.isExpanded()) {
				updateOrDeleteGameTitledPane.setExpanded(false);
			} else
				// hides the message when switching to another titled pane
				gameInputMessageHBox.setManaged(false);
		});

		resetGameTab();
	}

	/**
	 * Resets all controls to their predetermined default values on the Game Tab
	 */
	private void resetGameTab() {

		// titledpanes default expanded/collapsed display
		updateOrDeleteGameTitledPane.setExpanded(true);
		addGameTitledPane.setExpanded(false);

		// initially hide the messages until events triggered
		gameModifyMessageHBox.setManaged(false);
		gameInputMessageHBox.setManaged(false);

		// clear all textfields
		clearGameInputTextFields();
		clearGameModifyTextFIelds();
	}

	private void clearGameInputTextFields() {
		gameTitleInput.clear();
	}

	private void clearGameModifyTextFIelds() {
		gameIdModifyTF.clear();
		gameTitleModifyTF.clear();
	}
}
