package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.ContactBuilder;

public class ContactContainsTagsPredicateTest {

    @Test
    public void equals() {

        Set<Tag> firstPredicateTagSet = SampleDataUtil.getTagSet("first");
        Set<Tag> secondPredicateTagSet = SampleDataUtil.getTagSet("first", "second");

        ContactContainsTagsPredicate firstPredicate = new ContactContainsTagsPredicate(firstPredicateTagSet);
        ContactContainsTagsPredicate secondPredicate = new ContactContainsTagsPredicate(secondPredicateTagSet);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ContactContainsTagsPredicate firstPredicateCopy = new ContactContainsTagsPredicate(firstPredicateTagSet);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(6));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate values -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_contactContainsTags_returnsTrue() {
        // One tag
        ContactContainsTagsPredicate predicate =
                new ContactContainsTagsPredicate(SampleDataUtil.getTagSet("first"));
        assertTrue(predicate.test(new ContactBuilder().withTags("first").build()));

        // Multiple tags
        predicate = new ContactContainsTagsPredicate(SampleDataUtil.getTagSet("first", "second"));
        assertTrue(predicate.test(new ContactBuilder().withTags("first", "second").build()));

        // Only one matching tag
        predicate = new ContactContainsTagsPredicate(SampleDataUtil.getTagSet("first", "second"));
        assertTrue(predicate.test(new ContactBuilder().withTags("first", "third").build()));

        // Mixed-case tags
        predicate = new ContactContainsTagsPredicate(SampleDataUtil.getTagSet("fiRsT", "seCONd"));
        assertTrue(predicate.test(new ContactBuilder().withTags("first", "second").build()));
    }

    @Test
    public void test_contactDoesNotContainTags_returnsFalse() {
        ContactContainsTagsPredicate predicate =
                new ContactContainsTagsPredicate(SampleDataUtil.getTagSet("second"));

        // Contact has no tags
        assertFalse(predicate.test(new ContactBuilder().withTags().build()));

        // Non-matching tag
        assertFalse(predicate.test(new ContactBuilder().withTags("first").build()));
    }

}
