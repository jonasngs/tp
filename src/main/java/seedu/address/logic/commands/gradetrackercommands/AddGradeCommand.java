package seedu.address.logic.commands.gradetrackercommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
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
import seedu.address.model.module.grade.Grade;

public class AddGradeCommand extends Command {

    public static final String COMMAND_WORD = "addgrade";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a grade to the grade tracker for a module. "
            + "Parameters: "
            + PREFIX_NAME + "MODULE NAME "
            + PREFIX_GRADE + " GRADE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "CS2100 "
            + PREFIX_GRADE + "0.90";

    public static final String MESSAGE_SUCCESS = "New grade %1$s added.";
    public static final String MESSAGE_GRADE_NOT_ADDED = "Module to add to not found.";

    private final Logger logger = LogsCenter.getLogger(AddGradeCommand.class);

    private final ModuleName moduleToAdd;
    private final Grade gradeToAdd;

    /**
     * Creates an AddAssignmentCommand to add the specified {@code Grade}
     */
    public AddGradeCommand(ModuleName moduleToAdd, Grade grade) {
        requireNonNull(grade);
        logger.info("Adding a grade: " + grade.toString());
        this.moduleToAdd = moduleToAdd;
        this.gradeToAdd = grade;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Module module = null;
        List<Module> lastShownList = model.getFilteredModuleList();
        for (Module eachModule : lastShownList) {
            if (eachModule.getName().fullName.equals(moduleToAdd)) {
                module = eachModule;
                break;
            }
        }
        if (module == null) {
            throw new CommandException(MESSAGE_GRADE_NOT_ADDED);
        }
        module.addGrade(gradeToAdd);
        logger.info("Grade has been added: " + gradeToAdd.toString());
        model.commitModuleList();
        return new CommandResult(String.format(MESSAGE_SUCCESS, gradeToAdd));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
