package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FindContactCriteriaTest {

    @Test
    public void addPredicate_nullPredicate_throwsNullPointerException() {
        FindContactCriteria findContactCriteria = new FindContactCriteria();
        assertThrows(NullPointerException.class, () -> findContactCriteria.addPredicate(null));
    }

    @Test
    public void equals() {
        FindContactCriteria firstFindContactCriteria = new FindContactCriteria();
        FindContactCriteria secondFindContactCriteria = new FindContactCriteria();

        // same object -> returns true
        assertTrue(firstFindContactCriteria.equals(firstFindContactCriteria));

        // null -> returns false
        assertFalse(firstFindContactCriteria.equals(null));

        // different type -> returns false
        assertFalse(firstFindContactCriteria.equals(10));

        // same value -> returns true
        assertTrue(firstFindContactCriteria.equals(secondFindContactCriteria));
    }
}
