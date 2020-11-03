package seedu.address.logic.parser.gradetrackerparsers;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.NoSuchElementException;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.gradetrackercommands.DeleteAssignmentCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleName;

public class DeleteAssignmentParser implements Parser<DeleteAssignmentCommand> {

    @Override
    public DeleteAssignmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ModuleName moduleName;
        Index index;

        ArgumentTokenizer tokenizer = new ArgumentTokenizer(args, PREFIX_NAME);
        ArgumentMultimap argMultimap = tokenizer.tokenize();
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteAssignmentCommand.MESSAGE_USAGE), pe);
        }
        if (argMultimap.getValue(PREFIX_NAME).isEmpty()) {
            throw new NoSuchElementException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteAssignmentCommand.MESSAGE_USAGE));
        }
        moduleName = ParserUtil.parseModuleName(argMultimap.getValue(PREFIX_NAME).get());
        return new DeleteAssignmentCommand(index, moduleName);
    }
}
