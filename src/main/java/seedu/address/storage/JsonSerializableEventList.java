package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.EventList;
import seedu.address.model.ReadOnlyEventList;
import seedu.address.model.event.Event;

@JsonRootName(value = "eventlist")
public class JsonSerializableEventList {

    public static final String MESSAGE_DUPLICATE_MODULE = "Module list contains duplicate moduke(s).";

    private final List<JsonAdaptedEvent> events = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableEventList} with the given Event.
     */
    @JsonCreator
    public JsonSerializableEventList(@JsonProperty("ents") List<JsonAdaptedEvent> events) {
        this.events.addAll(this.events);
    }

    /**
     * Converts a given {@code ReadOnlyEventList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableEventList}.
     */
    public JsonSerializableEventList(ReadOnlyEventList source) {
        events.addAll(source.getEventList().stream().map(JsonAdaptedEvent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Event list into the model's {@code UniqueEventList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public EventList toModelType() throws IllegalValueException {
        EventList eventList = new EventList();
        for (JsonAdaptedEvent jsonAdaptedEvent : events) {
            Event event = jsonAdaptedEvent.toModelType();
            if (eventList.hasEvent(event)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE);
            }
            eventList.addEvent(event);
        }
        return eventList;
    }
}
