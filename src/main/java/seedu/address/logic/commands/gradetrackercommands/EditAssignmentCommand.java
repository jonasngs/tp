package seedu.address.logic.commands.gradetrackercommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_PERCENTAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_RESULT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.grade.Assignment;
import seedu.address.model.module.grade.AssignmentName;
import seedu.address.model.module.grade.AssignmentPercentage;
import seedu.address.model.module.grade.AssignmentResult;

public class EditAssignmentCommand extends Command {
    public static final String COMMAND_WORD = "editassignment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the assignment identified "
            + "by the index number used in the displayed gradetracker for the specified module. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "MODULE NAME] "
            + "[" + PREFIX_ASSIGNMENT_NAME + "ASSIGNMENT NAME] "
            + "[" + PREFIX_ASSIGNMENT_PERCENTAGE + "ASSIGNMENT PERCENTAGE] "
            + "[" + PREFIX_ASSIGNMENT_RESULT + "ASSIGNMENT RESULT]... \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "CS2101 "
            + PREFIX_ASSIGNMENT_NAME + "Oral Presentation 2";

    public static final String MESSAGE_EDIT_ASSIGNMENT_SUCCESS = "Edited assignment: \n%1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_MODULE_INVALID = "The module to edit assignment is invalid.";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "This assignment already exists in the gradetracker.";

    private final Logger logger = LogsCenter.getLogger(EditAssignmentCommand.class);

    private final Index index;
    private final ModuleName moduleName;
    private final EditAssignmentDescriptor editAssignmentDescriptor;

    /**
     * @param index of the assignment in the gradetracker of the module
     * @param editAssignmentDescriptor details to edit the assignment with
     */
    public EditAssignmentCommand(Index index, ModuleName moduleName,
                                 EditAssignmentDescriptor editAssignmentDescriptor) {
        requireNonNull(index);
        requireNonNull(moduleName);
        requireNonNull(editAssignmentDescriptor);

        logger.info("Editing assignment at position " + index.toString() + " from:" + moduleName.toString() + "");
        this.index = index;
        this.moduleName = moduleName;
        this.editAssignmentDescriptor = new EditAssignmentDescriptor(editAssignmentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();
        Module moduleWithAssignment = null;

        for (Module eachModule : lastShownList) {
            if (eachModule.getName().equals(moduleName)) {
                moduleWithAssignment = eachModule;
                break;
            }
        }

        if (moduleWithAssignment == null) {
            throw new CommandException(MESSAGE_MODULE_INVALID);
        }

        if (index.getZeroBased() >= moduleWithAssignment.getGradeTracker().getAssignments().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
        }

        Assignment assignmentToEdit = moduleWithAssignment.getGradeTracker().getSortedAssignments()
                .get(index.getZeroBased());
        Assignment editedAssignment = createEditedAssignment(assignmentToEdit, editAssignmentDescriptor);

        if (!assignmentToEdit.isSameAssignment(editedAssignment)
                && moduleWithAssignment.getGradeTracker().containsDuplicateAssignment(editedAssignment)) {
            throw new CommandException(MESSAGE_DUPLICATE_ASSIGNMENT);
        }

        moduleWithAssignment.getGradeTracker().setAssignment(assignmentToEdit, editedAssignment);

        logger.info("Assignment has been edited: " + assignmentToEdit.toString());

        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        return new CommandResult(String.format(MESSAGE_EDIT_ASSIGNMENT_SUCCESS, editedAssignment));
    }

    /**
     * Creates and returns an {@code Assignment} with the details of {@code assignmentToEdit}
     * edited with {@code editAssignmentDescriptor}.
     */
    private static Assignment createEditedAssignment(Assignment assignmentToEdit,
                                                     EditAssignmentDescriptor editAssignmentDescriptor) {
        assert assignmentToEdit != null : "The assignment to be edited must currently exist.";
        assert !assignmentToEdit.getAssignmentName().isEmpty();

        AssignmentName assignmentName = editAssignmentDescriptor.getAssignmentName().orElse(assignmentToEdit
                .getAssignmentName().get());

        Assignment editedAssignment = new Assignment(assignmentName);

        if (assignmentToEdit.getAssignmentPercentage().isPresent() || editAssignmentDescriptor
                .getAssignmentPercentage().isPresent()) {
            AssignmentPercentage updatedPercentage = editAssignmentDescriptor.getAssignmentPercentage()
                    .orElseGet(() -> assignmentToEdit.getAssignmentPercentage().get());
            editedAssignment = editedAssignment.setAssignmentPercentage(updatedPercentage);
        }

        if (assignmentToEdit.getAssignmentResult().isPresent() || editAssignmentDescriptor
                .getAssignmentResult().isPresent()) {
            AssignmentResult updatedResult = editAssignmentDescriptor.getAssignmentResult()
                    .orElseGet(() -> assignmentToEdit.getAssignmentResult().get());
            editedAssignment = editedAssignment.setAssignmentResult(updatedResult);
        }

        return editedAssignment;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditAssignmentCommand)) {
            return false;
        }

        // state check
        EditAssignmentCommand e = (EditAssignmentCommand) other;
        return index.equals(e.index)
                && editAssignmentDescriptor.equals(e.editAssignmentDescriptor);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
