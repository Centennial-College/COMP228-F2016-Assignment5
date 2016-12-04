package exercise1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
 * @version 0.3.0 completely redesigned GUI
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

	// Tab that allows manipulation to Game table
	// ---------------------------------------------------------------------------------------------
	Tab gameTab;
	VBox gameVBox; // contents of game tab in a vertical column

	// Used for CRUD operations on the Game Table
	TableView<Game> gameTable;
	TableColumn<Game, Integer> gameIdColum;
	TableColumn<Game, String> gameTitleColumn;
	TextField gameIdInput;
	TextField gameTitleInput;
	TextField gameIdModifyTF;
	TextField gameTitleModifyTF;
	Button addBtn;
	Button deleteBtn;
	Button updateBtn;
	HBox gameModifyHBox;
	HBox gameInputHBox;
	TitledPane updateOrDeleteGameTitledPane;
	TitledPane addGameTitledPane;

	// Tab that allows manipulation to Player table
	// ---------------------------------------------------------------------------------------------
	Tab playerTab;

	// Tab that allows manipulation to PlayerAndGame table
	// ---------------------------------------------------------------------------------------------
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

		// GAME TAB
		// =============================================================================================
		// Game Id Column
		gameIdColum = new TableColumn<>("Game Id");
		gameIdColum.setMinWidth(100);
		gameIdColum.setCellValueFactory(new PropertyValueFactory<>("gameId"));

		// Game Title Column
		gameTitleColumn = new TableColumn<>("Game Title");
		gameTitleColumn.setMinWidth(300);
		gameTitleColumn.setCellValueFactory(new PropertyValueFactory<>("gameTitle"));

		// Game Id Textfields
		gameIdInput = new TextField();
		gameIdInput.setPromptText("Game Id");
		gameIdInput.setMinWidth(100);

		gameIdModifyTF = new TextField();
		gameIdModifyTF.setPromptText("Game Id");
		gameIdModifyTF.setMinWidth(100);
		// disabled here to prevent user from changing primary key
		gameIdModifyTF.setDisable(true);

		// Game Title Textfields
		gameTitleInput = new TextField();
		gameTitleInput.setPromptText("Game Title");
		gameTitleInput.setMinWidth(300);

		gameTitleModifyTF = new TextField();
		gameTitleModifyTF.setPromptText("Game Title");
		gameTitleModifyTF.setMinWidth(300);

		// Buttons
		addBtn = new Button("Add");
		deleteBtn = new Button("Delete");
		updateBtn = new Button("Update");

		// Button Event Handlers
		addBtn.setOnAction(e -> {

		});
		deleteBtn.setOnAction(e -> {

		});
		updateBtn.setOnAction(e -> {

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
		updateOrDeleteGameTitledPane = new TitledPane("Update/Remove a Game", gameModifyHBox);

		// Adding textfields and buttons to add a new game to the db
		gameInputHBox = new HBox();
		gameInputHBox.setPadding(new Insets(10));
		gameInputHBox.setSpacing(10);
		gameInputHBox.getChildren().addAll(gameIdInput, gameTitleInput, addBtn);
		addGameTitledPane = new TitledPane("Add a New Game", gameInputHBox);

		// Adding contents to game tab
		gameVBox = new VBox();
		gameVBox.getChildren().addAll(gameTable, updateOrDeleteGameTitledPane, addGameTitledPane);
		gameTab.setContent(gameVBox);

		// Configuring the titled panes behavior - only one is shown at a time
		updateOrDeleteGameTitledPane.setExpanded(true);
		addGameTitledPane.setExpanded(false);
		updateOrDeleteGameTitledPane.expandedProperty().addListener(e -> {
			if (updateOrDeleteGameTitledPane.isExpanded())
				addGameTitledPane.setExpanded(false);
		});
		addGameTitledPane.expandedProperty().addListener(e -> {
			if (addGameTitledPane.isExpanded())
				updateOrDeleteGameTitledPane.setExpanded(false);
		});

		// Stage and scene configuration
		Scene scene = new Scene(tabbedPane);
		window.setScene(scene);
		window.setResizable(false);
		window.show();
	}

}
