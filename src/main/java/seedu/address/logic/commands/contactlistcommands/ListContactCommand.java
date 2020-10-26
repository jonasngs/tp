package seedu.address.logic.commands.contactlistcommands;


import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import java.util.logging.Logger;

/**
 * Lists all the existing contacts in the contact list to the user.
 */
public class ListContactCommand extends Command {

    public static final String COMMAND_WORD = "listcontact";

    public static final String MESSAGE_SUCCESS = "Listed all contacts";

    private final Logger logger = LogsCenter.getLogger(ListContactCommand.class);

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        logger.info("Display all contacts in contact list");
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
