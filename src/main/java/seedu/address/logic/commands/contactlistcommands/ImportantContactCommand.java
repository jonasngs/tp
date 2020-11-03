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
 * Marks a contact identified using it's displayed index from the contact as important.
 */
public class ImportantContactCommand extends Command {

    public static final String COMMAND_WORD = "importantcontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Marks the contact identified by the index number used in the displayed contact list as important.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1 \n"
        + "Note : To mark or undo a contact to not important please use the 'resetcontact' command";

    public static final String MESSAGE_MARK_CONTACT_SUCCESS = "Marked this contact as important: \n%1$s";

    private final Index targetIndex;

    public ImportantContactCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownList = model.getFilteredContactList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        Contact contactToMark = lastShownList.get(targetIndex.getZeroBased());
        Contact markedContact = contactToMark.markAsImportant();
        model.setContact(contactToMark, markedContact);
        return new CommandResult(String.format(MESSAGE_MARK_CONTACT_SUCCESS, markedContact));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ImportantContactCommand // instanceof handles nulls
            && targetIndex.equals(((ImportantContactCommand) other).targetIndex)); // state check
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
