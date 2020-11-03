package seedu.address.logic.commands.contactlistcommands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;

public class SortContactCommand extends Command {

    public static final String COMMAND_WORD = "sortcontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort the contact list based on the contact name "
            + "lexicographically \n"
            + "Parameter: [REVERSE] \n"
            + "To reverse the ordering, you can add 'r' before the criterion. \n"
            + "Example: " + COMMAND_WORD
            + "Example (reversed): " + COMMAND_WORD + " r";

    public static final String MESSAGE_SUCCESS = "List has been sorted!";

    private final Comparator<Contact> comparator;

    /**
     * Creates SortContactCommand to sort the todo list based on {@code Comparator}.
     */
    public SortContactCommand(Comparator<Contact> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateSortedContactList(comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortContactCommand)) {
            return false;
        }

        // state check
        SortContactCommand s = (SortContactCommand) other;

        return this.comparator.equals(s.comparator);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
