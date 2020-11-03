package seedu.address.model.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FindEventCriteria {
    /**
     * List of predicates to be used to test for matching events.
     */
    private final List<Predicate<Event>> predicateList = new ArrayList<>();

    /**
     * Adds a predicate into the list of predicates.
     *
     * @param predicate Predicate to be added.
     */
    public void addPredicate(Predicate<Event> predicate) {
        predicateList.add(predicate);
    }

    /**
     * Composes all the predicates in the predicateList field into one predicate to
     * be used to test for matching events.
     *
     * @return Predicate composed of each individual predicate in predicateList.
     */
    public Predicate<Event> getFindEventPredicate() {
        assert !predicateList.isEmpty() : "Predicate List cannot be empty";
        Predicate<Event> findEventPredicate = predicateList.get(0);
        for (int i = 1; i < predicateList.size(); i++) {
            findEventPredicate = findEventPredicate.and(predicateList.get(i));
        }
        return findEventPredicate;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindEventCriteria // instanceof handles nulls
                && predicateList.equals(((FindEventCriteria) other).predicateList));
    }
}
