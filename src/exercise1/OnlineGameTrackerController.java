package exercise1;

import javafx.collections.ObservableList;

public abstract class OnlineGameTrackerController {

	// need connection to the database - used by all controllers
	protected GameDatabaseContext db = new GameDatabaseContext();

	// abstract methods
	// all controllers in this application deal with their
	// own tables.
	// NOTE: Not all CRUD actions are in the abstract controller
	// because different tables will require different method
	// signatures
	abstract ObservableList<?> selectAll();
}
