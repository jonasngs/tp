package seedu.address.logic.parser.modulelistparsers;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.modulelistcommands.DeleteZoomLinkCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleLesson;

/**
 * Parses input arguments and creates a new DeleteZoomLinkCommand object
 */
public class DeleteZoomLinkParser implements Parser<DeleteZoomLinkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteZoomLinkCommand
     * and returns a DeleteZoomLinkCommand object for execution.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public DeleteZoomLinkCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentTokenizer tokenizer =
                new ArgumentTokenizer(args, PREFIX_NAME);
        ArgumentMultimap argMultimap = tokenizer.tokenize();

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteZoomLinkCommand.MESSAGE_USAGE));
        }

        assert argMultimap.getValue(PREFIX_NAME).isPresent() : "Argument for PREFIX_NAME must be present";
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteZoomLinkCommand.MESSAGE_USAGE), pe);

        }
        ModuleLesson lesson = ParserUtil.parseModuleLesson(argMultimap.getValue(PREFIX_NAME).get());
        return new DeleteZoomLinkCommand(index, lesson);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
