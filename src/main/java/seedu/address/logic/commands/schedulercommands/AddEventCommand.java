package seedu.address.logic.commands.schedulercommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Represents the AddEventCommand.
 */
public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "addevent";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to the module list. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_DATE + "Date and Time "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "CS2103T peer review "
            + PREFIX_DATE + "12-8-2020";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the event list";
    public static final String MESSAGE_SUCCESS = "New event added: %1$s";

    private final Event event;

    /**
     * Creates an AddEventCommand to be executed.
     *
     * @param event
     */
    public AddEventCommand(Event event) {
        requireNonNull(event);
        this.event = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasEvent(this.event)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }
        model.addEvent(this.event);
        // TODO: Yet to implement commit for scheduler
        //model.commitEventList();
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.event));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
