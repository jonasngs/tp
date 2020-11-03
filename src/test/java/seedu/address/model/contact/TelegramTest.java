package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Telegram(null));
    }

    @Test
    public void constructor_invalidTelegram_throwsIllegalArgumentException() {
        String invalidTelegram = "";
        assertThrows(IllegalArgumentException.class, () -> new Telegram(invalidTelegram));
    }

    @Test
    public void isValidTelegram() {
        // null telegram
        assertThrows(NullPointerException.class, () -> Telegram.isValidTelegram(null));

        // invalid telegram
        assertFalse(Telegram.isValidTelegram("")); // empty string
        assertFalse(Telegram.isValidTelegram(" ")); // spaces only
        assertFalse(Telegram.isValidTelegram("@^")); // contains non-alphanumeric characters only
        assertFalse(Telegram.isValidTelegram("@peter*")); // contains non-alphanumeric characters
        assertFalse(Telegram.isValidTelegram("@pete")); // contains less than 5 characters after "@"
        assertFalse(Telegram.isValidTelegram("peterjack")); // missing "@" character
        assertFalse(Telegram.isValidTelegram("@peter jack")); // contains white space

        // valid telegram
        assertTrue(Telegram.isValidTelegram("@peter")); // alphabets only
        assertTrue(Telegram.isValidTelegram("@12345")); // numbers only
        assertTrue(Telegram.isValidTelegram("@peter123")); // alphanumeric characters
        assertTrue(Telegram.isValidTelegram("@peter_123")); // alphanumeric characters with underscore
    }
}
