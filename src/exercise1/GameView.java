package exercise1;

import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @file OnlineGameTrackerView.java
 * @author Kevin Ma | #: 300867968
 * @date December 7, 2016
 * @version 0.4.1 added controls to GameView
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

	// read
	// ---------------------------------------------------------------------------------------------
	private TableColumn<GameModel, Integer> gameIdColumn;
	private TableColumn<GameModel, String> gameTitleColumn;

	// update/delete
	// ---------------------------------------------------------------------------------------------
	private TextField gameIdModifyTF;
	private TextField gameTitleModifyTF;
	private Button updateBtn;
	private Button deleteBtn;

	// add
	// ---------------------------------------------------------------------------------------------
	TextField gameTitleInput;
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

		// Table Columns
		// -----------------------------------------------------------------------------------------
		this.gameIdColumn = new TableColumn<>("Game Id");
		this.gameIdColumn.setMinWidth(100);
		// NOTE: the property name comes from Model, not the column (diff name)
		this.gameIdColumn.setCellValueFactory(new PropertyValueFactory<>("gameId"));
		// -----------------------------------------------------------------------------------------
		this.gameTitleColumn = new TableColumn<>("Game Title");
		this.gameTitleColumn.setMinWidth(100);
		this.gameTitleColumn.setCellValueFactory(new PropertyValueFactory<>("gameTitle"));

		// Textfields
		this.gameIdModifyTF = new TextField();
		this.gameIdModifyTF.setPromptText("Game Id");
		this.gameIdModifyTF.setEditable(false); // gameId is PK of table
		this.gameIdModifyTF.setMinWidth(100);
		// -----------------------------------------------------------------------------------------
		this.gameTitleInput = new TextField();
		this.gameTitleInput.setPromptText("Game Title");
		this.gameTitleInput.setMinWidth(300);
		// -----------------------------------------------------------------------------------------
		this.gameTitleModifyTF = new TextField();
		this.gameTitleModifyTF.setPromptText("Game Title");
		this.gameTitleModifyTF.setMinWidth(300);

		// Buttons
		this.addBtn = new Button("Add");
		this.updateBtn = new Button("Update");
		this.deleteBtn = new Button("Delete");

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
	void resetTab() {

	}
}
