package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Stores the list of criteria to be used when finding matching {@code Task}.
 */
public class FindTaskCriteria {

    /**
     * List of predicates to be used to test for matching tasks.
     */
    private final List<Predicate<Task>> predicateList = new ArrayList<>();

    /**
     * Adds a predicate into the list of predicates.
     *
     * @param predicate Predicate to be added.
     */
    public void addPredicate(Predicate<Task> predicate) {
        requireNonNull(predicate);
        predicateList.add(predicate);
    }

    /**
     * Composes all the predicates in the predicateList field into one predicate to
     * be used to test for matching tasks.
     *
     * @return Predicate composed of each individual predicate in predicateList.
     */
    public Predicate<Task> getFindTaskPredicate() {
        assert !predicateList.isEmpty() : "Predicate List cannot be empty";
        Predicate<Task> findTaskPredicate = predicateList.get(0);
        for (int i = 1; i < predicateList.size(); i++) {
            findTaskPredicate = findTaskPredicate.and(predicateList.get(i));
        }
        return findTaskPredicate;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTaskCriteria // instanceof handles nulls
                && predicateList.equals(((FindTaskCriteria) other).predicateList));
    }
}

