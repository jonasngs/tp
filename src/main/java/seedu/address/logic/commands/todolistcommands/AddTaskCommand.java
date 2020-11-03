package seedu.address.logic.commands.todolistcommands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Adds a task to the todo list.
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "addtask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the todo list. \n"
        + "Parameters: "
        + PREFIX_NAME + "TASK NAME "
        + PREFIX_TAG + "TAG [OPTIONAL] "
        + PREFIX_PRIORITY + "PRIORITY [OPTIONAL] "
        + PREFIX_DATE + "DATE/DEADLINE [OPTIONAL] \n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NAME + "Do Lab Sheet 1 "
        + PREFIX_TAG + "LAB "
        + PREFIX_PRIORITY + "HIGH "
        + PREFIX_DATE + "2020-10-16 ";

    public static final String MESSAGE_SUCCESS = "New task added: \n%1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the todo list";

    private final Logger logger = LogsCenter.getLogger(AddTaskCommand.class);

    private final Task toAdd;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public AddTaskCommand(Task task) {
        assert task != null;
        logger.info("Adding a task: \n" + task.toString());
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert model != null;

        if (model.hasTask(toAdd)) {
            logger.warning("Detected duplicate task. Task will not be added to the list.");
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        assert toAdd != null;

        logger.info("Adding task to the list.");
        model.addTask(toAdd);
        model.commitTodoList();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddTaskCommand // instanceof handles nulls
            && toAdd.equals(((AddTaskCommand) other).toAdd));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

