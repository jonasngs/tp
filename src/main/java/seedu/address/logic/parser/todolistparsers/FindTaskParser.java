package seedu.address.logic.parser.todolistparsers;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.todolistcommands.FindTaskCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Date;
import seedu.address.model.task.FindTaskCriteria;
import seedu.address.model.task.NameContainsKeywordsPredicate;
import seedu.address.model.task.Priority;
import seedu.address.model.task.TaskContainsTagsPredicate;
import seedu.address.model.task.TaskMatchesDatePredicate;
import seedu.address.model.task.TaskMatchesPriorityPredicate;

/**
 * Parses input arguments and creates a new FindTaskCommand object.
 */
public class FindTaskParser implements Parser<FindTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTaskCommand
     * and returns a FindTaskCommand object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format.
     */
    public FindTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(args,
                PREFIX_NAME, PREFIX_DATE, PREFIX_PRIORITY, PREFIX_TAG);
        ArgumentMultimap argMultiMap = tokenizer.tokenize();

        if (!isAtLeastOnePrefixPresent(argMultiMap, PREFIX_NAME, PREFIX_DATE, PREFIX_PRIORITY, PREFIX_TAG)
                || !argMultiMap.getPreamble().isBlank()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
        }

        FindTaskCriteria findTaskCriteria = new FindTaskCriteria();

        if (argMultiMap.getValue(PREFIX_NAME).isPresent()) {
            List<String> nameKeywords = ParserUtil.parseSearchKeywords(argMultiMap.getValue(PREFIX_NAME).get());
            NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(nameKeywords);
            findTaskCriteria.addPredicate(namePredicate);
        }

        if (argMultiMap.getValue(PREFIX_DATE).isPresent()) {
            Date searchDate = ParserUtil.parseTaskDate(argMultiMap.getValue(PREFIX_DATE).get());
            TaskMatchesDatePredicate datePredicate = new TaskMatchesDatePredicate(searchDate);
            findTaskCriteria.addPredicate(datePredicate);
        }

        if (argMultiMap.getValue(PREFIX_PRIORITY).isPresent()) {
            Priority searchPriority = ParserUtil.parseTaskPriority(argMultiMap.getValue(PREFIX_PRIORITY).get());
            TaskMatchesPriorityPredicate priorityPredicate = new TaskMatchesPriorityPredicate(searchPriority);
            findTaskCriteria.addPredicate(priorityPredicate);
        }

        if (argMultiMap.getValue(PREFIX_TAG).isPresent()) {
            List<String> tags = ParserUtil.parseSearchKeywords(argMultiMap.getValue(PREFIX_TAG).get());
            Set<Tag> searchTags = ParserUtil.parseTags(tags);
            TaskContainsTagsPredicate tagsPredicate = new TaskContainsTagsPredicate(searchTags);
            findTaskCriteria.addPredicate(tagsPredicate);
        }

        return new FindTaskCommand(findTaskCriteria);
    }

    /**
     * Determines if at least one of the prefixes does not contain an empty {@code Optional} value in the given
     * {@code ArgumentMultimap}.
     *
     * @param argumentMultimap ArgumentMultimap object containing the mapping of Prefixes to their respective arguments.
     * @param prefixes Prefixes to check for in the ArgumentMultimap.
     * @return True if at least one prefix has an argument mapped to it, false otherwise.
     */
    private static boolean isAtLeastOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
