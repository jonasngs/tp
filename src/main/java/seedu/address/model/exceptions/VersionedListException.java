package seedu.address.model.exceptions;

/**
 * Represents an error which occurs during memory state transition in versioned lists
 */
public class VersionedListException extends Exception {
    public VersionedListException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code VersionedListException} with the specified detail {@code message} and {@code cause}.
     */
    public VersionedListException(String message, Throwable cause) {
        super(message, cause);
    }
}
