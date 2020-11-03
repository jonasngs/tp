package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ContactBuilder;

public class ContactTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Contact contact = new ContactBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> contact.getTags().remove(0));
    }

    @Test
    public void constructor_nullArguments_throwsNullPointerException() {
        ContactBuilder contactBuilder = new ContactBuilder();

        // null Name -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> contactBuilder.withName(null).build());

        // null Email -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> contactBuilder.withEmail(null).build());

        // null Telegram -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> contactBuilder.withTelegram(null).build());
    }

    @Test
    public void isSameContact() {
        // same object -> returns true
        assertTrue(ALICE.isSameContact(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameContact(null));

        // same name, different telegram and email -> returns false
        Contact editedAlice = new ContactBuilder(ALICE).withEmail(VALID_EMAIL_BOB)
                .withTelegram(VALID_TELEGRAM_AMY).build();
        assertFalse(ALICE.isSameContact(editedAlice));

        // different name -> returns false
        editedAlice = new ContactBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameContact(editedAlice));

        // same name, same email, different attributes -> returns true
        editedAlice = new ContactBuilder(ALICE).withTelegram(VALID_TELEGRAM_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        assertTrue(ALICE.isSameContact(editedAlice));

        // same name, same telegram, different email -> returns false
        editedAlice = new ContactBuilder(ALICE).withEmail(VALID_EMAIL_AMY).build();
        assertFalse(ALICE.isSameContact(editedAlice));

        // same name, same email, same telegram, different tags -> returns true
        editedAlice = new ContactBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameContact(editedAlice));
    }

    @Test
    public void markAsImportant_validContact_success() {
        Contact contact = new ContactBuilder().build();
        assertTrue(contact.markAsImportant().isImportant());
    }

    @Test
    public void markAsNotImportant_validContact_success() {
        Contact contact = new ContactBuilder().build();
        assertFalse(contact.markAsNotImportant().isImportant());
    }

    @Test
    public void equals() {
        // same values -> returns true
        Contact aliceCopy = new ContactBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(10));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Contact editedAlice = new ContactBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new ContactBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different telegram -> returns false
        editedAlice = new ContactBuilder(ALICE).withTelegram(VALID_TELEGRAM_AMY).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ContactBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
