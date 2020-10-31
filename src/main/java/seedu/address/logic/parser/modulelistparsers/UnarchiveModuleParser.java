package seedu.address.logic.parser.modulelistparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.modulelistcommands.UnarchiveModuleCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class UnarchiveModuleParser implements Parser<UnarchiveModuleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UnarchiveModuleCommand
     * and returns a UnarchiveModuleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnarchiveModuleCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UnarchiveModuleCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnarchiveModuleCommand.MESSAGE_USAGE), pe);
        }
    }
}
