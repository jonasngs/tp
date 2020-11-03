package seedu.address.logic;

import seedu.address.logic.parser.FeatureParser;
import seedu.address.logic.parser.GradeTrackerParser;
import seedu.address.logic.parser.ModuleListParser;
import seedu.address.logic.parser.TodoListParser;
import seedu.address.logic.parser.contactlistparsers.ContactListParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.schedulerparsers.SchedulerParser;

/**
 * Represents the manager in charge of overseeing all the medium feature parsers.
 */
public class ParserManager {
    private final ModuleListParser moduleListParser;
    private final ContactListParser contactListParser;
    private final TodoListParser todoListParser;
    private final GradeTrackerParser gradeTrackerParser;
    private final SchedulerParser schedulerParser;

    /**
     * Creates a Container the holds all the medium parsers.
     * @param moduleListParser parses module related commands.
     * @param todoListParser parses task related commands.
     * @param contactListParser parses contact related commands.
     * @param gradeTrackerParser parses grade tracker related commands.
     */
    public ParserManager(ModuleListParser moduleListParser,
                         TodoListParser todoListParser,
                         ContactListParser contactListParser,
                         GradeTrackerParser gradeTrackerParser,
                         SchedulerParser schedulerParser) {
        this.contactListParser = contactListParser;
        this.todoListParser = todoListParser;
        this.moduleListParser = moduleListParser;
        this.gradeTrackerParser = gradeTrackerParser;
        this.schedulerParser = schedulerParser;
    }

    /**
     * Selects which medium parser to be used based on the command word.
     * @param commandWord the command word of the user input.
     * @return FeatureParser a medium parser.
     * @throws ParseException when the command word is invalid.
     */
    public FeatureParser select(String commandWord) throws ParseException {
        if (containsModuleListCommandWords(commandWord)) {
            return this.moduleListParser;
        } else if (commandWord.contains("task")) {
            return this.todoListParser;
        } else if (commandWord.contains("contact")) {
            return this.contactListParser;
        } else if (commandWord.contains("assignment")) {
            return this.gradeTrackerParser;
        } else if (commandWord.contains("event")) {
            return this.schedulerParser;
        } else {
            throw new ParseException("Does not recognise command type!");
        }
    }

    /**
     * Returns true if command word contains ModuleList related command words
     */
    private boolean containsModuleListCommandWords(String commandWord) {
        return commandWord.contains("module") || commandWord.contains("undo") || commandWord.contains("redo")
                || commandWord.contains("cap") || commandWord.contains("zoom") || commandWord.contains("archive");
    }
}
