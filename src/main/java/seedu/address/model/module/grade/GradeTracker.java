package seedu.address.model.module.grade;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import seedu.address.model.module.grade.comparator.AssignmentNameComparator;

/**
 * Represents an association class that tracks the assignments and grade for a module.
 */
public class GradeTracker implements ReadOnlyGradeTracker {
    public static final String MESSAGE_INVALID_GRADE =
            "Grades should be provided in the range from 0.00 to 1.00.";
    public static final String MESSAGE_INVALID_GRADEPOINT =
            "GradePoint should be given as a decimal from 0.00 to 5.00.";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT =
            "Assignments cannot be repeated.";
    private final UniqueAssignmentList assignments;
    private final AssignmentNameComparator comparator = new AssignmentNameComparator();
    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        assignments = new UniqueAssignmentList();
    }
    private Grade grade;
    private GradePoint gradePoint;

    /**
     * Creates a GradeTracker that stores the assignment, grades and grade point for a module.
     */
    public GradeTracker() {
        this.grade = new Grade(0);
        gradePoint = null;
    }

    /**
     * Creates a GradeTracker that stores the assignment, grades and grade point for a module.
     * @param gradePoint GradePoint for a completed module
     */
    public GradeTracker(double gradePoint) {
        this.grade = new Grade(0);
        this.gradePoint = new GradePoint(gradePoint);
    }

    public void setGrade(Grade newGrade) {
        this.grade = newGrade;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGradePoint(GradePoint newGradePoint) {
        this.gradePoint = newGradePoint;
    }

    public Optional<GradePoint> getGradePoint() {
        return Optional.ofNullable(gradePoint);
    }

    public void addAssignment(Assignment newAssignment) {
        assignments.add(newAssignment);
    }

    /**
     * Removes {@code key} from this {@code assignments}.
     * {@code key} must exist in the assignments.
     */
    public void removeAssignment(Assignment key) {
        assignments.remove(key);
    }

    public List<Assignment> getSortedAssignments() {
        List<Assignment> sortedAssignments = new SortedList<Assignment>(assignments
                .asUnmodifiableObservableList(), comparator);
        return sortedAssignments;
    }

    @Override
    public ObservableList<Assignment> getAssignments() {
        return assignments.asUnmodifiableObservableList();
    }

    /**
     * Replaces the given assignment {@code targetAssignment} in the list with {@code editedAssignment}.
     * {@code targetAssignment} must exist in the grade tracker.
     * The name of the assignment {@code editedAssignment} must not be the same as another
     * existing assignment in the grade tracker.
     */
    public void setAssignment(Assignment targetAssignment, Assignment editedAssignment) {
        requireNonNull(editedAssignment);
        assignments.setAssignment(targetAssignment, editedAssignment);
    }

    /**
     * Checks if the Grade Tracker is valid.
     *
     * @param gradeTracker grade tracker to check.
     * @return true if the grade tracker is valid.
     */
    public static boolean isValidGradeTracker(GradeTracker gradeTracker) {
        boolean areAssignmentsValid = true;
        for (Assignment eachAssignment: gradeTracker.assignments) {
            if (!AssignmentName.isValidAssignmentName(eachAssignment.getAssignmentName().get().assignmentName)
                    && AssignmentPercentage.isValidAssignmentPercentage(
                            eachAssignment.getAssignmentPercentage().get().assignmentPercentage)
                    && AssignmentResult.isValidAssignmentResult(
                            eachAssignment.getAssignmentResult().get().assignmentResult)) {
                areAssignmentsValid = false;
                break;
            }
        }
        if (Grade.isValidGrade(gradeTracker.grade.gradeResult) && areAssignmentsValid) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks for duplicated assignments in the module.
     *
     * @param otherAssignment the assignment being checked.
     * @return true if the assignment already exists
     */
    public boolean containsDuplicateAssignment(Assignment otherAssignment) {
        for (Assignment eachAssignment : assignments) {
            if (eachAssignment.equals(otherAssignment)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof GradeTracker) {
            GradeTracker gT = (GradeTracker) other;
            return this.grade.equals(gT.grade)
                    && this.assignments.equals(gT.assignments);
        } else {
            return false;
        }
    }
}
