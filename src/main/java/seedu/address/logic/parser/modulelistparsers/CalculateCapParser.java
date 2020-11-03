package seedu.address.logic.parser.modulelistparsers;

import java.util.stream.Stream;

import seedu.address.logic.commands.modulelistcommands.CalculateCapCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;



/**
 * Parses input arguments and creates a new CalculateCapCommand object
 */
public class CalculateCapParser implements Parser<CalculateCapCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the CalculateCapCommand
     * and returns an CalculateCapCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CalculateCapCommand parse(String args) throws ParseException {
        return new CalculateCapCommand();
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
