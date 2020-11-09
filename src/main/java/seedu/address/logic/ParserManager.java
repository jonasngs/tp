package seedu.address.logic;

import seedu.address.logic.parser.ContactListParser;
import seedu.address.logic.parser.FeatureParser;
import seedu.address.logic.parser.GradeTrackerParser;
import seedu.address.logic.parser.ModuleListParser;
import seedu.address.logic.parser.SchedulerParser;
import seedu.address.logic.parser.TodoListParser;
import seedu.address.logic.parser.exceptions.ParseException;


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
     */
    public ParserManager() {
        moduleListParser = new ModuleListParser();
        contactListParser = new ContactListParser();
        todoListParser = new TodoListParser();
        gradeTrackerParser = new GradeTrackerParser();
        schedulerParser = new SchedulerParser();
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
        } else if (commandWord.contains("assignment") || commandWord.contains("grade") ) {
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
                || commandWord.contains("cap") || commandWord.contains("zoom") || commandWord.contains("archive")
                || commandWord.contains("exit") || commandWord.contains("help");
    }
}
