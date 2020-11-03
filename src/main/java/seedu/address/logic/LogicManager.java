package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.FeatureParser;
import seedu.address.logic.parser.GradeTrackerParser;
import seedu.address.logic.parser.ModuleListParser;
import seedu.address.logic.parser.TodoListParser;
import seedu.address.logic.parser.contactlistparsers.ContactListParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.schedulerparsers.SchedulerParser;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.ReadOnlyEventList;
import seedu.address.model.ReadOnlyModuleList;
import seedu.address.model.ReadOnlyTodoList;
import seedu.address.model.contact.Contact;
import seedu.address.model.event.Event;
import seedu.address.model.module.Module;
import seedu.address.model.task.Task;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ModuleListParser moduleListParser;
    private final ContactListParser contactListParser;
    private final TodoListParser todoListParser;
    private final GradeTrackerParser gradeTrackerParser;
    private final ParserManager parserManager;
    private final SchedulerParser schedulerParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        moduleListParser = new ModuleListParser();
        contactListParser = new ContactListParser();
        todoListParser = new TodoListParser();
        gradeTrackerParser = new GradeTrackerParser();
        schedulerParser = new SchedulerParser();
        parserManager = new ParserManager(moduleListParser, todoListParser, contactListParser,
                gradeTrackerParser, schedulerParser);
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        String commandWord = commandText.split(" ")[0];
        FeatureParser parser = parserManager.select(commandWord);
        Command command = parser.parseCommand(commandText);
        commandResult = command.execute(model);
        try {
            storage.saveModuleList(model.getModuleList());
            storage.saveArchivedModuleList(model.getArchivedModuleList());
            storage.saveContactList(model.getContactList());
            storage.saveTodoList(model.getTodoList());
            storage.saveEventList(model.getEventList());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
        return commandResult;
    }

    @Override
    public ReadOnlyModuleList getModuleList() {
        return model.getModuleList();
    }

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return model.getFilteredModuleList();
        //return model.getFilteredModuleList();
    }

    @Override
    public ObservableList<Module> getFilteredModuleListDisplay() {
        if (model.getModuleListDisplay()) {
            return model.getFilteredArchivedModuleList();
        }
        return model.getFilteredModuleList();
    }

    @Override
    public Path getModuleListFilePath() {
        return model.getModuleListFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public ReadOnlyContactList getContactList() {
        return model.getContactList();
    }

    @Override
    public ObservableList<Contact> getFilteredContactList() {
        return model.getFilteredContactList();
    }

    @Override
    public ReadOnlyTodoList getTodoList() {
        return model.getTodoList();
    }

    @Override
    public ObservableList<Task> getFilteredTodoList() {
        return model.getFilteredTodoList();
    }

    @Override
    public Path getContactListFilePath() {
        return model.getModuleListFilePath();
    }

    @Override
    public ReadOnlyEventList getEventList() {
        return model.getEventList();
    }

    @Override
    public ObservableList<Event> getFilteredEventList() {
        return model.getFilteredEventList();
    }

    @Override
    public Path getEventListFilePath() {
        return model.getContactListFilePath();
    }
}
