package seedu.address.logic.parser.modulelistparsers;


import seedu.address.logic.commands.modulelistcommands.UndoCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new UndoCommand object
 */
public class UndoParser implements Parser<UndoCommand> {
    /**
     * Returns a UndoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UndoCommand parse(String userInput) throws ParseException {
        return new UndoCommand();
    }
}
