package seedu.address.logic.commands.gradetrackercommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_PERCENTAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_RESULT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.grade.Assignment;

public class AddAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "addassignment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an assignment to the grade tracker for a module. "
            + "Parameters: "
            + PREFIX_NAME + "MODULE NAME "
            + PREFIX_ASSIGNMENT_NAME + "ASSIGNMENT NAME "
            + PREFIX_ASSIGNMENT_PERCENTAGE + " PERCENTAGE OF FINAL GRADE "
            + PREFIX_ASSIGNMENT_RESULT + " RESULT ACHIEVED "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "CS2100 "
            + PREFIX_ASSIGNMENT_NAME + "Quiz 1"
            + PREFIX_ASSIGNMENT_PERCENTAGE + " 15"
            + PREFIX_ASSIGNMENT_RESULT + " 0.85";

    public static final String MESSAGE_SUCCESS = "New assignment %1$s added.";
    public static final String MESSAGE_ASSIGNMENT_NOT_ADDED = "Module to add to not found.";

    private final Logger logger = LogsCenter.getLogger(AddAssignmentCommand.class);

    private final ModuleName moduleToAdd;
    private final Assignment assignmentToAdd;

    /**
     * Creates an AddAssignmentCommand to add the specified {@code Assignment}
     */
    public AddAssignmentCommand(ModuleName moduleToAdd, Assignment assignment) {
        requireNonNull(assignment);
        logger.info("Adding an assignment: " + assignment.toString());
        this.moduleToAdd = moduleToAdd;
        this.assignmentToAdd = assignment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Module module = null;
        List<Module> lastShownList = model.getFilteredModuleList();
        for (Module eachModule : lastShownList) {
            if (eachModule.getName().equals(moduleToAdd)) {
                module = eachModule;
                break;
            }
        }
        if (module == null) {
            throw new CommandException(MESSAGE_ASSIGNMENT_NOT_ADDED);
        }
        module.addAssignment(assignmentToAdd);
        logger.info("Assignment has been added: " + assignmentToAdd.toString());
        model.commitModuleList();
        return new CommandResult(String.format(MESSAGE_SUCCESS, assignmentToAdd));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
