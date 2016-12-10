package views;

import controllers.PlayerController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import models.PlayerModel;

/**
 * @file PlayerView.java
 * @author Kevin Ma | #: 300867968
 * @date December 9, 2016
 * @version 0.5.6 implemented event handler for table row selected
 * @description This class defines the structure and behaviors of the Player
 *              view for this application at a micro level.
 */

public class PlayerView extends OnlineGameTrackerView {
	// INSTANCE VARIABLES
	// =============================================================================================
	// Controller
	// ---------------------------------------------------------------------------------------------
	// This isn't like traditional MVC. This is just a workaround.
	private PlayerController pc;

	// TableView - can't be in abstract class due to typing problems. I tried =(
	// ---------------------------------------------------------------------------------------------
	private TableView<PlayerModel> table;

	// read
	// ---------------------------------------------------------------------------------------------
	private TableColumn<PlayerModel, Integer> playerIdColumn;
	private TableColumn<PlayerModel, String> playerFnameColumn;
	private TableColumn<PlayerModel, String> playerLnameColumn;
	private TableColumn<PlayerModel, String> playerAddrColumn;
	private TableColumn<PlayerModel, String> playerPcodeColumn;
	private TableColumn<PlayerModel, String> playerProvColumn;
	private TableColumn<PlayerModel, String> playerPhoneColumn;

	// update/delete
	// ---------------------------------------------------------------------------------------------
	private TextField playerIdModifyTF;
	private TextField playerFnameModifyTF;
	private TextField playerLnameModifyTF;
	private TextField playerAddrModifyTF;
	private TextField playerPcodeModifyTF;
	private TextField playerProvModifyTF;
	private TextField playerPhoneModifyTF;
	private Button updateBtn;
	private Button deleteBtn;

	// add
	// ---------------------------------------------------------------------------------------------
	private TextField playerFnameInputTF;
	private TextField playerLnameInputTF;
	private TextField playerAddrInputTF;
	private TextField playerPcodeInputTF;
	private TextField playerProvInputTF;
	private TextField playerPhoneInputTF;
	private Button addBtn;

	// CONSTRUCTOR
	// =============================================================================================
	public PlayerView() {
		super("Player");
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
		this.table.setItems(pc.selectAll());

		this.resetTab();
	}

	/**
	 * Returns the Player "View" to the application
	 * 
	 * @return the tab containing all the contents of the GameView
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

		// titledpanes default expanded/collapsed display
		this.updateOrDeleteTitledPane.setExpanded(true);
		this.addTitledPane.setExpanded(false);

		// disable buttons by default
		this.addBtn.setDisable(true);
		this.deleteBtn.setDisable(true);
		this.updateBtn.setDisable(true);

		// clear all textfields
		this.playerIdModifyTF.clear();
		this.playerFnameInputTF.clear();
		this.playerFnameModifyTF.clear();
		this.playerLnameInputTF.clear();
		this.playerLnameModifyTF.clear();
		this.playerAddrInputTF.clear();
		this.playerAddrModifyTF.clear();
		this.playerPcodeInputTF.clear();
		this.playerPcodeModifyTF.clear();
		this.playerProvInputTF.clear();
		this.playerProvModifyTF.clear();
		this.playerPhoneInputTF.clear();
		this.playerPhoneModifyTF.clear();

		// cannot edit a non-referenced record
		this.playerFnameModifyTF.setDisable(true);
		this.playerLnameModifyTF.setDisable(true);
		this.playerAddrModifyTF.setDisable(true);
		this.playerPcodeModifyTF.setDisable(true);
		this.playerProvModifyTF.setDisable(true);
		this.playerPhoneModifyTF.setDisable(true);

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
	 * Enables the update and delete buttons only when all the modify text
	 * fields are not empty.
	 */
	private void modifyTextFieldsHandler() {
		if (this.playerFnameModifyTF.getText().length() > 0 && this.playerLnameModifyTF.getText().length() > 0
				&& this.playerAddrModifyTF.getText().length() > 0 && this.playerPcodeModifyTF.getText().length() > 0
				&& this.playerProvModifyTF.getText().length() > 0 && this.playerPhoneModifyTF.getText().length() > 0) {
			this.updateBtn.setDisable(false);
			this.deleteBtn.setDisable(false);
		} else {
			this.updateBtn.setDisable(true);
			this.deleteBtn.setDisable(true);
		}
	}

	/**
	 * Enables the add button only when all the input textfields are not empty
	 */
	private void inputTextFieldsHandler() {
		if (this.playerFnameInputTF.getText().length() > 0 && this.playerLnameInputTF.getText().length() > 0
				&& this.playerAddrInputTF.getText().length() > 0 && this.playerPcodeInputTF.getText().length() > 0
				&& this.playerProvInputTF.getText().length() > 0 && this.playerPhoneInputTF.getText().length() > 0)

			this.addBtn.setDisable(false);
		else
			this.addBtn.setDisable(true);
	}

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
				this.playerFnameModifyTF.setDisable(false);
				this.playerLnameModifyTF.setDisable(false);
				this.playerAddrModifyTF.setDisable(false);
				this.playerPcodeModifyTF.setDisable(false);
				this.playerProvModifyTF.setDisable(false);
				this.playerPhoneModifyTF.setDisable(false);

				// clear and hide previous message when selecting new record
				this.updateOrDeleteMsgLabel.setText("");
				this.updateOrDeleteMsgLblHBox.setManaged(false);

				// populate textfields with data from selected table row
				PlayerModel tmpPlayer = this.table.getSelectionModel().getSelectedItem();
				this.playerIdModifyTF.setText(tmpPlayer.getPlayerId() + "");
				this.playerFnameModifyTF.setText(tmpPlayer.getFirstName());
				this.playerLnameModifyTF.setText(tmpPlayer.getLastName());
				this.playerAddrModifyTF.setText(tmpPlayer.getAddress());
				this.playerPcodeModifyTF.setText(tmpPlayer.getPostalCode());
				this.playerProvModifyTF.setText(tmpPlayer.getProvince());
				this.playerPhoneModifyTF.setText(tmpPlayer.getPhoneNumber());
			}
		});
		// -----------------------------------------------------------------------------------------

		// TextField Event Handlers
		// only allows adding to the table if text is not null
		this.playerFnameInputTF.textProperty().addListener(e -> {
			this.inputTextFieldsHandler();
		});
		this.playerLnameInputTF.textProperty().addListener(e -> {
			this.inputTextFieldsHandler();
		});
		this.playerAddrInputTF.textProperty().addListener(e -> {
			this.inputTextFieldsHandler();
		});
		this.playerPcodeInputTF.textProperty().addListener(e -> {
			this.inputTextFieldsHandler();
		});
		this.playerProvInputTF.textProperty().addListener(e -> {
			this.inputTextFieldsHandler();
		});
		this.playerPhoneInputTF.textProperty().addListener(e -> {
			this.inputTextFieldsHandler();
		});

		// only allows updating/deleting from the table if text is not null
		this.playerFnameModifyTF.textProperty().addListener(e -> {
			this.modifyTextFieldsHandler();
		});
		this.playerLnameModifyTF.textProperty().addListener(e -> {
			this.modifyTextFieldsHandler();
		});
		this.playerAddrModifyTF.textProperty().addListener(e -> {
			this.modifyTextFieldsHandler();
		});
		this.playerPcodeModifyTF.textProperty().addListener(e -> {
			this.modifyTextFieldsHandler();
		});
		this.playerProvModifyTF.textProperty().addListener(e -> {
			this.modifyTextFieldsHandler();
		});
		this.playerPhoneModifyTF.textProperty().addListener(e -> {
			this.modifyTextFieldsHandler();
		});
		// -----------------------------------------------------------------------------------------

		// Button Event Handlers
		this.addBtn.setOnAction(e -> {
			if (pc.insertIntoPlayer(this.playerFnameInputTF.getText(), this.playerLnameInputTF.getText(),
					this.playerAddrInputTF.getText(), this.playerPcodeInputTF.getText(),
					this.playerProvInputTF.getText(), this.playerPhoneInputTF.getText())) {
				this.addMsgLabel.setText("Successfully added '" + this.playerFnameInputTF.getText() + " "
						+ this.playerLnameInputTF.getText() + "' to the Player table.");
			} else {
				this.addMsgLabel.setText("Failed to add '" + this.playerFnameInputTF.getText() + " "
						+ this.playerLnameInputTF.getText() + "' to the Player table. Players must be unique!");
			}

			this.addMsgLblHBox.setManaged(true);

			this.updateTable();

			// prevents redoing same action
			this.playerFnameInputTF.clear();
			this.playerLnameInputTF.clear();
			this.playerAddrInputTF.clear();
			this.playerPcodeInputTF.clear();
			this.playerProvInputTF.clear();
			this.playerPhoneInputTF.clear();
			this.addBtn.setDisable(true);
		});
		this.deleteBtn.setOnAction(e -> {
			// if
			// (pc.deleteGame(Integer.parseInt(this.playerFnameModifyTF.getText())))
			// {
			// this.updateOrDeleteMsgLabel.setText("Successfully deleted game #"
			// + this.playerFnameModifyTF.getText()
			// + " '" + this.playerIdModifyTF.getText() + "' from the Game
			// table.");
			// } else {
			// this.updateOrDeleteMsgLabel.setText("Failed to delete game #" +
			// this.playerFnameModifyTF.getText()
			// + " '" + this.playerIdModifyTF.getText() + "' from the Game
			// table.");
			// }
			this.updateOrDeleteMsgLblHBox.setManaged(true);

			this.updateTable();

			// prevents redoing same action
			this.playerIdModifyTF.clear();
			this.playerFnameModifyTF.clear();
			this.playerIdModifyTF.setDisable(true);
			this.updateBtn.setDisable(true);
			this.deleteBtn.setDisable(true);
		});
		this.updateBtn.setOnAction(e -> {
			// if
			// (pc.updateGame(Integer.parseInt(this.playerFnameModifyTF.getText()),
			// this.playerIdModifyTF.getText())) {
			// this.updateOrDeleteMsgLabel
			// .setText(String.format("Successfully updated game #%s to '%s' in
			// the Game table.",
			// this.playerFnameModifyTF.getText(),
			// this.playerIdModifyTF.getText()));
			// } else {
			// this.updateOrDeleteMsgLabel.setText(String.format("Failed to
			// update game #%s: '%s' in the Game table. Players must be
			// unique!",
			// this.playerFnameModifyTF.getText(),
			// this.playerIdModifyTF.getText()));
			// }
			this.updateOrDeleteMsgLblHBox.setManaged(true);

			this.updateTable();

			// prevents redoing same action
			this.playerFnameModifyTF.clear();
			this.playerIdModifyTF.clear();
			this.playerIdModifyTF.setDisable(true);
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
		this.table.getItems().addAll(pc.selectAll());
	}

	/**
	 * Adding the initialized controls to their container(s)
	 */
	private void addContentsToContainers() {
		// table view
		this.table = new TableView<PlayerModel>();
		this.table.getColumns().addAll(this.playerIdColumn, this.playerFnameColumn, this.playerLnameColumn,
				this.playerAddrColumn, this.playerPcodeColumn, this.playerProvColumn, this.playerPhoneColumn);
		this.playerIdColumn.prefWidthProperty().bind(this.table.widthProperty().divide(7));
		this.playerFnameColumn.prefWidthProperty().bind(this.table.widthProperty().divide(7));
		this.playerLnameColumn.prefWidthProperty().bind(this.table.widthProperty().divide(7));
		this.playerAddrColumn.prefWidthProperty().bind(this.table.widthProperty().divide(7));
		this.playerPcodeColumn.prefWidthProperty().bind(this.table.widthProperty().divide(7));
		this.playerProvColumn.prefWidthProperty().bind(this.table.widthProperty().divide(7));
		this.playerPhoneColumn.prefWidthProperty().bind(this.table.widthProperty().divide(7));

		this.tabBodyVBox.getChildren().add(0, this.table);

		// update/delete box
		GridPane updateDeleteGridPane = new GridPane();
		updateDeleteGridPane.setAlignment(Pos.CENTER);
		updateDeleteGridPane.setHgap(10);
//		updateDeleteGridPane.setVgap(10);
//		updateDeleteGridPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		
		updateDeleteGridPane.add(this.playerIdModifyTF, 0, 0);
		updateDeleteGridPane.add(this.playerFnameModifyTF, 1, 0);
		updateDeleteGridPane.add(this.playerLnameModifyTF, 2, 0);
		updateDeleteGridPane.add(this.playerAddrModifyTF, 3, 0);
		
		this.updateOrDeleteControlsHBox.getChildren().addAll(updateDeleteGridPane);
//		this.updateOrDeleteControlsHBox.getChildren().addAll(this.playerIdModifyTF, this.playerFnameModifyTF,
//				this.playerLnameModifyTF, this.playerAddrModifyTF, this.playerPcodeModifyTF, this.playerProvModifyTF,
//				this.playerPhoneModifyTF, this.updateBtn, this.deleteBtn);

		// add box
//		this.addControlsHBox.getChildren().addAll(this.playerFnameInputTF, this.playerLnameInputTF,
//				this.playerAddrInputTF, this.playerPcodeInputTF, this.playerProvInputTF, this.playerPhoneInputTF,
//				this.addBtn);
	}

	/**
	 * Initializes the controls used on the PlayerView
	 */
	private void initializeControls() {
		// Initialize the view's Controller
		pc = new PlayerController();

		// Table Columns
		// -----------------------------------------------------------------------------------------
		this.playerIdColumn = new TableColumn<>("Player Id");
		// NOTE: the property name comes from Model, not the column (diff name)
		this.playerIdColumn.setCellValueFactory(new PropertyValueFactory<>("playerId"));
		// -----------------------------------------------------------------------------------------
		this.playerFnameColumn = new TableColumn<>("First Name");
		this.playerFnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		// -----------------------------------------------------------------------------------------
		this.playerLnameColumn = new TableColumn<>("Last Name");
		this.playerLnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		// -----------------------------------------------------------------------------------------
		this.playerAddrColumn = new TableColumn<>("Address");
		this.playerAddrColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
		// -----------------------------------------------------------------------------------------
		this.playerPcodeColumn = new TableColumn<>("Postal Code");
		this.playerPcodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
		// -----------------------------------------------------------------------------------------
		this.playerProvColumn = new TableColumn<>("Province");
		this.playerProvColumn.setCellValueFactory(new PropertyValueFactory<>("province"));
		// -----------------------------------------------------------------------------------------
		this.playerPhoneColumn = new TableColumn<>("Phone #");
		this.playerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

		// Textfields
		this.playerIdModifyTF = new TextField();
		this.playerIdModifyTF.setPromptText("Player Id");
		this.playerIdModifyTF.setDisable(true); // cannot edit to maintain
												// referential integrity
		// -----------------------------------------------------------------------------------------
		this.playerFnameInputTF = new TextField();
		this.playerFnameInputTF.setPromptText("First Name");
		// -----------------------------------------------------------------------------------------
		this.playerFnameModifyTF = new TextField();
		this.playerFnameModifyTF.setPromptText("First Name");
		// -----------------------------------------------------------------------------------------
		this.playerLnameInputTF = new TextField();
		this.playerLnameInputTF.setPromptText("Last Name");
		// -----------------------------------------------------------------------------------------
		this.playerLnameModifyTF = new TextField();
		this.playerLnameModifyTF.setPromptText("Last Name");
		// -----------------------------------------------------------------------------------------
		this.playerAddrInputTF = new TextField();
		this.playerAddrInputTF.setPromptText("Address");
		// -----------------------------------------------------------------------------------------
		this.playerAddrModifyTF = new TextField();
		this.playerAddrModifyTF.setPromptText("Address");
		// -----------------------------------------------------------------------------------------
		this.playerPcodeInputTF = new TextField();
		this.playerPcodeInputTF.setPromptText("Postal Code");
		// -----------------------------------------------------------------------------------------
		this.playerPcodeModifyTF = new TextField();
		this.playerPcodeModifyTF.setPromptText("Postal Code");
		// -----------------------------------------------------------------------------------------
		this.playerProvInputTF = new TextField();
		this.playerProvInputTF.setPromptText("Province");
		// -----------------------------------------------------------------------------------------
		this.playerProvModifyTF = new TextField();
		this.playerProvModifyTF.setPromptText("Province");
		// -----------------------------------------------------------------------------------------
		this.playerPhoneInputTF = new TextField();
		this.playerPhoneInputTF.setPromptText("Phone Number");
		// -----------------------------------------------------------------------------------------
		this.playerPhoneModifyTF = new TextField();
		this.playerPhoneModifyTF.setPromptText("Phone Number");

		// Buttons
		this.addBtn = new Button("Add");
		this.updateBtn = new Button("Update");
		this.deleteBtn = new Button("Delete");
	}
}
