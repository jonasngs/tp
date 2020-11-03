package seedu.address.logic.parser.modulelistparsers;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ZOOM_LINK;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.modulelistcommands.AddZoomDescriptor;
import seedu.address.logic.commands.modulelistcommands.AddZoomLinkCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleLesson;
import seedu.address.model.module.ZoomLink;

/**
 * Parses the input arguments and creates a new AddZoomLinkCommand object.
 */
public class AddZoomLinkParser implements Parser<AddZoomLinkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddZoomLinkCommand
     * and returns an AddZoomLinkCommand object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format.
     */
    public AddZoomLinkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(args, PREFIX_NAME, PREFIX_ZOOM_LINK);
        ArgumentMultimap argMultimap = tokenizer.tokenize();

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ZOOM_LINK)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddZoomLinkCommand.MESSAGE_USAGE));
        }

        Index moduleIndex;
        try {
            moduleIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException ex) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddZoomLinkCommand.MESSAGE_USAGE), ex);
        }
        AddZoomDescriptor descriptor = new AddZoomDescriptor();
        ZoomLink zoomLink = ParserUtil.parseZoomLink(argMultimap.getValue(PREFIX_ZOOM_LINK).get());;
        ModuleLesson lesson = ParserUtil.parseModuleLesson(argMultimap.getValue(PREFIX_NAME).get());;

        descriptor.setLink(zoomLink);
        descriptor.setModuleLesson(lesson);

        return new AddZoomLinkCommand(moduleIndex, descriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given.
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
