package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.event.Event;

/**
 * Unmodifiable view of a Event list
 */
public interface ReadOnlyEventList {
    /**
     * Returns an unmodifiable view of the Event list.
     * This list will not contain any duplicate Events.
     */
    ObservableList<Event> getEventList();
}
