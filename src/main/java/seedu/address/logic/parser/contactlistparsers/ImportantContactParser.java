package seedu.address.logic.parser.contactlistparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.contactlistcommands.ImportantContactCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class ImportantContactParser implements Parser<ImportantContactCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ImportantContactCommand
     * and returns a ImportantContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ImportantContactCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ImportantContactCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportantContactCommand.MESSAGE_USAGE), pe);
        }
    }
}
