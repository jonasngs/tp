package seedu.address.model.module.grade;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

/**
 * Represents an assignment in the Grade Tracker.
 * Guarantees: immutable; is valid
 */
public class Assignment {

    public static final String MESSAGE_ASSIGNMENT_NAME_CONSTRAINTS =
            "Assignment names should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String MESSAGE_ASSIGNMENT_PERCENTAGE_CONSTRAINTS =
            "Assignment percentage should be in the range 0 - 100";
    public static final String MESSAGE_ASSIGNMENT_RESULT_CONSTRAINTS =
            "Assignment result should be in the range 0.00 to 1.00";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final AssignmentName assignmentName;
    public final AssignmentPercentage assignmentPercentage;
    public final AssignmentResult assignmentResult;

    /**
     * Constructs a {@code Assignment}.
     *
     * @param assignmentName name of the assignment.
     * @param assignmentPercentage the percentage of the total grade that this assignment takes up.
     * @param assignmentResult the result achieved for this assignment. Range from 0 to 1.
     */
    public Assignment(AssignmentName assignmentName, AssignmentPercentage assignmentPercentage,
                      AssignmentResult assignmentResult) {
        requireNonNull(assignmentName);
        this.assignmentName = assignmentName;
        this.assignmentPercentage = assignmentPercentage;
        this.assignmentResult = assignmentResult;
    }

    /**
     * Default constructor for Assignment with only assignment name.
     */
    public Assignment(AssignmentName assignmentName) {
        this.assignmentName = assignmentName;
        this.assignmentPercentage = null;
        this.assignmentResult = null;
    }

    public Optional<AssignmentName> getAssignmentName() {
        return Optional.ofNullable(this.assignmentName);
    }

    public Optional<AssignmentPercentage> getAssignmentPercentage() {
        return Optional.ofNullable(this.assignmentPercentage);
    }

    public Optional<AssignmentResult> getAssignmentResult() {
        return Optional.ofNullable(this.assignmentResult);
    }

    public Assignment setAssignmentName(AssignmentName assignmentName) {
        return new Assignment(assignmentName, this.assignmentPercentage, assignmentResult);
    }

    public Assignment setAssignmentPercentage(AssignmentPercentage assignmentPercentage) {
        return new Assignment(this.assignmentName, assignmentPercentage, assignmentResult);
    }

    public Assignment setAssignmentResult(AssignmentResult assignmentResult) {
        return new Assignment(this.assignmentName, this.assignmentPercentage, assignmentResult);
    }

    /**
     * Returns true if both assignments have the same name.
     * This defines a weaker notion of equality between two assignments.
     *
     * @param otherAssignment other assignment to be compared
     * @return true if both assignment has the same name.
     */
    public boolean isSameAssignment(Assignment otherAssignment) {
        if (this == otherAssignment) {
            return true;
        }

        return getAssignmentName().equals((otherAssignment.getAssignmentName()));
    }

    @Override
    public String toString() {
        return String.format("Assignment %1$s is %2$s%% of the total grade and the result is %3$s",
                assignmentName, assignmentPercentage, assignmentResult);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Assignment // instanceof handles nulls
                && assignmentName.equals(((Assignment) other).assignmentName)
                && assignmentPercentage.equals(((Assignment) other).assignmentPercentage)
                && assignmentResult.equals(((Assignment) other).assignmentResult)); // state check
    }

    @Override
    public int hashCode() {
        return assignmentName.hashCode();
    }

}
