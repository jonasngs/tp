package seedu.address.logic.commands.todolistcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Date;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;

/**
 * Edits the details of an existing task in the todo list.
 */
public class EditTaskCommand extends Command {

    public static final String COMMAND_WORD = "edittask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
        + "by the index number used in the displayed todo list. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_NAME + "NAME] "
        + "[" + PREFIX_TAG + "TAG (can be more than 1)] "
        + "[" + PREFIX_DATE + "DATE/DEADLINE] "
        + "[" + PREFIX_PRIORITY + "PRIORITY]...\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_NAME + "read chapter 7\n"
        + "NOTE : 1. To update the status of the task you can use the complete command.\n"
        + "NOTE : 2. If tags are present, it will overwrite all of previous tags (editing tags is not cumulative)";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited task: \n%1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the todo list.";

    private final Index index;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * @param index of the task in the filtered todo list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public EditTaskCommand(Index index, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(index);
        requireNonNull(editTaskDescriptor);

        this.index = index;
        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTodoList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

        if (!taskToEdit.isSameTask(editedTask) && model.hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTodoList(PREDICATE_SHOW_ALL_TASKS);
        model.commitTodoList();
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    private static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;
        assert taskToEdit.getName().isPresent();

        TaskName name = editTaskDescriptor.getName().orElse(taskToEdit.getName().get());

        Task editedTask = new Task(name);

        if (taskToEdit.getTags().isPresent() || editTaskDescriptor.getTags().isPresent()) {
            Set<Tag> updatedTag = editTaskDescriptor.getTags()
                    .orElseGet(() -> taskToEdit.getTags().get());
            editedTask = editedTask.setTags(updatedTag);
        }

        if (taskToEdit.getPriority().isPresent() || editTaskDescriptor.getPriority().isPresent()) {
            Priority updatedPriority = editTaskDescriptor.getPriority()
                    .orElseGet(() -> taskToEdit.getPriority().get());
            editedTask = editedTask.setPriority(updatedPriority);
        }

        if (taskToEdit.getDate().isPresent() || editTaskDescriptor.getDate().isPresent()) {
            Date updatedDate = editTaskDescriptor.getDate()
                    .orElseGet(() -> taskToEdit.getDate().get());
            editedTask = editedTask.setDate(updatedDate);
        }

        if (taskToEdit.getStatus().isPresent()) {
            editedTask = editedTask.setStatus(taskToEdit.getStatus().get());
        }

        return editedTask;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTaskCommand)) {
            return false;
        }

        // state check
        EditTaskCommand e = (EditTaskCommand) other;
        return index.equals(e.index)
            && editTaskDescriptor.equals(e.editTaskDescriptor);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
