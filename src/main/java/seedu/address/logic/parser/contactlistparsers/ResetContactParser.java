package seedu.address.logic.parser.contactlistparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.contactlistcommands.ResetContactCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ResetContactCommand object.
 */
public class ResetContactParser implements Parser<ResetContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ResetContactCommand
     * and returns a ResetContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ResetContactCommand parse(String userInput) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(userInput);
            return new ResetContactCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResetContactCommand.MESSAGE_USAGE), pe);
        }
    }
}
