package seedu.address.logic.commands.todolistcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;

/**
 * Reset status of a task identified using it's displayed index from the todo list from completed to not completed.
 */
public class ResetTaskCommand extends Command {

    public static final String COMMAND_WORD = "resettask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Change status of a task from COMPLETED to NOT COMPLETED.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1 \n";

    public static final String MESSAGE_RESET_TASK_SUCCESS = "This task is not completed now : \n%1$s";

    private final Index targetIndex;

    public ResetTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTodoList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToReset = lastShownList.get(targetIndex.getZeroBased());
        Task resetTask = taskToReset.setStatus(Status.NOT_COMPLETED);
        model.setTask(taskToReset, resetTask);
        model.commitTodoList();
        return new CommandResult(String.format(MESSAGE_RESET_TASK_SUCCESS, resetTask));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ResetTaskCommand // instanceof handles nulls
            && targetIndex.equals(((ResetTaskCommand) other).targetIndex)); // state check
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
