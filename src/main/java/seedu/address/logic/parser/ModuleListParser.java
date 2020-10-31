package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.contactlistcommands.AddContactCommand;
import seedu.address.logic.commands.contactlistcommands.DeleteContactCommand;
import seedu.address.logic.commands.contactlistcommands.EditContactCommand;
import seedu.address.logic.commands.contactlistcommands.FindContactCommand;
import seedu.address.logic.commands.gradetrackercommands.AddGradeCommand;
import seedu.address.logic.commands.modulelistcommands.AddCompletedModuleCommand;
import seedu.address.logic.commands.modulelistcommands.AddModuleCommand;
import seedu.address.logic.commands.modulelistcommands.AddZoomLinkCommand;
import seedu.address.logic.commands.modulelistcommands.ArchiveModuleCommand;
import seedu.address.logic.commands.modulelistcommands.CalculateCapCommand;
import seedu.address.logic.commands.modulelistcommands.ClearModuleCommand;
import seedu.address.logic.commands.modulelistcommands.DeleteModuleCommand;
import seedu.address.logic.commands.modulelistcommands.EditModuleCommand;
import seedu.address.logic.commands.modulelistcommands.ListModuleCommand;
import seedu.address.logic.commands.modulelistcommands.RedoCommand;
import seedu.address.logic.commands.modulelistcommands.TargetCapCalculatorCommand;
import seedu.address.logic.commands.modulelistcommands.UnarchiveModuleCommand;
import seedu.address.logic.commands.modulelistcommands.UndoCommand;
import seedu.address.logic.commands.modulelistcommands.ViewArchivedModulesCommand;
import seedu.address.logic.commands.modulelistcommands.ViewModuleCommand;
import seedu.address.logic.parser.contactlistparsers.AddContactParser;
import seedu.address.logic.parser.contactlistparsers.DeleteContactParser;
import seedu.address.logic.parser.contactlistparsers.EditContactParser;
import seedu.address.logic.parser.contactlistparsers.FindContactParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.gradetrackerparsers.AddGradeParser;
import seedu.address.logic.parser.modulelistparsers.AddCompletedModuleParser;
import seedu.address.logic.parser.modulelistparsers.AddModuleParser;
import seedu.address.logic.parser.modulelistparsers.AddZoomLinkParser;
import seedu.address.logic.parser.modulelistparsers.ArchiveModuleParser;
import seedu.address.logic.parser.modulelistparsers.CalculateCapParser;
import seedu.address.logic.parser.modulelistparsers.DeleteModuleParser;
import seedu.address.logic.parser.modulelistparsers.EditModuleParser;
import seedu.address.logic.parser.modulelistparsers.RedoParser;
import seedu.address.logic.parser.modulelistparsers.TargetCapCalculatorParser;
import seedu.address.logic.parser.modulelistparsers.UnarchiveModuleParser;
import seedu.address.logic.parser.modulelistparsers.UndoParser;
import seedu.address.logic.parser.modulelistparsers.ViewModuleParser;

/**
 * Parses user input.
 */
public class ModuleListParser implements FeatureParser {

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

        case AddModuleCommand.COMMAND_WORD:
            return new AddModuleParser().parse(arguments);

        case EditModuleCommand.COMMAND_WORD:
            return new EditModuleParser().parse(arguments);

        case DeleteModuleCommand.COMMAND_WORD:
            return new DeleteModuleParser().parse(arguments);

        case ClearModuleCommand.COMMAND_WORD:
            return new ClearModuleCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListModuleCommand.COMMAND_WORD:
            return new ListModuleCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddZoomLinkCommand.COMMAND_WORD:
            return new AddZoomLinkParser().parse(arguments);

        case ViewModuleCommand.COMMAND_WORD:
            return new ViewModuleParser().parse(arguments);

        case FindContactCommand.COMMAND_WORD:
            return new FindContactParser().parse(arguments);

        case DeleteContactCommand.COMMAND_WORD:
            return new DeleteContactParser().parse(arguments);

        case EditContactCommand.COMMAND_WORD:
            return new EditContactParser().parse(arguments);

        case AddContactCommand.COMMAND_WORD:
            return new AddContactParser().parse(arguments);

        case AddGradeCommand.COMMAND_WORD:
            return new AddGradeParser().parse(arguments);

        case AddCompletedModuleCommand.COMMAND_WORD:
            return new AddCompletedModuleParser().parse(arguments);

        case CalculateCapCommand.COMMAND_WORD:
            return new CalculateCapParser().parse(arguments);

        case TargetCapCalculatorCommand.COMMAND_WORD:
            return new TargetCapCalculatorParser().parse(arguments);
        //case EditGradeCommand.COMMAND_WORD:
        //            return new EditGradeParser().parse(arguments);
        case UndoCommand.COMMAND_WORD:
            return new UndoParser().parse(arguments);

        case RedoCommand.COMMAND_WORD:
            return new RedoParser().parse(arguments);
        case ArchiveModuleCommand.COMMAND_WORD:
            return new ArchiveModuleParser().parse(arguments);
        case ViewArchivedModulesCommand.COMMAND_WORD:
            return new ViewArchivedModulesCommand();
        case UnarchiveModuleCommand.COMMAND_WORD:
            return new UnarchiveModuleParser().parse(arguments);

        //case EditGradeCommand.COMMAND_WORD:
        //            return new EditGradeParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
