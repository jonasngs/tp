package seedu.address.logic.parser.contactlistparsers;

import java.util.Comparator;

import seedu.address.logic.commands.contactlistcommands.SortContactCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactComparatorByName;

/**
 * Parses input arguments and creates a new SortContactCommand object
 */
public class SortContactParser implements Parser<SortContactCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SortContactCommand
     * and returns an SortContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortContactCommand parse(String args) {
        Comparator<Contact> comparator = new ContactComparatorByName();
        if (args.trim().equals("r")) {
            comparator = comparator.reversed();
        }
        return new SortContactCommand(comparator);
    }
}
