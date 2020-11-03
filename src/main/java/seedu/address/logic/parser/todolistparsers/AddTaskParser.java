package seedu.address.logic.parser.todolistparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.todolistcommands.AddTaskCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Date;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;

/**
 * Parses input arguments and creates a new AddTaskCommand object
 */
public class AddTaskParser implements Parser<AddTaskCommand> {

    private final Logger logger = LogsCenter.getLogger(AddTaskParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the AddTaskCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddTaskCommand parse(String userInput) throws ParseException {
        assert userInput != null;
        logger.info("User input is: " + userInput);

        Task task;
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(userInput, PREFIX_NAME, PREFIX_TAG, PREFIX_PRIORITY,
            PREFIX_DATE);
        ArgumentMultimap argMultimap = tokenizer.tokenize();
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
            || !argMultimap.getPreamble().isEmpty()) {
            logger.warning("User input does not contain prefix for name which is compulsory for creating task.");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        assert argMultimap.getValue(PREFIX_NAME).isPresent();

        TaskName taskName = ParserUtil.parseTaskName(argMultimap.getValue(PREFIX_NAME).get());
        task = new Task(taskName);

        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            logger.info("Tag field is present");
            Set<Tag> taskTags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            task = task.setTags(taskTags);
        }
        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            logger.info("Priority field is present.");
            Priority priority = ParserUtil.parseTaskPriority(argMultimap.getValue(PREFIX_PRIORITY).get());
            task = task.setPriority(priority);
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            logger.info("Date field is present.");
            Date date = ParserUtil.parseTaskDate(argMultimap.getValue(PREFIX_DATE).get());
            task = task.setDate(date);
        }

        assert task != null;

        return new AddTaskCommand(task);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
