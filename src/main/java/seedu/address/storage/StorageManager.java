package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.ReadOnlyEventList;
import seedu.address.model.ReadOnlyModuleList;
import seedu.address.model.ReadOnlyTodoList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ModuleListStorage moduleListStorage;
    private ModuleListStorage archivedModuleListStorage;
    private ContactListStorage contactListStorage;
    private TodoListStorage todoListStorage;
    private UserPrefsStorage userPrefsStorage;
    private EventListStorage eventListStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ModuleListStorage},
     * {@code ContactListStorage} and {@code UserPrefStorage}.
     */

    public StorageManager(ModuleListStorage moduleListStorage, ModuleListStorage archivedModuleListStorage,
                          ContactListStorage contactListStorage, TodoListStorage todoListStorage,
                          EventListStorage eventListStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.moduleListStorage = moduleListStorage;
        this.archivedModuleListStorage = archivedModuleListStorage;
        this.contactListStorage = contactListStorage;
        this.todoListStorage = todoListStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.eventListStorage = eventListStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ ModuleList methods ==============================

    @Override
    public Path getModuleListFilePath() {
        return moduleListStorage.getModuleListFilePath();
    }

    @Override
    public Optional<ReadOnlyModuleList> readModuleList() throws DataConversionException, IOException {
        return readModuleList(moduleListStorage.getModuleListFilePath());
    }

    @Override
    public Optional<ReadOnlyModuleList> readModuleList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return moduleListStorage.readModuleList(filePath);
    }

    @Override
    public void saveModuleList(ReadOnlyModuleList moduleList) throws IOException {
        saveModuleList(moduleList, moduleListStorage.getModuleListFilePath());
    }

    @Override
    public void saveModuleList(ReadOnlyModuleList moduleList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        moduleListStorage.saveModuleList(moduleList, filePath);
    }
    public Path getArchivedModuleListFilePath() {
        return archivedModuleListStorage.getModuleListFilePath();
    }
    @Override
    public Optional<ReadOnlyModuleList> readArchivedModuleList() throws DataConversionException, IOException {
        return readArchivedModuleList(archivedModuleListStorage.getModuleListFilePath());
    }
    @Override
    public Optional<ReadOnlyModuleList> readArchivedModuleList(Path filePath) throws DataConversionException,
            IOException {
        logger.fine("Attempting to read archived modulelist data from file: " + filePath);
        return archivedModuleListStorage.readModuleList(filePath);
    }
    @Override
    public void saveArchivedModuleList(ReadOnlyModuleList archivedModuleList) throws IOException {
        saveArchivedModuleList(archivedModuleList, archivedModuleListStorage.getModuleListFilePath());
    }

    @Override
    public void saveArchivedModuleList(ReadOnlyModuleList moduleList, Path filePath) throws IOException {
        logger.fine("Attempting to write to archived modulelist data file: " + filePath);
        archivedModuleListStorage.saveModuleList(moduleList, filePath);
    }
    // ================ ContactList methods ==============================
    @Override
    public Path getContactListFilePath() {
        return contactListStorage.getContactListFilePath();
    }

    @Override
    public Optional<ReadOnlyContactList> readContactList() throws DataConversionException, IOException {
        return readContactList(contactListStorage.getContactListFilePath());
    }

    @Override
    public Optional<ReadOnlyContactList> readContactList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return contactListStorage.readContactList(filePath);
    }

    @Override
    public void saveContactList(ReadOnlyContactList contactList) throws IOException {
        saveContactList(contactList, contactListStorage.getContactListFilePath());
    }

    @Override
    public void saveContactList(ReadOnlyContactList contactList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        contactListStorage.saveContactList(contactList, filePath);
    }

    // ================ TodoList methods ==============================
    @Override
    public Path getTodoListFilePath() {
        return todoListStorage.getTodoListFilePath();
    }

    @Override
    public Optional<ReadOnlyTodoList> readTodoList() throws DataConversionException, IOException {
        return readTodoList(todoListStorage.getTodoListFilePath());
    }

    @Override
    public Optional<ReadOnlyTodoList> readTodoList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read Todo list data from file: " + filePath);
        return todoListStorage.readTodoList(filePath);
    }

    @Override
    public void saveTodoList(ReadOnlyTodoList todoList) throws IOException {
        saveTodoList(todoList, todoListStorage.getTodoListFilePath());
    }

    @Override
    public void saveTodoList(ReadOnlyTodoList todoList, Path filePath) throws IOException {
        logger.fine("Attempting to write Todo list to data file: " + filePath);
        todoListStorage.saveTodoList(todoList, filePath);
    }

    @Override
    public Path getEventListFilePath() {
        return eventListStorage.getEventListFilePath();
    }

    @Override
    public Optional<ReadOnlyEventList> readEventList() throws DataConversionException, IOException {
        return eventListStorage.readEventList();
    }

    @Override
    public Optional<ReadOnlyEventList> readEventList(Path filePath) throws DataConversionException, IOException {
        return eventListStorage.readEventList(filePath);
    }

    @Override
    public void saveEventList(ReadOnlyEventList eventList) throws IOException {
        eventListStorage.saveEventList(eventList);
    }

    @Override
    public void saveEventList(ReadOnlyEventList eventList, Path filePath) throws IOException {
        eventListStorage.saveEventList(eventList, filePath);
    }

}
