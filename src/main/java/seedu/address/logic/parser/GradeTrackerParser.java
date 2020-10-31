package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.gradetrackercommands.AddAssignmentCommand;
import seedu.address.logic.commands.gradetrackercommands.AddGradeCommand;
import seedu.address.logic.commands.gradetrackercommands.DeleteAssignmentCommand;
import seedu.address.logic.commands.gradetrackercommands.EditAssignmentCommand;
import seedu.address.logic.commands.modulelistcommands.ClearModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.gradetrackerparsers.AddAssignmentParser;
import seedu.address.logic.parser.gradetrackerparsers.AddGradeParser;
import seedu.address.logic.parser.gradetrackerparsers.DeleteAssignmentParser;
import seedu.address.logic.parser.gradetrackerparsers.EditAssignmentParser;

/**
 * Represents the parser in charge for GradeTracker related commands.
 */
public class GradeTrackerParser implements FeatureParser {
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

        case AddAssignmentCommand.COMMAND_WORD:
            return new AddAssignmentParser().parse(arguments);

        case EditAssignmentCommand.COMMAND_WORD:
            return new EditAssignmentParser().parse(arguments);

        case DeleteAssignmentCommand.COMMAND_WORD:
            return new DeleteAssignmentParser().parse(arguments);

        case AddGradeCommand.COMMAND_WORD:
            return new AddGradeParser().parse(arguments);

        case ClearModuleCommand.COMMAND_WORD:
            return new ClearModuleCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
