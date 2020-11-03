package seedu.address.logic.commands.modulelistcommands.modulelistexceptions;

/**
 * Represents an error which occurs during execution CAP related calculation
 */
public class CapCalculationException extends Exception {
    public CapCalculationException(String message) {
        super(message);
    }
    /**
     * Constructs a new {@code capCalculationException} with the specified detail {@code message} and {@code cause}.
     */
    public CapCalculationException(String message, Throwable cause) {
        super(message, cause);
    }

}
