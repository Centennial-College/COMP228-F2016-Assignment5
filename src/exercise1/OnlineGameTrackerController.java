package exercise1;

import javafx.collections.ObservableList;

/**
 * @file OnlineGameTrackerController.java
 * @author Kevin Ma | #: 300867968
 * @date December 7, 2016
 * @version 0.4.0 redesigned application to follow MVC design pattern - added
 *          abstract View and Controller classes
 * @description This abstract class defines the behaviors of controllers for
 *              this application at a macro level.
 */

public abstract class OnlineGameTrackerController {
	// INSTANCE VARIABLES
	// =============================================================================================
	// connection to the database - used by all controllers
	protected GameDatabaseContext db = new GameDatabaseContext();

	// CONSTRUCTOR
	// =============================================================================================
	protected OnlineGameTrackerController() {
		db = new GameDatabaseContext();
	}

	// ABSTRACT METHODS
	// =============================================================================================
	/**
	 * Selects and returns all rows from a table.
	 * 
	 * @return all rows of data in a given table, depends on the Model used
	 */
	abstract ObservableList<?> selectAll();
}
