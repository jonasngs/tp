package seedu.address.logic.commands.gradetrackercommands;

import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.module.grade.AssignmentName;
import seedu.address.model.module.grade.AssignmentPercentage;
import seedu.address.model.module.grade.AssignmentResult;

/**
 * Stores the details to edit the assignment with. Each non-empty field value will replace the
 * corresponding field value of the assignment.
 */
public class EditAssignmentDescriptor {
    private AssignmentName assignmentName;
    private AssignmentPercentage assignmentPercentage;
    private AssignmentResult assignmentResult;

    public EditAssignmentDescriptor() {}

    /**
     * Copy constructor.
     */
    public EditAssignmentDescriptor(EditAssignmentDescriptor toCopy) {
        setAssignmentName(toCopy.assignmentName);
        setAssignmentPercentage(toCopy.assignmentPercentage);
        setAssignmentResult(toCopy.assignmentResult);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(assignmentName, assignmentPercentage, assignmentResult);
    }

    public void setAssignmentName(AssignmentName assignmentName) {
        this.assignmentName = assignmentName;
    }

    public Optional<AssignmentName> getAssignmentName() {
        return Optional.ofNullable(assignmentName);
    }

    public void setAssignmentPercentage(AssignmentPercentage assignmentPercentage) {
        this.assignmentPercentage = assignmentPercentage;
    }

    public Optional<AssignmentPercentage> getAssignmentPercentage() {
        return Optional.ofNullable(assignmentPercentage);
    }

    public void setAssignmentResult(AssignmentResult assignmentResult) {
        this.assignmentResult = assignmentResult;
    }

    public Optional<AssignmentResult> getAssignmentResult() {
        return Optional.ofNullable(assignmentResult);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditAssignmentDescriptor)) {
            return false;
        }

        // state check
        EditAssignmentDescriptor e = (EditAssignmentDescriptor) other;

        return getAssignmentName().equals(e.getAssignmentName())
                && getAssignmentPercentage() == e.getAssignmentPercentage()
                && getAssignmentResult() == e.getAssignmentResult();
    }
}

