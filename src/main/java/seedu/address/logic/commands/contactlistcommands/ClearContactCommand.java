package seedu.address.logic.commands.contactlistcommands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.ContactList;
import seedu.address.model.Model;

/**
 * Clears the contact list.
 */
public class ClearContactCommand extends Command {

    public static final String COMMAND_WORD = "clearcontact";
    public static final String MESSAGE_SUCCESS = "Contact list has been cleared!";

    private final Logger logger = LogsCenter.getLogger(ClearContactCommand.class);

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setContactList(new ContactList());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
