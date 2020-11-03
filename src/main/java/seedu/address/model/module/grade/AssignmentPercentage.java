package seedu.address.model.module.grade;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class AssignmentPercentage {

    public static final String MESSAGE_CONSTRAINTS =
            "Assignment percentages should be a value between 0 to 100 and it should not be blank.";

    public final double assignmentPercentage;

    /**
     * Constructs a {@code assignmentPercentage}.
     *
     * @param assignmentPercentage A valid assignment percentage.
     */
    public AssignmentPercentage(double assignmentPercentage) {
        requireNonNull(assignmentPercentage);
        checkArgument(isValidAssignmentPercentage(assignmentPercentage), MESSAGE_CONSTRAINTS);
        this.assignmentPercentage = assignmentPercentage;
    }

    /**
     * Returns true if a given double is a valid assignment percentage.
     */
    public static boolean isValidAssignmentPercentage(double test) {
        if (test <= 100 && test >= 0) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public String toString() {
        return String.valueOf(assignmentPercentage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssignmentPercentage // instanceof handles nulls
                && assignmentPercentage == ((AssignmentPercentage) other).assignmentPercentage); // state check
    }
}
