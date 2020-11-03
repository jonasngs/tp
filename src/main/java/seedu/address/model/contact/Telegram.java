package seedu.address.model.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Contact's telegram in the contact list.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegram(String)}
 */
public class Telegram {

    public static final String MESSAGE_CONSTRAINTS =
            "Telegram username should adhere to the following requirements: \n"
                    + "1. Start with the \"@\" symbol\n"
                    + "2. Contain at least 5 characters not including the \"@\" symbol \n"
                    + "3. Contain only alphanumeric characters and underscore \n"
                    + "4. Should not be blank";

    /**
     * Constraints for a telegram username as specified by the Telegram application.
     */
    public static final String USERNAME_REGEX = "[a-zA-Z0-9_]{5,}";
    public static final String VALIDATION_REGEX = "@" + USERNAME_REGEX;

    /** String containing the telegram username. */
    public final String telegramUsername;

    /**
     * Creates and initialises a new Telegram object.
     *
     * @param telegramUsername Telegram username.
     */
    public Telegram(String telegramUsername) {
        requireNonNull(telegramUsername);
        checkArgument(isValidTelegram(telegramUsername), MESSAGE_CONSTRAINTS);
        this.telegramUsername = telegramUsername;
    }

    /**
     * Returns true if a given string is a valid telegram username.
     */
    public static boolean isValidTelegram(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.telegramUsername;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Telegram // instanceof handles nulls
                && this.telegramUsername.equals(((Telegram) other).telegramUsername)); // state check
    }

    @Override
    public int hashCode() {
        return this.telegramUsername.hashCode();
    }

}
