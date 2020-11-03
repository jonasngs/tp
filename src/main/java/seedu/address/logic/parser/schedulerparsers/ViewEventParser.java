package seedu.address.logic.parser.schedulerparsers;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.schedulercommands.ViewEventCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class ViewEventParser implements Parser<ViewEventCommand> {

    @Override
    public ViewEventCommand parse(String userInput) throws ParseException {
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(userInput);
        ArgumentMultimap argMultiMap = tokenizer.tokenize();
        String preamble = argMultiMap.getPreamble();
        Index index = ParserUtil.parseIndex(preamble);
        return new ViewEventCommand(index);
    }
}
