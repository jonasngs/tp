package seedu.address.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.module.ModuleName;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Event}.
 */
public class JsonAdaptedEvent {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";

    private final String name;
    private final String dateTime;
    // private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given Event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("name") String name,
                            @JsonProperty("date") String dateTime) {
        this.name = name;
        this.dateTime = dateTime;
        // if (tagged != null) {
        //     this.tagged.addAll(tagged);
        // }
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        name = source.getName().getName();
        dateTime = source.getTime().getStart().toString();
        //tagged.addAll(source.getTags().stream()
        //        .map(JsonAdaptedTag::new)
        //        .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted Event object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Event.
     */
    public Event toModelType() throws IllegalValueException {
        final List<Tag> eventTags = new ArrayList<>();
        // for (JsonAdaptedTag tag : tagged) {
        //     eventTags.add(tag.toModelType());
        // }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventName.class.getSimpleName()));
        }
        if (!EventName.isValidName(name)) {
            throw new IllegalValueException(EventName.MESSAGE_CONSTRAINTS);
        }
        final EventName eventName = new EventName(name);

        if (dateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventTime.class.getSimpleName()));
        }
        if (!EventTime.isValidDateTime(dateTime)) {
            throw new IllegalValueException(ModuleName.MESSAGE_CONSTRAINTS);
        }
        final LocalDateTime time = LocalDateTime.parse(dateTime);
        final EventTime eventTime = new EventTime(time);

        // final Set<Tag> modelTags = new HashSet<>(eventTags);
        // TODO: Implement tags in event
        return new Event(eventName, eventTime);
    }
}
