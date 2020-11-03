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
 * Marks a task identified using it's displayed index from the todo list as completed.
 */
public class CompleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "completetask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Mark the task identified by the index number used in the displayed todo list as COMPLETED.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1 \n"
        + "Note : To mark or undo a task to NOT COMPLETED please use the 'resettask' command";

    public static final String MESSAGE_COMPLETE_TASK_SUCCESS = "Completed Task: \n%1$s";

    private final Index targetIndex;

    public CompleteTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTodoList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToComplete = lastShownList.get(targetIndex.getZeroBased());
        Task completedTask = taskToComplete.setStatus(Status.COMPLETED);
        model.setTask(taskToComplete, completedTask);
        model.commitTodoList();
        return new CommandResult(String.format(MESSAGE_COMPLETE_TASK_SUCCESS, completedTask));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof CompleteTaskCommand // instanceof handles nulls
            && targetIndex.equals(((CompleteTaskCommand) other).targetIndex)); // state check
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
