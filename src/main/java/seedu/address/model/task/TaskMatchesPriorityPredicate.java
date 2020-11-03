package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code Priority} matches the search priority provided by the user.
 */
public class TaskMatchesPriorityPredicate implements Predicate<Task> {

    private final Priority searchPriority;

    /**
     * Creates and initialises a TaskMatchesPriorityPredicate object.
     *
     * @param searchPriority Priority provided by users to search for matching tasks.
     */
    public TaskMatchesPriorityPredicate(Priority searchPriority) {
        requireNonNull(searchPriority);
        this.searchPriority = searchPriority;
    }

    @Override
    public boolean test(Task task) {
        requireNonNull(task);
        boolean isTaskPriorityPresent = task.getPriority().isPresent();
        if (isTaskPriorityPresent) {
            return task.hasSamePriority(searchPriority);
        }
        return false;
    }

}
