package views;

import java.util.Optional;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @file OnlineGameTrackerView.java
 * @author Kevin Ma | #: 300867968
 * @date December 11, 2016
 * @version 1.1.0 - implemented deletion confirmation; updated deletion to
 *          maintain referential integrity
 * @description This abstract class defines the structure and behaviors of views
 *              for this application at a macro level.
 */

public abstract class OnlineGameTrackerView {
	// INSTANCE VARIABLES
	// =============================================================================================
	protected Tab tab;
	protected VBox tabBodyVBox; // main container, level 1
	// ---------------------------------------------------------------------------------------------
	protected TitledPane updateOrDeleteTitledPane;
	protected VBox updateOrDeleteBodyVBox; // container level 2
	protected HBox updateOrDeleteControlsHBox; // container level 3
	protected HBox updateOrDeleteMsgLblHBox; // container level 3
	protected Label updateOrDeleteMsgLabel; // provides visual feedback
	protected Alert deleteConfirmationAlert;
	protected Optional<ButtonType> confirmationResult;
	// ---------------------------------------------------------------------------------------------
	protected TitledPane addTitledPane;
	protected VBox addBodyVBox; // container level 2
	protected HBox addControlsHBox; // container level 3
	protected HBox addMsgLblHBox; // container level 3
	protected Label addMsgLabel; // provides visual feedback

	// CONSTRUCTOR
	// =============================================================================================
	protected OnlineGameTrackerView(String tabName) {
		this.tab = new Tab(tabName);
		this.updateOrDeleteTitledPane = new TitledPane("Update/Remove a [" + tabName + "]",
				this.updateOrDeleteBodyVBox);
		this.addTitledPane = new TitledPane("Add a [" + tabName + "]", this.addBodyVBox);

		this.deleteConfirmationAlert = new Alert(AlertType.CONFIRMATION);
		this.deleteConfirmationAlert.setTitle("Delete Confirmation");
		this.deleteConfirmationAlert.setHeaderText("Are you sure you want to delete this [" + tabName + "] record?");

		this.start();
	}

	// PUBLIC METHODS
	// =============================================================================================
	/**
	 * Initializes the view's main containers. Details will be added in detail
	 * in descendants.
	 */
	public void start() {
		this.initializeContainers();
		this.addContentsToContainers();

		// configures the containers
		this.configureHBoxes();
		this.configureVBoxes();
		this.configureTitledPanes();
	}

	// PRIVATE METHODS
	// =============================================================================================
	/**
	 * Initializes the containers used for the OnlineGameTrakerView
	 */
	private void initializeContainers() {
		this.tabBodyVBox = new VBox();
		this.updateOrDeleteBodyVBox = new VBox();
		this.updateOrDeleteControlsHBox = new HBox();
		this.updateOrDeleteMsgLblHBox = new HBox(this.updateOrDeleteMsgLabel = new Label());
		this.addBodyVBox = new VBox();
		this.addControlsHBox = new HBox();
		this.addMsgLblHBox = new HBox(this.addMsgLabel = new Label());
	}

	/**
	 * Add the 3 main sections to the view - read, update/delete, and create
	 * nested containers for CRUD of table
	 */
	private void addContentsToContainers() {
		// HBoxes in BodyVBoxes
		this.updateOrDeleteBodyVBox.getChildren().addAll(this.updateOrDeleteControlsHBox,
				this.updateOrDeleteMsgLblHBox);
		this.addBodyVBox.getChildren().addAll(this.addControlsHBox, this.addMsgLblHBox);

		// HBoxes in TitledPanes
		this.updateOrDeleteTitledPane.setContent(this.updateOrDeleteBodyVBox);
		this.addTitledPane.setContent(this.addBodyVBox);

		// TitledPanes in MainVBox
		this.tabBodyVBox.getChildren().addAll(this.updateOrDeleteTitledPane, this.addTitledPane);

		// set content of Tab to MainVBox
		this.tab.setContent(this.tabBodyVBox);
	}

	/**
	 * Sets up the configuration for the 4 HBoxes
	 */
	private void configureHBoxes() {
		this.updateOrDeleteControlsHBox.setPadding(new Insets(10));
		this.updateOrDeleteControlsHBox.setSpacing(10);
		// -----------------------------------------------------------------------------------------
		this.updateOrDeleteMsgLblHBox.setPadding(new Insets(10));
		this.updateOrDeleteMsgLblHBox.setSpacing(10);
		// -----------------------------------------------------------------------------------------
		this.addControlsHBox.setPadding(new Insets(10));
		this.addControlsHBox.setSpacing(10);
		// -----------------------------------------------------------------------------------------
		this.addMsgLblHBox.setPadding(new Insets(10));
		this.addMsgLblHBox.setSpacing(10);
	}

	/**
	 * Sets up the configuration for the 2 VBoxes
	 */
	private void configureVBoxes() {

	}

	/**
	 * Configures the titled panes behaviors - only one is shown at a time, the
	 * other is collapsed
	 */
	private void configureTitledPanes() {
		this.updateOrDeleteTitledPane.expandedProperty().addListener(e -> {
			if (this.updateOrDeleteTitledPane.isExpanded()) {
				this.addTitledPane.setExpanded(false);
			} else
				// hides the message when switching to another titled pane
				this.updateOrDeleteMsgLblHBox.setManaged(false);
		});
		this.addTitledPane.expandedProperty().addListener(e -> {
			if (this.addTitledPane.isExpanded()) {
				this.updateOrDeleteTitledPane.setExpanded(false);
			} else
				// hides the message when switching to another titled pane
				this.addMsgLblHBox.setManaged(false);
		});
	}

	// ABSTRACT METHODS
	// =============================================================================================
	/**
	 * Resets all controls to their predetermined default values on the Tab
	 */
	abstract void resetTab();
}
