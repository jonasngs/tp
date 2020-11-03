package seedu.address.logic.commands.contactlistcommands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.FindContactCriteria;

/**
 * Finds and lists all contacts in the contact list whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindContactCommand extends Command {

    public static final String COMMAND_WORD = "findcontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final Logger logger = LogsCenter.getLogger(FindContactCommand.class);

    private final Predicate<Contact> predicate;

    /**
     * Creates and initialises a FindContactCommand object.
     *
     * @param findContactCriteria FindContactCriteria that encapsulates a list of predicates to test a contact with.
     */
    public FindContactCommand(FindContactCriteria findContactCriteria) {
        requireNonNull(findContactCriteria);
        logger.info("The find contact command is being executed");
        this.predicate = findContactCriteria.getFindContactPredicate();
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredContactList(predicate);
        logger.info("The result of executing the find contact command has been displayed");
        return new CommandResult(
                String.format(Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW, model.getFilteredContactList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindContactCommand // instanceof handles nulls
                && predicate.equals(((FindContactCommand) other).predicate)); // state check
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
