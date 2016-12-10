package views;

import controllers.GameController;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Game;

/**
 * @file GameView.java
 * @author Kevin Ma | #: 300867968
 * @date December 9, 2016
 * @version 0.5.4 added prevention of adding multiple games with the same title
 * @description This class defines the structure and behaviors of the Game view
 *              for this application at a micro level.
 */

public class GameView extends OnlineGameTrackerView {
	// INSTANCE VARIABLES
	// =============================================================================================
	// Controller
	// ---------------------------------------------------------------------------------------------
	// This isn't like traditional MVC. This is just a workaround.
	private GameController gc;

	// TableView - can't be in abstract class due to typing problems. I tried =(
	// ---------------------------------------------------------------------------------------------
	private TableView<Game> table;

	// read
	// ---------------------------------------------------------------------------------------------
	private TableColumn<Game, Integer> gameIdColumn;
	private TableColumn<Game, String> gameTitleColumn;

	// update/delete
	// ---------------------------------------------------------------------------------------------
	private TextField gameTitleModifyTF;
	private TextField gameIdModifyTF;
	private Button updateBtn;
	private Button deleteBtn;

	// add
	// ---------------------------------------------------------------------------------------------
	private TextField gameTitleInputTF;
	private Button addBtn;

	// CONSTRUCTOR
	// =============================================================================================
	public GameView() {
		super("Game");
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
		this.addEventListeners();

		// populate the table
		this.table.setItems(gc.selectAll());

		this.resetTab();
	}

	/**
	 * Returns the Game "View" to the application
	 * 
	 * @return the tab containing all the contents of the GameView
	 */
	public Tab getTab() {
		return this.tab;
	}

	/**
	 * Resets all controls to their predetermined default values on the Game Tab
	 */
	@Override
	public void resetTab() {

		// titledpanes default expanded/collapsed display
		this.addTitledPane.setExpanded(true);
		this.updateOrDeleteTitledPane.setExpanded(false);

		// disable buttons by default
		this.addBtn.setDisable(true);
		this.deleteBtn.setDisable(true);
		this.updateBtn.setDisable(true);

		// clear all textfields
		this.gameTitleInputTF.clear();
		this.gameTitleModifyTF.clear();

		// cannot edit a non-referenced record
		this.gameTitleModifyTF.setDisable(true);

		// clear all labels
		this.updateOrDeleteMsgLabel.setText("");
		this.addMsgLabel.setText("");

		// initially hide the messages until events triggered
		this.updateOrDeleteMsgLblHBox.setManaged(false);
		this.addMsgLblHBox.setManaged(false);
	}

	// PRIVATE METHODS
	// =============================================================================================
	/**
	 * Define behaviors of the controls on the view
	 */
	private void addEventListeners() {
		// TableView Event Handlers - when selecting a different record
		this.table.getSelectionModel().selectedItemProperty().addListener(e -> {

			// to prevent errors, when removing all items from table
			if (this.table.getItems().size() > 0) {

				// switch focus to the update/delete titled pane
				this.updateOrDeleteTitledPane.setExpanded(true);
				this.addTitledPane.setExpanded(false);

				// enable buttons to interact with selected record
				this.updateBtn.setDisable(false);
				this.deleteBtn.setDisable(false);

				// enable TextFields so that the selected row can be edited
				this.gameTitleModifyTF.setDisable(false);

				// clear and hide previous message when selecting new record
				this.updateOrDeleteMsgLabel.setText("");
				this.updateOrDeleteMsgLblHBox.setManaged(false);

				// populate textfields with data from selected table row
				Game tmpGame = this.table.getSelectionModel().getSelectedItem();
				this.gameIdModifyTF.setText(tmpGame.getGameId() + "");
				this.gameTitleModifyTF.setText(tmpGame.getGameTitle());
			}
		});
		// -----------------------------------------------------------------------------------------

		// TextField Event Handlers
		// only allows adding to the table if text is not null
		this.gameTitleInputTF.textProperty().addListener(e -> {
			if (this.gameTitleInputTF.getText().length() > 0)
				this.addBtn.setDisable(false);
			else
				this.addBtn.setDisable(true);

		});
		// only allows updating/deleting from the table if text is not null
		this.gameTitleModifyTF.textProperty().addListener(e -> {
			if (this.gameTitleModifyTF.getText().length() > 0) {
				this.updateBtn.setDisable(false);
				this.deleteBtn.setDisable(false);
			} else {
				this.updateBtn.setDisable(true);
				this.deleteBtn.setDisable(true);
			}
		});
		// -----------------------------------------------------------------------------------------

		// Button Event Handlers
		this.addBtn.setOnAction(e -> {
			if (gc.insertIntoGame(this.gameTitleInputTF.getText())) {
				this.addMsgLabel
						.setText("Successfully added '" + this.gameTitleInputTF.getText() + "' to the Game table.");
			} else {
				this.addMsgLabel.setText("Failed to add '" + this.gameTitleInputTF.getText()
						+ "' to the Game table. It already exists within the Game table.");
			}

			this.addMsgLblHBox.setManaged(true);

			this.updateTable();

			// prevents redoing same action
			this.gameTitleInputTF.clear();
			this.addBtn.setDisable(true);
		});
		this.deleteBtn.setOnAction(e -> {
			if (gc.deleteGame(Integer.parseInt(this.gameIdModifyTF.getText()))) {
				this.updateOrDeleteMsgLabel.setText("Successfully deleted game #" + this.gameIdModifyTF.getText() + " '"
						+ this.gameTitleModifyTF.getText() + "' from the Game table.");
			} else {
				this.updateOrDeleteMsgLabel.setText("Failed to delete game #" + this.gameIdModifyTF.getText() + " '"
						+ this.gameTitleModifyTF.getText() + "' from the Game table.");
			}
			this.updateOrDeleteMsgLblHBox.setManaged(true);

			this.updateTable();

			// prevents redoing same action
			this.gameTitleModifyTF.clear();
			this.gameIdModifyTF.clear();
			this.gameTitleModifyTF.setDisable(true);
			this.updateBtn.setDisable(true);
			this.deleteBtn.setDisable(true);
		});
		this.updateBtn.setOnAction(e -> {
			if (gc.updateGame(Integer.parseInt(this.gameIdModifyTF.getText()), this.gameTitleModifyTF.getText())) {
				this.updateOrDeleteMsgLabel
						.setText(String.format("Successfully updated game #%s to '%s' in the Game table.",
								this.gameIdModifyTF.getText(), this.gameTitleModifyTF.getText()));
			} else {
				this.updateOrDeleteMsgLabel.setText(
						String.format("Failed to update game #%s: '%s' in the Game table. Game titles must be unique!",
								this.gameIdModifyTF.getText(), this.gameTitleModifyTF.getText()));
			}
			this.updateOrDeleteMsgLblHBox.setManaged(true);

			this.updateTable();

			// prevents redoing same action
			this.gameIdModifyTF.clear();
			this.gameTitleModifyTF.clear();
			this.gameTitleModifyTF.setDisable(true);
			this.updateBtn.setDisable(true);
			this.deleteBtn.setDisable(true);
		});
	}

	/**
	 * Updates the table after a change has been made.
	 */
	private void updateTable() {
		if (this.table.getItems().size() > 0)
			this.table.getItems().clear();
		this.table.getItems().addAll(gc.selectAll());
	}

	/**
	 * Adding the initialized controls to their container(s)
	 */
	private void addContentsToContainers() {
		// table view
		this.table = new TableView<Game>();
		this.table.getColumns().addAll(this.gameIdColumn, this.gameTitleColumn);
		this.gameIdColumn.prefWidthProperty().bind(this.table.widthProperty().multiply(0.25));
		this.gameTitleColumn.prefWidthProperty().bind(this.table.widthProperty().multiply(0.75));
		this.tabBodyVBox.getChildren().add(0, this.table);

		// update/delete box
		this.updateOrDeleteControlsHBox.getChildren().addAll(this.gameIdModifyTF, this.gameTitleModifyTF,
				this.updateBtn, this.deleteBtn);

		// add box
		this.addControlsHBox.getChildren().addAll(this.gameTitleInputTF, this.addBtn);
	}

	/**
	 * Initializes the controls used on the GameView
	 */
	private void initializeControls() {
		// Initialize the view's Controller
		gc = new GameController();

		// Table Columns
		// -----------------------------------------------------------------------------------------
		this.gameIdColumn = new TableColumn<>("Game Id");
		// NOTE: the property name comes from Model, not the column (diff name)
		this.gameIdColumn.setCellValueFactory(new PropertyValueFactory<>("gameId"));
		// -----------------------------------------------------------------------------------------
		this.gameTitleColumn = new TableColumn<>("Game Title");
		this.gameTitleColumn.setCellValueFactory(new PropertyValueFactory<>("gameTitle"));

		// Textfields
		this.gameTitleInputTF = new TextField();
		this.gameTitleInputTF.setPromptText("Game Title");
		this.gameTitleInputTF.setMinWidth(300);
		// -----------------------------------------------------------------------------------------
		this.gameIdModifyTF = new TextField();
		this.gameIdModifyTF.setPromptText("Game Id");
		this.gameIdModifyTF.setMinWidth(100);
		this.gameIdModifyTF.setDisable(true); // cannot edit to maintain
												// referential integrity
		// -----------------------------------------------------------------------------------------
		this.gameTitleModifyTF = new TextField();
		this.gameTitleModifyTF.setPromptText("Game Title");
		this.gameTitleModifyTF.setMinWidth(300);

		// Buttons
		this.addBtn = new Button("Add");
		this.updateBtn = new Button("Update");
		this.deleteBtn = new Button("Delete");
	}
}
