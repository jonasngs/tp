package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code Date} matches the search date provided by the user.
 */
public class TaskMatchesDatePredicate implements Predicate<Task> {

    private final Date searchDate;

    /**
     * Creates and initialises a new TaskMatchesDatePredicate object.
     *
     * @param searchDate Date provided by the user to search for matching tasks.
     */
    public TaskMatchesDatePredicate(Date searchDate) {
        requireNonNull(searchDate);
        this.searchDate = searchDate;
    }

    @Override
    public boolean test(Task task) {
        requireNonNull(task);
        boolean isTaskDatePresent = task.getDate().isPresent();
        if (isTaskDatePresent) {
            return task.hasSameDate(this.searchDate);
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskMatchesDatePredicate // instanceof handles nulls
                && searchDate.equals(((TaskMatchesDatePredicate) other).searchDate)); // state check
    }

}
