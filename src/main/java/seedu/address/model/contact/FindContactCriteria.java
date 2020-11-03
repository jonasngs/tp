package seedu.address.model.contact;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Stores the list of criteria to be used when finding matching {@code Contact}.
 */
public class FindContactCriteria {

    /**
     * List of predicates to be used to test for matching contacts.
     */
    private final List<Predicate<Contact>> predicateList = new ArrayList<>();

    /**
     * Adds a predicate into the list of predicates.
     *
     * @param predicate Predicate to be added.
     */
    public void addPredicate(Predicate<Contact> predicate) {
        requireNonNull(predicate);
        predicateList.add(predicate);
    }

    /**
     * Composes all the predicates in the predicateList field into one predicate to
     * be used to test for matching contacts.
     *
     * @return Predicate composed of each individual predicate in predicateList.
     */
    public Predicate<Contact> getFindContactPredicate() {
        assert !predicateList.isEmpty() : "Predicate List cannot be empty";
        Predicate<Contact> findContactPredicate = predicateList.get(0);
        for (int i = 1; i < predicateList.size(); i++) {
            findContactPredicate = findContactPredicate.and(predicateList.get(i));
        }
        return findContactPredicate;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindContactCriteria // instanceof handles nulls
                && predicateList.equals(((FindContactCriteria) other).predicateList));
    }
}
