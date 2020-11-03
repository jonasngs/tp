package seedu.address.model.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Represents the container that stores the start and end time of an Event.
 */
public class EventTime {
    private static final DateTimeFormatter validDateFormat = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Creates an EventTime object that stores the start and end time.
     * @param start start time and date.
     * @param end end time and date.
     */
    public EventTime(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Creates a EventTime that stores the start time of the event.
     *
     * @param start LocalDateTime of the start.
     */
    public EventTime(LocalDateTime start) {
        this.start = start;
        this.end = null;
    }

    /**
     * Checks if the given input is a valid date format.
     *
     * @param input date.
     * @return boolean is valid.
     */
    public static boolean isValidDateTime(String input) {
        try {
            LocalDateTime.parse(input);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Start time & date: ")
                .append(getStart())
                .append("End time & date: ")
                .append(getEnd());
        return builder.toString();
    }

    @Override
    public boolean equals(Object otherTime) {
        if (this == otherTime) {
            return true;
        } else if (otherTime == null) {
            return false;
        } else if (otherTime instanceof EventTime) {
            EventTime other = (EventTime) otherTime;
            return this.getStart().isEqual(other.getStart())
                    && this.getEnd().isEqual(other.getEnd());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.start, this.end);
    }
}
