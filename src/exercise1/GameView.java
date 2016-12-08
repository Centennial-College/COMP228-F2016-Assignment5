package exercise1;

import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @file OnlineGameTrackerView.java
 * @author Kevin Ma | #: 300867968
 * @date December 8, 2016
 * @version 0.4.1 added controls to GameView, updated OnlineGameTrackerView
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
	private TableView<GameModel> table;

	// read
	// ---------------------------------------------------------------------------------------------
	private TableColumn<GameModel, Integer> gameIdColumn;
	private TableColumn<GameModel, String> gameTitleColumn;

	// update/delete
	// ---------------------------------------------------------------------------------------------
	private TextField gameTitleModifyTF;
	private Button updateBtn;
	private Button deleteBtn;

	// add
	// ---------------------------------------------------------------------------------------------
	TextField gameTitleInputTF;
	Button addBtn;

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

		// Initializing the controls
		// Table Columns
		// -----------------------------------------------------------------------------------------
		this.gameIdColumn = new TableColumn<>("Game Id");
		this.gameIdColumn.setMinWidth(100);
		// NOTE: the property name comes from Model, not the column (diff name)
		this.gameIdColumn.setCellValueFactory(new PropertyValueFactory<>("gameId"));
		// -----------------------------------------------------------------------------------------
		this.gameTitleColumn = new TableColumn<>("Game Title");
		this.gameTitleColumn.setMinWidth(300);
		this.gameTitleColumn.setCellValueFactory(new PropertyValueFactory<>("gameTitle"));

		// Textfields
		this.gameTitleInputTF = new TextField();
		this.gameTitleInputTF.setPromptText("Game Title");
		this.gameTitleInputTF.setMinWidth(300);
		// -----------------------------------------------------------------------------------------
		this.gameTitleModifyTF = new TextField();
		this.gameTitleModifyTF.setPromptText("Game Title");
		this.gameTitleModifyTF.setMinWidth(300);

		// Buttons
		this.addBtn = new Button("Add");
		this.updateBtn = new Button("Update");
		this.deleteBtn = new Button("Delete");

		// Adding the initialized controls to their container(s)
		// table view
		this.table = new TableView<GameModel>();
		this.table.getColumns().addAll(this.gameIdColumn, this.gameTitleColumn);
		this.tabBodyVBox.getChildren().add(0, this.table);

		// update/delete box
		this.updateOrDeleteControlsHBox.getChildren().addAll(this.gameTitleInputTF, this.updateBtn, this.deleteBtn);

		// add box
		this.addControlsHBox.getChildren().addAll(this.gameTitleModifyTF, this.addBtn);

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
		this.updateOrDeleteTitledPane.setExpanded(true);
		this.addTitledPane.setExpanded(false);

		// clear all textfields
		this.gameTitleInputTF.clear();
		this.gameTitleModifyTF.clear();

		// clear all labels
		this.updateOrDeleteMsgLabel.setText("");
		this.addMsgLabel.setText("");

		// initially hide the messages until events triggered
		this.updateOrDeleteMsgLblHBox.setManaged(false);
		this.addMsgLblHBox.setManaged(false);
	}
}
