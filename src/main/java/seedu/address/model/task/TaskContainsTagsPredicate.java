package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Task}'s {@code Tag} matches any of the search tags provided by the user.
 */
public class TaskContainsTagsPredicate implements Predicate<Task> {

    /**
     * Tags provided by the user to search for tasks containing a matching tag.
     */
    private final Set<Tag> searchTags;

    /**
     * Creates and initialises a new TagContainsKeywordsPredicate.
     *
     * @param searchTags Set of search tags provided by the user.
     */
    public TaskContainsTagsPredicate(Set<Tag> searchTags) {
        assert !searchTags.isEmpty() : "At least one search tag must be present";
        this.searchTags = searchTags;
    }

    @Override
    public boolean test(Task task) {
        requireNonNull(task);
        boolean isTaskTagPresent = task.getTags().isPresent();
        if (isTaskTagPresent) {
            Set<Tag> taskTags = task.getTags().get();
            return searchTags.stream()
                    .anyMatch(tag -> taskTags.contains(tag));
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskContainsTagsPredicate // instanceof handles nulls
                && searchTags.equals(((TaskContainsTagsPredicate) other).searchTags)); // state check
    }
}
