package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code Name} matches any of the search keywords provided by the user.
 */
public class NameContainsKeywordsPredicate implements Predicate<Task> {

    private final List<String> keywords;

    /**
     * Creates and initialises a NameContainsKeywordsPredicate object with a list of keywords.
     *
     * @param keywords List of keywords.
     */
    public NameContainsKeywordsPredicate(List<String> keywords) {
        assert !keywords.isEmpty() : "At least one search keyword must be present";
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        requireNonNull(task);
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getName().get().getValue(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
