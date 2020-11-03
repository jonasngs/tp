package seedu.address.logic.parser.modulelistparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE_POINT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULAR_CREDITS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.modulelistcommands.AddCompletedModuleCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModularCredits;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.grade.GradePoint;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCompletedModuleCommand object
 */
public class AddCompletedModuleParser implements Parser<AddCompletedModuleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddCompletedModuleCommand
     * and returns an AddCompletedModuleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCompletedModuleCommand parse(String args) throws ParseException {
        Module module;
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(args, PREFIX_NAME, PREFIX_TAG,
                PREFIX_MODULAR_CREDITS, PREFIX_GRADE_POINT);
        ArgumentMultimap argMultimap = tokenizer.tokenize();
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCompletedModuleCommand.MESSAGE_USAGE));
        }
        ModuleName moduleName = ParserUtil.parseModuleName(argMultimap.getValue(PREFIX_NAME).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        if (hasCompletedTag(tagList)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCompletedModuleCommand.MESSAGE_USAGE));
        }
        tagList.add(new Tag("completed"));
        if (!arePrefixesPresent(argMultimap, PREFIX_MODULAR_CREDITS)
                && argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCompletedModuleCommand.MESSAGE_USAGE));
        }
        ModularCredits modularCredits = ParserUtil.parseModularCredits(argMultimap
                .getValue(PREFIX_MODULAR_CREDITS).get());
        if (!arePrefixesPresent(argMultimap, PREFIX_GRADE_POINT)
                && argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCompletedModuleCommand.MESSAGE_USAGE));
        }
        GradePoint gradePoint = ParserUtil.parseGradePoint(argMultimap.getValue(PREFIX_GRADE_POINT).get());
        module = new Module(moduleName, tagList, modularCredits, gradePoint);
        return new AddCompletedModuleCommand(module);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private boolean hasCompletedTag(Set<Tag> tags) {
        for (Tag t : tags) {
            if (t.equals(new Tag("completed"))) {
                return true;
            }
        }
        return false;
    }
}
