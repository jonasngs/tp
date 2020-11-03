package seedu.address.model.contact;


import static java.util.Objects.requireNonNull;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Contact}'s {@code Tag} matches any of the search tags provided by the user.
 */
public class ContactContainsTagsPredicate implements Predicate<Contact> {

    /**
     * Tags provided by the user to search for contacts containing a matching tag.
     */
    private final Set<Tag> searchTags;

    /**
     * Creates and initialises a new ContactContainsTagsPredicate.
     *
     * @param searchTags Set of search tags provided by the user.
     */
    public ContactContainsTagsPredicate(Set<Tag> searchTags) {
        assert !searchTags.isEmpty() : "At least one search tag must be present";
        this.searchTags = searchTags;
    }

    @Override
    public boolean test(Contact contact) {
        requireNonNull(contact);
        boolean isContactTagPresent = !contact.getTags().isEmpty();
        if (isContactTagPresent) {
            Set<Tag> taskTags = contact.getTags();
            return searchTags.stream()
                    .anyMatch(tag -> taskTags.contains(tag));
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactContainsTagsPredicate // instanceof handles nulls
                && searchTags.equals(((ContactContainsTagsPredicate) other).searchTags)); // state check
    }
}
