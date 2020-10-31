package seedu.address.logic.commands.todolistcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.TodoList;

/**
 * Clears the todo list.
 */
public class ClearTaskCommand extends Command {

    public static final String COMMAND_WORD = "cleartask";
    public static final String MESSAGE_SUCCESS = "Todo list has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTodoList(new TodoList());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
