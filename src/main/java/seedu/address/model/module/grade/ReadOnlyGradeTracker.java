package seedu.address.model.module.grade;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of a GradeTracker
 */
public interface ReadOnlyGradeTracker {

    /**
     * Returns an unmodifiable view of the assignments in GradeTracker.
     * This list will not contain any duplicate assignments.
     */
    ObservableList<Assignment> getAssignments();
}
