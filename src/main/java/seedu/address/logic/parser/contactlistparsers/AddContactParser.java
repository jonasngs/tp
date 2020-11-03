package seedu.address.logic.parser.contactlistparsers;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.contactlistcommands.AddContactCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactName;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Telegram;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddContactCommand object.
 */
public class AddContactParser implements Parser<AddContactCommand> {

    private final Logger logger = LogsCenter.getLogger(AddContactParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the AddContactCommand
     * and returns an AddContactCommand object for execution.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public AddContactCommand parse(String args) throws ParseException {
        requireNonNull(args);
        logger.info("The user input is: " + args);

        ArgumentTokenizer tokenizer =
                new ArgumentTokenizer(args, PREFIX_NAME, PREFIX_EMAIL, PREFIX_TELEGRAM, PREFIX_TAG);
        ArgumentMultimap argMultimap = tokenizer.tokenize();

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddContactCommand.MESSAGE_USAGE));
        }

        assert argMultimap.getValue(PREFIX_NAME).isPresent() : "Argument for PREFIX_NAME must be present";
        assert argMultimap.getValue(PREFIX_EMAIL).isPresent() : "Argument for PREFIX_EMAIL must be present";

        Contact contact;
        ContactName name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        boolean isContactImportant = false;

        if (argMultimap.getValue(PREFIX_TELEGRAM).isPresent()) {
            Telegram telegram = ParserUtil.parseTelegram(argMultimap.getValue(PREFIX_TELEGRAM).get());
            contact = new Contact(name, email, telegram, tagList, isContactImportant);
        } else {
            contact = new Contact(name, email, tagList, isContactImportant);
        }

        requireNonNull(contact);
        return new AddContactCommand(contact);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

