package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.contact.Contact;
import seedu.address.model.event.Event;
import seedu.address.model.exceptions.VersionedListException;
import seedu.address.model.module.Module;
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    public static final String MESSAGE_NO_UNDO_HISTORY = "There are no commands to undo";
    public static final String MESSAGE_NO_REDO_HISTORY = "There are no commands to redo";
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private final ModuleList moduleListDisplay;
    private final ModuleList moduleList;
    private final ArchivedModuleList archivedModuleList;
    private final VersionedModuleList versionedModuleList;
    private final VersionedModuleList versionedArchivedModuleList;
    private final ContactList contactList;
    private final VersionedContactList versionedContactList;
    private final TodoList todoList;
    private final EventList eventList;
    private final VersionedTodoList versionedTodoList;
    private final UserPrefs userPrefs;
    private final FilteredList<Module> filteredModulesDisplay;
    private final FilteredList<Module> filteredModules;
    private final FilteredList<Module> filteredArchivedModules;
    private final FilteredList<Contact> filteredContacts;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<Event> filteredEvents;
    private final SortedList<Contact> sortedContacts;
    private final SortedList<Task> sortedTasks;
    private int accessPointer;
    private List<Integer> accessSequence;
    private boolean isArchiveModuleOnDisplay = false;
    private FilteredList<Module> mainList;

    /**
     * Initializes a ModelManager with the given moduleList, archivedModuleList, contactList, todoList,
     * eventList and userPrefs.
     */
    public ModelManager(ReadOnlyModuleList moduleList, ReadOnlyModuleList archivedModuleList,
                        ReadOnlyContactList contactList, ReadOnlyTodoList todoList, ReadOnlyEventList eventList,
                        ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(moduleList, todoList, userPrefs);

        logger.fine("Initializing with module list: " + moduleList + " and todo list" + todoList
                + " and user prefs " + userPrefs);
        this.moduleList = new ModuleList(moduleList);
        this.moduleListDisplay = new ModuleList(moduleList);
        this.archivedModuleList = new ArchivedModuleList(archivedModuleList);
        this.versionedModuleList = new VersionedModuleList(moduleList);
        this.versionedArchivedModuleList = new VersionedModuleList(archivedModuleList);
        this.contactList = new ContactList(contactList);
        this.versionedContactList = new VersionedContactList(contactList);
        this.todoList = new TodoList(todoList);
        this.eventList = new EventList(eventList);
        this.versionedTodoList = new VersionedTodoList(todoList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredModulesDisplay = new FilteredList<Module>(this.moduleListDisplay.getModuleList());
        filteredModules = new FilteredList<Module>(this.moduleList.getModuleList());
        filteredArchivedModules = new FilteredList<Module>(this.archivedModuleList.getModuleList());
        sortedContacts = new SortedList<Contact>(this.contactList.getContactList());
        filteredContacts = new FilteredList<Contact>(sortedContacts);
        filteredEvents = new FilteredList<Event>(this.eventList.getEventList());
        sortedTasks = new SortedList<Task>(this.todoList.getTodoList());
        filteredTasks = new FilteredList<Task>(sortedTasks);
        accessPointer = 0;
        accessSequence = new ArrayList<>();
        accessSequence.add(0);
    }
    /**
     * Initializes a ModelManager with a blank moduleList, archivedModuleList, contactList, todoList,
     * eventList and userPrefs.
     */
    public ModelManager() {
        this(new ModuleList(), new ArchivedModuleList(), new ContactList(), new TodoList(),
                new EventList(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getModuleListFilePath() {
        return userPrefs.getModuleListFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setModuleListFilePath(addressBookFilePath);
    }

    //=========== Module List ================================================================================

    @Override
    public void setModuleList(ReadOnlyModuleList moduleList) {
        this.moduleList.resetData(moduleList);
        if (!getModuleListDisplay()) {
            this.moduleListDisplay.resetData(moduleList);
        }
    }

    @Override
    public ReadOnlyModuleList getModuleList() {
        return moduleList;
    }

    @Override
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return moduleList.hasModule(module);
    }

    @Override
    public void deleteModule(Module target) {
        moduleList.removeModule(target);
        if (!getModuleListDisplay()) {
            moduleListDisplay.removeModule(target);
        }
    }

    @Override
    public void addModule(Module module) {
        moduleList.addModule(module);
        if (!getModuleListDisplay()) {
            moduleListDisplay.addModule(module);
        }
        updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
    }

    @Override
    public void setModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);
        moduleList.setModule(target, editedModule);
        if (!getModuleListDisplay()) {
            moduleListDisplay.setModule(target, editedModule);
        }
    }

    @Override
    public void commitModuleList() {
        assert accessPointer >= 0;
        accessSequence.subList(this.accessPointer + 1, accessSequence.size()).clear();
        versionedModuleList.commit(moduleList);
        versionedArchivedModuleList.commit(archivedModuleList);
        accessSequence.add(1);
        accessPointer += 1;
    }

    @Override
    public void undoModuleList() throws VersionedListException {
        assert accessPointer >= 0;
        try {
            versionedModuleList.undo();
            versionedArchivedModuleList.undo();
        } catch (VersionedListException versionedListException) {
            throw versionedListException;
        }
        setModuleList(versionedModuleList.getCurrentModuleList());
        setArchivedModuleList(versionedArchivedModuleList.getCurrentModuleList());
    }

    @Override
    public void redoModuleList() throws VersionedListException {
        assert accessPointer >= 0;
        try {
            versionedModuleList.redo();
            versionedArchivedModuleList.redo();
        } catch (VersionedListException versionedListException) {
            throw versionedListException;
        }
        setModuleList(versionedModuleList.getCurrentModuleList());
        setArchivedModuleList(versionedArchivedModuleList.getCurrentModuleList());
    }
    //Archived Modules
    @Override
    public void setArchivedModuleList(ReadOnlyModuleList archivedModuleList) {
        this.archivedModuleList.resetData(archivedModuleList);
        if (getModuleListDisplay()) {
            this.moduleListDisplay.resetData(archivedModuleList);
        }
    }

    @Override
    public ReadOnlyModuleList getArchivedModuleList() {
        return archivedModuleList;
    }

    @Override
    public boolean hasArchivedModule(Module module) {
        requireNonNull(module);
        return archivedModuleList.hasModule(module);
    }

    @Override
    public void deleteArchivedModule(Module target) {
        archivedModuleList.removeModule(target);
        if (getModuleListDisplay()) {
            this.moduleListDisplay.removeModule(target);
        }
    }
    @Override
    public void addArchivedModule(Module module) {
        archivedModuleList.addModule(module);
        if (getModuleListDisplay()) {
            this.moduleListDisplay.addModule(module);
        }
        updateFilteredArchivedModuleList(PREDICATE_SHOW_ALL_MODULES);
    }

    @Override
    public void setArchivedModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);
        archivedModuleList.setModule(target, editedModule);
        if (getModuleListDisplay()) {
            this.moduleListDisplay.setModule(target, editedModule);
        }
    }

    @Override
    public void archiveModule(Module target) {
        deleteModule(target);
        addArchivedModule(target);
    }

    @Override
    public void unarchiveModule(Module target) {
        deleteArchivedModule(target);
        addModule(target);
    }
    @Override
    public void displayArchivedModules() {
        isArchiveModuleOnDisplay = true;
        this.moduleListDisplay.resetData(archivedModuleList);
        //mainList = filteredArchivedModules;
    }
    @Override
    public void displayNonArchivedModules() {
        isArchiveModuleOnDisplay = false;
        this.moduleListDisplay.resetData(moduleList);
    }
    //=========== Contact List ================================================================================

    @Override
    public void setContactList(ReadOnlyContactList contactList) {
        this.contactList.resetData(contactList);
    }

    @Override
    public ReadOnlyContactList getContactList() {
        return contactList;
    }

    @Override
    public boolean hasContact(Contact contact) {
        requireNonNull(contact);
        return contactList.hasContact(contact);
    }

    @Override
    public void deleteContact(Contact target) {
        contactList.removeContact(target);
    }

    @Override
    public void addContact(Contact contact) {
        contactList.addContact(contact);
        updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
    }

    @Override
    public void setContact(Contact target, Contact editedContact) {
        requireAllNonNull(target, editedContact);

        contactList.setContact(target, editedContact);
    }

    @Override
    public Path getContactListFilePath() {
        return userPrefs.getContactListFilePath();
    }

    @Override
    public void commitContactList() {
        assert accessPointer >= 0;
        accessSequence.subList(this.accessPointer + 1, accessSequence.size()).clear();
        versionedContactList.commit(contactList);
        accessSequence.add(2);
        accessPointer += 1;
    }

    @Override
    public void undoContactList() throws VersionedListException {
        assert accessPointer >= 0;
        try {
            versionedContactList.undo();
        } catch (VersionedListException versionedListException) {
            throw versionedListException;
        }
        setContactList(versionedContactList.getCurrentContactList());
    }

    @Override
    public void redoContactList() throws VersionedListException {
        assert accessPointer >= 0;
        try {
            versionedContactList.redo();
        } catch (VersionedListException versionedListException) {
            throw versionedListException;
        }
        setContactList(versionedContactList.getCurrentContactList());
    }

    //=========== Todo List =============================================================


    @Override
    public void setTodoList(ReadOnlyTodoList todoList) {
        this.todoList.resetData(todoList);
    }

    @Override
    public ReadOnlyTodoList getTodoList() {
        return todoList;
    }

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return todoList.hasTask(task);
    }

    @Override
    public void deleteTask(Task target) {
        todoList.removeTask(target);
    }

    @Override
    public void addTask(Task task) {
        todoList.addTask(task);
        updateFilteredTodoList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        todoList.setTask(target, editedTask);
    }

    @Override
    public void commitTodoList() {
        assert accessPointer >= 0;
        accessSequence.subList(this.accessPointer + 1, accessSequence.size()).clear();
        versionedTodoList.commit(todoList);
        accessSequence.add(3);
        accessPointer += 1;
    }

    @Override
    public void undoTodoList() throws VersionedListException {
        assert accessPointer >= 0;
        try {
            versionedTodoList.undo();
        } catch (VersionedListException versionedListException) {
            throw versionedListException;
        }
        setTodoList(versionedTodoList.getCurrentTodoList());
    }

    @Override
    public void redoTodoList() throws VersionedListException {
        assert accessPointer >= 0;
        try {
            versionedTodoList.redo();
        } catch (VersionedListException versionedListException) {
            throw versionedListException;
        }
        setTodoList(versionedTodoList.getCurrentTodoList());
    }

    //=========== General =============================================================
    @Override
    public void commit(int type) {
        if (type == 1) {
            commitModuleList();
        } else if (type == 2) {
            commitContactList();
        } else {
            commitTodoList();
        }
    }
    @Override
    public void undo() throws VersionedListException {
        if (accessPointer == 0) {
            throw new VersionedListException(MESSAGE_NO_UNDO_HISTORY);
        }
        int pointer = accessSequence.get(accessPointer);
        try {
            if (pointer == 1) {
                undoModuleList();
            } else if (pointer == 2) {
                undoContactList();
            } else {
                undoTodoList();
            }
        } catch (VersionedListException versionedListException) {
            throw versionedListException;
        }
        accessPointer -= 1;
    }
    @Override
    public void redo() throws VersionedListException {
        if (accessPointer >= accessSequence.size() - 1) {
            throw new VersionedListException(MESSAGE_NO_REDO_HISTORY);
        }
        int pointer = accessSequence.get(accessPointer + 1);
        try {
            if (pointer == 1) {
                redoModuleList();
            } else if (pointer == 2) {
                redoContactList();
            } else {
                redoTodoList();
            }
        } catch (VersionedListException versionedListException) {
            throw versionedListException;
        }
        accessPointer += 1;
    }

    //=========== Filtered Module List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Module} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return filteredModulesDisplay;
    }

    @Override
    public void updateFilteredModuleList(Predicate<Module> predicate) {
        requireNonNull(predicate);
        //filteredModules.setPredicate(predicate);
        filteredModulesDisplay.setPredicate(predicate);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Module} backed by the internal list of
     * {@code versionedModuleList}
     */
    @Override
    public ObservableList<Module> getFilteredArchivedModuleList() {
        return filteredArchivedModules;
    }
    @Override
    public void updateFilteredArchivedModuleList(Predicate<Module> predicate) {
        requireNonNull(predicate);
        filteredArchivedModules.setPredicate(predicate);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Module} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Module> getFilteredUnarchivedModuleList() {
        return filteredModules;
    }



    /**
     * Returns an unmodifiable view of the list of {@code Contact} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Contact> getFilteredContactList() {
        return filteredContacts;
    }

    @Override
    public void updateFilteredContactList(Predicate<Contact> predicate) {
        requireNonNull(predicate);
        filteredContacts.setPredicate(predicate);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Task> getFilteredTodoList() {
        return filteredTasks;
    }

    @Override
    public void updateFilteredTodoList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    @Override
    public ObservableList<Event> getFilteredEventList() {
        return filteredEvents;
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        filteredEvents.setPredicate(predicate);
    }

    //=========== Sorted List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Contact} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Contact> getSortedContactList() {
        return sortedContacts;
    }

    @Override
    public void updateSortedContactList(Comparator<Contact> comparator) {
        // No assertion here because comparator value can be null to reset ordering.
        sortedContacts.setComparator(comparator);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Task> getSortedTodoList() {
        return sortedTasks;
    }

    @Override
    public void updateSortedTodoList(Comparator<Task> comparator) {
        // No assertion here because comparator value can be null to reset ordering.
        sortedTasks.setComparator(comparator);
    }
    // ==================================== Scehduler =============================================== //
    @Override
    public void setEventList(ReadOnlyEventList eventList) {
        this.eventList.resetData(eventList);
    }

    @Override
    public ReadOnlyEventList getEventList() {
        return this.eventList;
    }

    @Override
    public boolean hasEvent(Event event) {
        return this.eventList.hasEvent(event);
    }

    @Override
    public void deleteEvent(Event target) {
        this.eventList.removeEvent(target);
    }

    @Override
    public boolean getModuleListDisplay() {
        return isArchiveModuleOnDisplay;
    }

    @Override
    public void addEvent(Event event) {
        this.eventList.addEvent(event);
    }

    @Override
    public void setEvent(Event target, Event editedEvent) {
        this.eventList.setEvent(target, editedEvent);
    }

    // TODO: Yet to be implemented
    //@Override
    //public void commitEventList() {}

}
