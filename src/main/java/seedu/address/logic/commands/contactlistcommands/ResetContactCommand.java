package seedu.address.logic.commands.contactlistcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;

/**
 * Resets important mark from a contact.
 */
public class ResetContactCommand extends Command {

    public static final String COMMAND_WORD = "resetcontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Reset contact from important to not important.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1 \n";

    public static final String MESSAGE_RESET_CONTACT_SUCCESS = "This contact is not important now : %1$s";

    private final Index targetIndex;

    public ResetContactCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownList = model.getFilteredContactList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        Contact contactToReset = lastShownList.get(targetIndex.getZeroBased());
        Contact resetContact = contactToReset.markAsNotImportant();
        model.setContact(contactToReset, resetContact);
        return new CommandResult(String.format(MESSAGE_RESET_CONTACT_SUCCESS, resetContact));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ResetContactCommand // instanceof handles nulls
            && targetIndex.equals(((ResetContactCommand) other).targetIndex)); // state check
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
