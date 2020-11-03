package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.UniqueContactList;

/**
 * Wraps all data at the contact list level
 * Duplicates are not allowed (by .isSameContact comparison)
 */
public class ContactList implements ReadOnlyContactList {

    private final UniqueContactList contacts;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        contacts = new UniqueContactList();
    }

    public ContactList() {}

    /**
     * Creates a Contact list using the Contacts in the {@code toBeCopied}
     */
    public ContactList(ReadOnlyContactList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * * Resets the existing data of this {@code ContactList} with {@code newData}.
     */
    public void resetData(ReadOnlyContactList newData) {
        requireNonNull(newData);

        setContact(newData.getContactList());
    }

    //// person-level operations

    /**
     * Returns true if a contact with the same identity as {@code contact} exists in the contact list.
     */
    public boolean hasContact(Contact contact) {
        requireNonNull(contact);
        return contacts.contains(contact);
    }

    /**
     * Adds a contact to the contact list.
     * The contact must not already exist in the contact list.
     */
    public void addContact(Contact m) {
        contacts.add(m);
    }
    /**
     * Replaces the contents of the contact list with {@code contact}.
     * {@code contact} must not contain duplicate contact.
     */
    public void setContact(List<Contact> contacts) {
        this.contacts.setContacts(contacts);
    }

    /**
     * Replaces the given contact {@code target} in the list with {@code editedContact}.
     * {@code target} must exist in the contactlist.
     * The contact identity of {@code editedContact} must not be the same as another
     * contact existing in the contactlist.
     */
    public void setContact(Contact target, Contact editedContact) {
        requireNonNull(editedContact);
        contacts.setContact(target, editedContact);
    }

    /**
     * Removes {@code key} from this {@code ContactList}.
     * {@code key} must exist in the contactlist.
     */
    public void removeContact(Contact key) {
        contacts.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return contacts.asUnmodifiableObservableList().size() + " contacts";
    }

    @Override
    public ObservableList<Contact> getContactList() {
        return contacts.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactList // instanceof handles nulls
                && contacts.equals(((ContactList) other).contacts));
    }

    @Override
    public int hashCode() {
        return contacts.hashCode();
    }
}
