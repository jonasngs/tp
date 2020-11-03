package seedu.address.model.event;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Event's name in the Scheduler.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class EventName {
    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    private final String name;

    /**
     * Creates a EventName object that represents the name of a Event.
     * @param name
     */
    public EventName(String name) {
        assert(name != null);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Checks if the given String name is valid based on the format.
     * @param name to be checked.
     * @return boolean.
     */
    public static boolean isValidName(String name) {
        return name.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object otherName) {
        if (otherName == null) {
            return false;
        } else if (this == otherName) {
            return true;
        } else if (otherName instanceof EventName) {
            EventName other = (EventName) otherName;
            return this.getName().equals(other.getName());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
