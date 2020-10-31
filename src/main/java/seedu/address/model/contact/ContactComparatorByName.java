package seedu.address.model.contact;

import java.util.Comparator;

/**
 * Comparator to compare two Contacts based on their name.
 */
public class ContactComparatorByName implements Comparator<Contact> {
    /**
     * Compare two Contacts based on their name, lexicographically.
     *
     * @param contact first contact
     * @param otherContact second contact
     * @return -1 if contact's name is lexicographically less than value of otherContact's name,
     *         0 if both contacts name are the same, and 1 if value of contact's name is lexicographically
     *         greater than value of otherContact's name.
     */
    @Override
    public int compare(Contact contact, Contact otherContact) {
        int value = contact.getName().getName().compareToIgnoreCase(otherContact.getName().getName());
        if (value > 0) {
            return 1;
        } else if (value == 0) {
            return value;
        } else {
            return -1;
        }
    }
}
