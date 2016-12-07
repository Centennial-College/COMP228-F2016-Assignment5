package exercise1;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @file OnlineGameTrackerView.java
 * @author Kevin Ma | #: 300867968
 * @date December 7, 2016
 * @version 0.4.0 redesigned application to follow MVC design pattern - added
 *          abstract View and Controller classes
 * @description This abstract class defines the structure and behaviors of views
 *              for this application at a macro level.
 */

public abstract class OnlineGameTrackerView {
	// INSTANCE VARIABLES
	// =============================================================================================
	protected Tab tab;
	protected VBox tabBodyVBox; // main container, level 1
	// ---------------------------------------------------------------------------------------------
	protected TableView<?> table;
	// ---------------------------------------------------------------------------------------------
	protected TitledPane updateOrDeleteTitledPane;
	protected VBox updateOrDeleteBodyVBox; // container level 2
	protected HBox updateOrDeleteControlsHBox; // container level 3
	protected HBox updateOrDeleteMsgLblHBox; // container level 3
	protected Label updateOrDeleteMsgLabel; // provides visual feedback
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
		this.start();
	}

	// PUBLIC METHODS
	// =============================================================================================
	/**
	 * Initializes the view's main containers. Details will be added in detail
	 * in descendants.
	 */
	public void start() {
		// initialize the main containers
		this.tabBodyVBox = new VBox();
		this.updateOrDeleteBodyVBox = new VBox();
		this.updateOrDeleteControlsHBox = new HBox();
		this.updateOrDeleteMsgLblHBox = new HBox(this.updateOrDeleteMsgLabel = new Label());
		this.addBodyVBox = new VBox();
		this.addControlsHBox = new HBox();
		this.addMsgLblHBox = new HBox(this.addMsgLabel = new Label());

		// initialize the tableview
		this.table = new TableView<>();

		// add the 3 main sections to the view - read, update/delete, and create
		// nested containers for CRUD of table
		// HBoxes in BodyVBoxes
		this.updateOrDeleteBodyVBox.getChildren().addAll(this.updateOrDeleteControlsHBox,
				this.updateOrDeleteMsgLblHBox);
		this.addBodyVBox.getChildren().addAll(this.addControlsHBox, this.addMsgLblHBox);

		// TitledPanes in MainVBox; BodyVBoxes already nested in TitledPanes
		this.tabBodyVBox.getChildren().addAll(this.table, this.updateOrDeleteTitledPane, this.addTitledPane);

		// set content of Tab to MainVBox
		this.tab.setContent(this.tabBodyVBox);

		this.configureTitledPanes();
	}

	// PRIVATE METHODS
	// =============================================================================================
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
