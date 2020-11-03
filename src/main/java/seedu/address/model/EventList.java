package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.event.UniqueEventList;

public class EventList implements ReadOnlyEventList {
    private final UniqueEventList events = new UniqueEventList();

    public EventList() {}

    /**
     * Creates the EventList from an existing EventList.
     * @param toBeCopied EventList to copy.
     */
    public EventList(ReadOnlyEventList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * * Resets the existing data of this {@code EventList} with {@code newData}.
     */
    public void resetData(ReadOnlyEventList newData) {
        requireNonNull(newData);
        setEvent(newData.getEventList());
    }

    /**
     * Returns true if a Event with the same identity as {@code Event} exists in the Event list.
     */
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return events.contains(event);
    }

    /**
     * Adds an Event to the Event list.
     * The Event must not already exist in the Event list.
     */
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Replaces the contents of the Event list with {@code Event}.
     * {@code Event} must not contain duplicate Event.
     */
    public void setEvent(List<Event> event) {
        this.events.setEvents(event);
    }

    /**
     * Replaces the given module {@code target} in the list with {@code editedEvent}.
     * {@code target} must exist in the Eventlist.
     * The Event identity of {@code editedEvent} must not be the same as another existing Event in the Eventlist.
     */
    public void setEvent(Event target, Event editedEvent) {
        requireNonNull(editedEvent);
        events.setEvent(target, editedEvent);
    }

    /**
     * Removes {@code key} from this {@code EventList}.
     * {@code key} must exist in the Eventlist.
     */
    public void removeEvent(Event key) {
        events.remove(key);
    }

    @Override
    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList();
    }

    @Override
    public String toString() {
        return events.asUnmodifiableObservableList().size() + " events";
        // TODO: refine later
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventList // instanceof handles nulls
                && events.equals(((EventList) other).events));
    }

    @Override
    public int hashCode() {
        return events.hashCode();
    }
}
