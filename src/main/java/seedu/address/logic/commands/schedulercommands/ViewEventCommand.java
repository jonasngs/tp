package seedu.address.logic.commands.schedulercommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

public class ViewEventCommand extends Command {

    public static final String COMMAND_WORD = "viewevent";
    public static final String MESSAGE_SUCCESS = "Event details have been displayed successfully!\n"
            + "%1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views a event in the module list. "
            + "Parameters: " + "Index of event"
            + "Example: " + COMMAND_WORD + "1";

    public static final String MESSAGE_MODULE_NOT_FOUND = "The event requested cannot be found.";

    public final Index index;

    public ViewEventCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredEventList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        Event toView = lastShownList.get(this.index.getZeroBased());
        return new CommandResult(String.format(MESSAGE_SUCCESS, toView));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
