package seedu.address.model.module.grade.comparator;

import java.util.Comparator;

import seedu.address.model.module.grade.Assignment;

/**
 * Comparator to compare two tasks name.
 */
public class AssignmentNameComparator implements Comparator<Assignment> {
    /**
     * Compare two assignments lexicographically.
     *
     * @param assignment first assignment
     * @param otherAssignment second assignment
     * @return -1 if value of assignment is lexicographically less than value of otherAssignment,
     *         0 if both assignment names are the same, and 1 if value of assignment is lexicographically
     *         greater than value of otherAssignment
     */
    @Override
    public int compare(Assignment assignment, Assignment otherAssignment) {
        return assignment.getAssignmentName().get().assignmentName.compareTo(
                otherAssignment.getAssignmentName().get().assignmentName);
    }
}
