package seedu.address.logic.commands.contactlistcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;

/**
 * Adds a contact into the contact list.
 */
public class AddContactCommand extends Command {

    public static final String COMMAND_WORD = "addcontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a contact to the contact list. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_TELEGRAM + "TELEGRAM "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_TELEGRAM + "@johndoe"
            + PREFIX_TAG + "friend";

    public static final String MESSAGE_SUCCESS = "New contact added: %1$s";
    public static final String MESSAGE_DUPLICATE_CONTACT = "This contact already exists in the contact list";

    private final Logger logger = LogsCenter.getLogger(AddContactCommand.class);

    private final Contact toAdd;

    /**
     * Creates and initialises a new AddContactCommand for the addition of a new contact.
     *
     * @param contact Contact to be added.
     */
    public AddContactCommand(Contact contact) {
        requireNonNull(contact);
        logger.info("Adding a contact: \n" + contact.toString());
        toAdd = contact;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasContact(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONTACT);
        }
        model.addContact(toAdd);
        model.commitContactList();
        logger.info("Contact has been added: \n" + toAdd.toString());
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddContactCommand // instanceof handles nulls
                && toAdd.equals(((AddContactCommand) other).toAdd));
    }

    @Override
    public boolean isExit() {
        return false;
    }

}

