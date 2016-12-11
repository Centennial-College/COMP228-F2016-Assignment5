package views;

import controllers.GameController;
import controllers.PlayerAndGameController;
import controllers.PlayerController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import models.Game;
import models.Player;
import models.PlayerAndGame;

/**
 * @file PlayerAndGameView.java
 * @author Kevin Ma | #: 300867968
 * @date December 10, 2016
 * @version 0.6.2 - completed layout for PlayerAndGameView
 * @description This class defines the structure and behaviors of the
 *              PlayerAndGame view for this application at a micro level.
 */

public class PlayerAndGameView extends OnlineGameTrackerView {

	// INSTANCE VARIABLES
	// =============================================================================================
	// Controller
	// ---------------------------------------------------------------------------------------------
	// This isn't like traditional MVC. This is just a workaround.
	private PlayerAndGameController pgc;
	private GameController gc; // to select all games for ComboBox
	private PlayerController pc; // to select all players for ComboBox

	// TableView - can't be in abstract class due to typing problems. I tried =(
	// ---------------------------------------------------------------------------------------------
	private TableView<PlayerAndGame> table;
	private GridPane detailsGP;
	private HBox dataViewHBox;
	private SplitPane dataViewSplitPane;

	// read - table
	// ---------------------------------------------------------------------------------------------
	private TableColumn<PlayerAndGame, Integer> playerIdColumn;
	private TableColumn<PlayerAndGame, Integer> gameIdColumn;
	// private TableColumn<PlayerAndGame, String> playingDateColumn;
	// private TableColumn<PlayerAndGame, String> scoreColumn;

	// read - details
	// ---------------------------------------------------------------------------------------------
	private Label playerIdLbl;
	private Label playerDetailsHeaderLbl;
	private Label gameDetailsHeaderLbl;
	private Label fnameLbl;
	private Label lnameLbl;
	private Label gameIdLbl;
	private Label gameTitleLbl;
	private Label gameDateLbl;
	private Label gameScoreLbl;

	// update/delete
	// ---------------------------------------------------------------------------------------------
	private TextField gameTFMod;
	private TextField playerTFMod;
	private TextField playDateTFMod;
	private TextField scoreTFMod;
	private Button updateBtn;
	private Button deleteBtn;

	// add
	// ---------------------------------------------------------------------------------------------
	private TextField playDateTFIn;
	private TextField scoreTFIn;
	private ComboBox<Game> gamesCB;
	private ComboBox<Player> playersCB;
	private Button addBtn;

	// CONSTRUCTOR
	// =============================================================================================
	public PlayerAndGameView() {
		super("Player and Game");
	}

	// PUBLIC METHODS
	// =============================================================================================
	/**
	 * Initializes all the controls of the view and adds them to their
	 * respective containers.
	 */
	public void start() {
		super.start();
		this.initializeControls();
		this.addContentsToContainers();
		// this.addEventListeners();

		// populate the table
		// this.table.setItems(pc.selectAll());

		this.resetTab();
	}

	/**
	 * Returns the PlayerAndGame "View" to the application
	 * 
	 * @return the tab containing all the contents of the PlayerAndGameView
	 */
	public Tab getTab() {
		return this.tab;
	}

	/**
	 * Resets all controls to their predetermined default values on the Player
	 * Tab
	 */
	@Override
	public void resetTab() {

		// deselect all rows
		// this.table.getSelectionModel().clearSelection();

		// titledpanes default expanded/collapsed display
		this.addTitledPane.setExpanded(true);
		this.updateOrDeleteTitledPane.setExpanded(false);

		// disable buttons by default
		this.addBtn.setDisable(true);
		this.deleteBtn.setDisable(true);
		this.updateBtn.setDisable(true);

		// clear all textfields
		this.playerTFMod.clear();
		this.gameTFMod.clear();
		this.playDateTFMod.clear();
		this.scoreTFMod.clear();
		this.playDateTFIn.clear();
		this.scoreTFIn.clear();

		// reset all labels
		this.playerIdLbl.setText("-");
		this.fnameLbl.setText("-");
		this.lnameLbl.setText("-");
		this.gameIdLbl.setText("-");
		this.gameTitleLbl.setText("-");
		this.gameDateLbl.setText("-");
		this.gameScoreLbl.setText("-");

		// cannot edit a non-referenced record
		this.playDateTFMod.setDisable(true);
		this.scoreTFMod.setDisable(true);

		// reset ComboBoxes
		this.gamesCB.getItems().add(new Game(5));
		this.playersCB.getItems().add(new Player(23));
		this.gamesCB.getSelectionModel().clearSelection();

		// initially hide the messages until events triggered
		this.updateOrDeleteMsgLblHBox.setManaged(false);
		this.addMsgLblHBox.setManaged(false);
	}

	// PRIVATE METHODS
	// =============================================================================================
	/**
	 * Adding the initialized controls to their container(s)
	 */
	private void addContentsToContainers() {
		// Data Viewing
		// -----------------------------------------------------------------------------------------
		this.dataViewSplitPane = new SplitPane();
		this.dataViewHBox = new HBox();
		this.detailsGP = new GridPane();
		this.detailsGP.setHgap(10); // horizontal gap in pixels
		this.detailsGP.setVgap(10); // vertical gap in pixels
		this.detailsGP.setPadding(new Insets(10, 10, 10, 10)); // margins around
																// the whole
																// grid
		// (top/right/bottom/left)
		// -----------------------------------------------------------------------------------------
		// PlayersAndGames TableView
		this.table = new TableView<PlayerAndGame>();
		this.table.getColumns().addAll(this.playerIdColumn, this.gameIdColumn);
		this.playerIdColumn.prefWidthProperty().bind(this.table.widthProperty().divide(2));
		this.gameIdColumn.prefWidthProperty().bind(this.table.widthProperty().divide(2));
		// -----------------------------------------------------------------------------------------
		// Details GridView
		this.detailsGP.add(playerDetailsHeaderLbl = new Label("Player Details"), 0, 0);
		this.playerDetailsHeaderLbl.getStyleClass().add("label-header");
		this.detailsGP.setColumnSpan(this.playerDetailsHeaderLbl, 2);
		// -----------------------------------------------------------------------------------------
		this.detailsGP.add(new Label("Player Id\t\t\t"), 0, 1);
		this.detailsGP.add(this.playerIdLbl, 1, 1);
		this.playerIdLbl.getStyleClass().add("label-bright");
		// -----------------------------------------------------------------------------------------
		this.detailsGP.add(new Label("First Name\t\t\t"), 0, 2);
		this.detailsGP.add(this.fnameLbl, 1, 2);
		this.fnameLbl.getStyleClass().add("label-bright");
		// -----------------------------------------------------------------------------------------
		this.detailsGP.add(new Label("Last Name\t\t\t"), 0, 3);
		this.detailsGP.add(this.lnameLbl, 1, 3);
		this.lnameLbl.getStyleClass().add("label-bright");
		// -----------------------------------------------------------------------------------------
		this.detailsGP.add(gameDetailsHeaderLbl = new Label("Game Details"), 0, 4);
		this.gameDetailsHeaderLbl.getStyleClass().add("label-header");
		this.detailsGP.setColumnSpan(this.gameDetailsHeaderLbl, 2);
		// -----------------------------------------------------------------------------------------
		this.detailsGP.add(new Label("Game Id\t\t\t"), 0, 5);
		this.detailsGP.add(this.gameIdLbl, 1, 5);
		this.gameIdLbl.getStyleClass().add("label-bright");
		// -----------------------------------------------------------------------------------------
		this.detailsGP.add(new Label("Game Title\t\t\t"), 0, 6);
		this.detailsGP.add(this.gameTitleLbl, 1, 6);
		this.gameTitleLbl.getStyleClass().add("label-bright");
		// -----------------------------------------------------------------------------------------
		this.detailsGP.add(new Label("Last Played\t\t\t"), 0, 7);
		this.detailsGP.add(this.gameDateLbl, 1, 7);
		this.gameDateLbl.getStyleClass().add("label-bright");
		// -----------------------------------------------------------------------------------------
		this.detailsGP.add(new Label("Score\t\t\t"), 0, 8);
		this.detailsGP.add(this.gameScoreLbl, 1, 8);
		this.gameScoreLbl.getStyleClass().add("label-bright");
		// -----------------------------------------------------------------------------------------

		this.dataViewHBox.getChildren().addAll(this.detailsGP);
		this.dataViewSplitPane.getItems().addAll(this.table, this.dataViewHBox);

		this.tabBodyVBox.getChildren().add(0, this.dataViewSplitPane);

		// update/delete box
		// -----------------------------------------------------------------------------------------
		GridPane updateDeleteGridPane = new GridPane();
		updateDeleteGridPane.setAlignment(Pos.CENTER);
		updateDeleteGridPane.setHgap(10);
		updateDeleteGridPane.setVgap(10);

		updateDeleteGridPane.add(this.playerTFMod, 0, 0);
		updateDeleteGridPane.add(this.gameTFMod, 1, 0);
		updateDeleteGridPane.add(this.playDateTFMod, 2, 0);
		updateDeleteGridPane.add(this.scoreTFMod, 3, 0);
		updateDeleteGridPane.add(this.updateBtn, 1, 2);
		updateDeleteGridPane.add(this.deleteBtn, 2, 2);

		GridPane.setHalignment(this.updateBtn, HPos.RIGHT);

		this.updateOrDeleteControlsHBox.getChildren().addAll(updateDeleteGridPane);

		// add box
		// -----------------------------------------------------------------------------------------
		GridPane addGridPane = new GridPane();
		addGridPane.setAlignment(Pos.CENTER);
		addGridPane.setHgap(10);
		addGridPane.setVgap(10);

		addGridPane.add(this.playersCB, 0, 0);
		addGridPane.add(this.gamesCB, 1, 0);
		addGridPane.add(this.playDateTFIn, 2, 0);
		addGridPane.add(this.scoreTFIn, 3, 0);
		// addGridPane.add(this.playerProvInputTF, 0, 1);
		// addGridPane.add(this.playerPhoneInputTF, 1, 1);
		addGridPane.add(this.addBtn, 1, 2);

		GridPane.setHalignment(this.addBtn, HPos.RIGHT);

		this.addControlsHBox.getChildren().addAll(addGridPane);
	}

	/**
	 * Initializes the controls used on the PlayerAndGameView
	 */
	private void initializeControls() {
		// Initialize the view's Controllers
		gc = new GameController();
		pc = new PlayerController();
		pgc = new PlayerAndGameController();

		// Table Columns
		// -----------------------------------------------------------------------------------------
		this.playerIdColumn = new TableColumn<>("Player Id");
		// NOTE: the property name comes from Model, not the column (diff name)
		this.playerIdColumn.setCellValueFactory(new PropertyValueFactory<>("playerId"));
		// -----------------------------------------------------------------------------------------
		this.gameIdColumn = new TableColumn<>("Game Id");
		this.gameIdColumn.setCellValueFactory(new PropertyValueFactory<>("gameId"));

		// Textfields
		// -----------------------------------------------------------------------------------------
		// these two are not editable
		this.gameTFMod = new TextField();
		this.gameTFMod.setPromptText("Game Title");
		this.gameTFMod.setDisable(true);
		this.playerTFMod = new TextField();
		this.playerTFMod.setPromptText("Player Id and Name");
		this.playerTFMod.setDisable(true);
		// -----------------------------------------------------------------------------------------
		// editable - update
		this.playDateTFMod = new TextField();
		this.playDateTFMod.setPromptText("Playing Date");
		this.scoreTFMod = new TextField();
		this.scoreTFMod.setPromptText("Score/Rank");
		// -----------------------------------------------------------------------------------------
		// editable - add
		this.playDateTFIn = new TextField();
		this.playDateTFIn.setPromptText("Playing Date");
		this.scoreTFIn = new TextField();
		this.scoreTFIn.setPromptText("Score/Rank");

		// Labels
		this.playerIdLbl = new Label();
		this.fnameLbl = new Label();
		this.lnameLbl = new Label();
		this.gameIdLbl = new Label();
		this.gameDateLbl = new Label();
		this.gameTitleLbl = new Label();
		this.gameScoreLbl = new Label();

		// Buttons
		this.addBtn = new Button("Add");
		this.updateBtn = new Button("Update");
		this.deleteBtn = new Button("Delete");

		// ComboBoxes
		this.gamesCB = new ComboBox<>();
		this.gamesCB.setPromptText("Game Title");

		this.playersCB = new ComboBox<>();
		this.playersCB.setPromptText("Player Id and Name");
	}
}
