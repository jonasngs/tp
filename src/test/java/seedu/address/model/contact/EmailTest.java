package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Email(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new Email(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> Email.isValidEmail(null));

        // blank email
        assertFalse(Email.isValidEmail("")); // empty string
        assertFalse(Email.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(Email.isValidEmail("@example.com")); // missing local part
        assertFalse(Email.isValidEmail("johndoeexample.com")); // missing '@' symbol
        assertFalse(Email.isValidEmail("johndoe@")); // missing domain name

        // invalid parts
        assertFalse(Email.isValidEmail("johndoe@-")); // invalid domain name
        assertFalse(Email.isValidEmail("johndoe@exam_ple.com")); // underscore in domain name
        assertFalse(Email.isValidEmail("john doe@example.com")); // spaces in local part
        assertFalse(Email.isValidEmail("johndoe@exam ple.com")); // spaces in domain name
        assertFalse(Email.isValidEmail(" johndoe@example.com")); // leading space
        assertFalse(Email.isValidEmail("johndoe@example.com ")); // trailing space
        assertFalse(Email.isValidEmail("johndoe@@example.com")); // double '@' symbol
        assertFalse(Email.isValidEmail("john@doe@example.com")); // '@' symbol in local part
        assertFalse(Email.isValidEmail("johndoe@example@com")); // '@' symbol in domain name
        assertFalse(Email.isValidEmail("johndoe@.example.com")); // domain name starts with a period
        assertFalse(Email.isValidEmail("johndoe@example.com.")); // domain name ends with a period
        assertFalse(Email.isValidEmail("johndoe@-example.com")); // domain name starts with a hyphen
        assertFalse(Email.isValidEmail("johndoe@example.com-")); // domain name ends with a hyphen

        // valid email
        assertTrue(Email.isValidEmail("JohnDoe_1190@example.com"));
        assertTrue(Email.isValidEmail("n@bc")); // minimal
        assertTrue(Email.isValidEmail("testing@localhost")); // alphabets only
        assertTrue(Email.isValidEmail("!#$&%'*+/=?`{|}~^.-@example.org")); // special characters local part
        assertTrue(Email.isValidEmail("123@1456")); // numeric local part and domain name
        assertTrue(Email.isValidEmail("a2+^$be!@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(Email.isValidEmail("john_doe@very-very-very-long-example.com")); // long domain name
        assertTrue(Email.isValidEmail("you.can.do.it.if.you.believe@example.com")); // long local part
    }
}
