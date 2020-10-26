package seedu.address.logic.commands.contact;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Finds the specific contact in the contact list.
 */
public class FindContactCommand extends Command {
    public static final String COMMAND_WORD = "findcontact";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
