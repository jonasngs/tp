package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.schedulercommands.AddEventCommand;
import seedu.address.logic.commands.schedulercommands.DeleteEventCommand;
import seedu.address.logic.commands.schedulercommands.EditEventCommand;
import seedu.address.logic.commands.schedulercommands.FindEventCommand;
import seedu.address.logic.commands.schedulercommands.ListEventCommand;
import seedu.address.logic.commands.schedulercommands.ViewEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.schedulerparsers.AddEventParser;
import seedu.address.logic.parser.schedulerparsers.DeleteEventParser;
import seedu.address.logic.parser.schedulerparsers.EditEventParser;
import seedu.address.logic.parser.schedulerparsers.FindEventParser;
import seedu.address.logic.parser.schedulerparsers.ViewEventParser;

/**
 * Represents the parser in charge for Scheduler related commands.
 */
public class SchedulerParser implements FeatureParser {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddEventCommand.COMMAND_WORD:
            return new AddEventParser().parse(arguments);

        case EditEventCommand.COMMAND_WORD:
            return new EditEventParser().parse(arguments);

        case DeleteEventCommand.COMMAND_WORD:
            return new DeleteEventParser().parse(arguments);

        case ViewEventCommand.COMMAND_WORD:
            return new ViewEventParser().parse(arguments);

        case FindEventCommand.COMMAND_WORD:
            return new FindEventParser().parse(arguments);

        case ListEventCommand.COMMAND_WORD:
            return singleWordCommandsChecker(ListEventCommand.COMMAND_WORD, arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private Command singleWordCommandsChecker(String commandWord, String argument) throws ParseException {
        if (!argument.equals("")) {
            throw new ParseException("Invalid input format, extra string after the command word.");
        }

        switch (commandWord) {

        case ListEventCommand.COMMAND_WORD:
            return new ListEventCommand();

        default:
            throw new ParseException("Invalid command");
        }
    }
}
