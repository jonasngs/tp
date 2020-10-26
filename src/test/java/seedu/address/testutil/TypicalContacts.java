package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ContactList;
import seedu.address.model.ModuleList;
import seedu.address.model.contact.Contact;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalContacts {

    public static final Contact ALICE = new ContactBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withTags("friends").build();
    public static final Contact BENSON = new ContactBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com")
            .withTags("owesMoney", "friends").build();
    public static final Contact CARL = new ContactBuilder().withName("Carl Kurz")
            .withEmail("heinz@example.com").build();
    public static final Contact DANIEL = new ContactBuilder().withName("Daniel Meier")
            .withEmail("cornelia@example.com").withTags("friends").build();
    public static final Contact ELLE = new ContactBuilder().withName("Elle Meyer")
            .withEmail("werner@example.com").build();
    public static final Contact FIONA = new ContactBuilder().withName("Fiona Kunz")
            .withEmail("lydia@example.com").build();
    public static final Contact GEORGE = new ContactBuilder().withName("George Best")
            .withEmail("anna@example.com").build();

    // Manually added
    public static final Contact HOON = new ContactBuilder().withName("Hoon Meier")
            .withEmail("stefan@example.com").build();
    public static final Contact IDA = new ContactBuilder().withName("Ida Mueller")
            .withEmail("hans@example.com").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Contact AMY = new ContactBuilder().withName(VALID_NAME_AMY)
            .withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Contact BOB = new ContactBuilder().withName(VALID_NAME_BOB)
            .withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalContacts() {} // prevents instantiation

    /**
     * Returns a {@code ContactList} with all the typical contacts.
     */
    public static ContactList getTypicalContactList() {
        ContactList contactList = new ContactList();
        for (Contact contact : getTypicalContacts()) {
            contactList.addContact(contact);
        }
        return contactList;
    }

    public static List<Contact> getTypicalContacts() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
