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

public class DeleteEventCommand extends Command {

    public static final String COMMAND_WORD = "deleteevent";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the Event identified by the index number used in the displayed Event list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_DELETE_EVENT_SUCCESS = "Deleted event: %1$s";
    private final Index index;
    public DeleteEventCommand(Index index) {
        this.index = index;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> filteredList = model.getFilteredEventList();
        if (this.index.getZeroBased() >= filteredList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }
        Event event = filteredList.get(this.index.getZeroBased());
        model.deleteEvent(event);
        //model.commitEventList();
        return new CommandResult(String.format(MESSAGE_DELETE_EVENT_SUCCESS, event));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
