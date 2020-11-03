package seedu.address.model.module.grade.exceptions;

/**
 * Signals that the operation will result in duplicate assignments (Assignments are considered duplicates
 * if they have the same name, type, and date).
 */
public class DuplicateAssignmentException extends RuntimeException {
    public DuplicateAssignmentException() {
        super("Operation would result in duplicate assignments");
    }
}
