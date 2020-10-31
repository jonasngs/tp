package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.contact.Contact;
import seedu.address.model.event.Event;
import seedu.address.model.exceptions.VersionedListException;
import seedu.address.model.module.Module;
import seedu.address.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Contact> PREDICATE_SHOW_ALL_CONTACTS = unused -> true;

    Predicate<Module> PREDICATE_SHOW_ALL_MODULES = unused -> true;

    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

    Predicate<Event> PREDICATE_SHOW_ALL_EVENTS = unused -> true;

    // ==================== UserPrefs ===============================================================

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getModuleListFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    // ============================ ModuleList ==================================================

    /**
     * Replaces module list data with the data in {@code modulelist}.
     */
    void setModuleList(ReadOnlyModuleList moduleList);

    /** Returns the ModuleList */
    ReadOnlyModuleList getModuleList();

    /**
     * Returns true if a module with the same identity as {@code module} exists in the module list.
     */
    boolean hasModule(Module module);

    /**
     * Deletes the given module.
     * The module must exist in the module list.
     */
    void deleteModule(Module target);

    /**
     * Adds the given module.
     * {@code module} must not already exist in the module list.
     */
    void addModule(Module module);

    /**
     * Replaces the given module {@code target} with {@code editedModule}.
     * {@code target} must exist in the module list.
     * The module identity of {@code editedModule} must not be the same as another existing module in the module.
     */
    void setModule(Module target, Module editedModule);

    /** Returns an unmodifiable view of the filtered module list */
    ObservableList<Module> getFilteredModuleList();

    /**
     * Updates the filter of the filtered module list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleList(Predicate<Module> predicate);

    /**
     * Saves the current module list state in history.
     */
    void commitModuleList();

    /**
     * Restores the previous module list state from history.
     */
    void undoModuleList() throws VersionedListException;

    /**
     * Restores the previously undone module list state from history.
     */
    void redoModuleList() throws VersionedListException;

    /**
     * Replaces archived module list data with the data in {@code modulelist}.
     */
    void setArchivedModuleList(ReadOnlyModuleList moduleList);

    /** Returns the archived ModuleList */
    ReadOnlyModuleList getArchivedModuleList();

    /**
     * Returns true if a module with the same identity as {@code module} exists in the archived module list.
     */
    boolean hasArchivedModule(Module module);

    /**
     * Deletes the given module.
     * The module must exist in the archived module list.
     */
    void deleteArchivedModule(Module target);
    /**
     * Adds the given module to the archived module list.
     * {@code module} must not already exist in the archived module list.
     */
    void addArchivedModule(Module module);

    /**
     * Replaces the given module {@code target} with {@code editedModule}.
     * {@code target} must exist in the archived module list.
     * The module identity of {@code editedModule} must not be the same as another existing module in the module.
     */
    void setArchivedModule(Module target, Module editedModule);

    /**
     * Moves the given module in the module list into the archived module list
     * {@code target} must exist in the module list.
     * {@code module} must not already exist in the archived module list.
     */
    void archiveModule(Module target);

    /**
     * Moves the given module in the archived module list into the module list
     * {@code target} must exist in the module list.
     * {@code module} must not already exist in the archived module list.
     */
    void unarchiveModule(Module target);

    /**
     * Sets module list to display the archived module list
     */
    void displayArchivedModules();

    /**
     * Sets module list to display the non-archived module list
     */
    void displayNonArchivedModules();

    /** Returns an unmodifiable view of the filtered archived module list */
    ObservableList<Module> getFilteredArchivedModuleList();

    /** Returns an unmodifiable view of the filtered module list */
    ObservableList<Module> getFilteredUnarchivedModuleList();

    /**
     * Updates the filter of the filtered archived module list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredArchivedModuleList(Predicate<Module> predicate);
    // ============================ ContactList ==================================================

    /**
     * Replaces contact list data with the data in {@code contactlist}.
     */
    void setContactList(ReadOnlyContactList contactList);

    /** Returns the ContactList */
    ReadOnlyContactList getContactList();

    /**
     * Returns true if a contact with the same identity as {@code contact} exists in the contact list.
     */
    boolean hasContact(Contact contact);

    /**
     * Deletes the given contact.
     * The contact must exist in the contact list.
     */
    void deleteContact(Contact target);

    /**
     * Adds the given contact.
     * {@code contact} must not already exist in the contact list.
     */
    void addContact(Contact contact);

    /**
     * Replaces the given contact {@code target} with {@code editedContact}.
     * {@code target} must exist in the contact list.
     * The contact identity of {@code editedContact} must not be the same as another existing
     * contact in the contact list.
     */
    void setContact(Contact target, Contact editedContact);

    /** Returns an unmodifiable view of the filtered contact list */
    ObservableList<Contact> getFilteredContactList();

    /**
     * Updates the filter of the filtered contact list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredContactList(Predicate<Contact> predicate);

    /** Returns an unmodifiable view of the filtered contact list */
    ObservableList<Contact> getSortedContactList();

    /**
     * Updates the filter of the filtered contact list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateSortedContactList(Comparator<Contact> comparator);

    /**
     * Returns the file path of the contact list.
     * @return Path contact list file path.
     */
    public Path getContactListFilePath();

    /**
     * Saves the current contact list state in history.
     */
    void commitContactList();

    /**
     * Restores the previous contact list state from history.
     */
    void undoContactList() throws VersionedListException;

    /**
     * Restores the previously undone contact list state from history.
     */
    void redoContactList() throws VersionedListException;

    // ============================ TodoList ==================================================

    /**
     * Replaces todo list data with the data in {@code todolist}.
     */
    void setTodoList(ReadOnlyTodoList todoList);

    /** Returns the TodoList */
    ReadOnlyTodoList getTodoList();

    /**
     * Returns true if a task with the same name, date, and type as {@code task} exists in the todo list.
     */
    boolean hasTask(Task task);

    /**
     * Deletes the given task.
     * The task must exist in the todo list.
     */
    void deleteTask(Task target);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the todo list.
     */
    void addTask(Task task);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the todo list.
     * The task name of {@code editedTask} must not be the same as another existing task in the todo list.
     */
    void setTask(Task target, Task editedTask);

    /** Returns an unmodifiable view of the filtered todo list */
    ObservableList<Task> getFilteredTodoList();

    /**
     * Updates the filter of the filtered todo list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTodoList(Predicate<Task> predicate);

    /** Returns an unmodifiable view of the filtered todo list */
    ObservableList<Task> getSortedTodoList();

    /**
     * Updates the filter of the filtered todo list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateSortedTodoList(Comparator<Task> comparator);

    // ========================== Scheduler Methods ============================================ //

    /**
     * Replaces Event list data with the data in {@code Eventlist}.
     */
    void setEventList(ReadOnlyEventList eventList);

    /** Returns the EventList */
    ReadOnlyEventList getEventList();

    /**
     * Returns true if a Event with the same identity as {@code Event} exists in the Event list.
     */
    boolean hasEvent(Event event);

    /**
     * Deletes the given Event.
     * The Event must exist in the Event list.
     */
    void deleteEvent(Event target);

    /**
     * Adds the given Event.
     * {@code Event} must not already exist in the Event list.
     */
    void addEvent(Event event);

    /**
     * Replaces the given Event {@code target} with {@code editedEvent}.
     * {@code target} must exist in the Event list.
     * The Event identity of {@code editedEvent} must not be the same as another existing Event in the Event List.
     */
    void setEvent(Event target, Event editedEvent);

    /** Returns an unmodifiable view of the filtered Event list */
    ObservableList<Event> getFilteredEventList();

    /**
     * Updates the filter of the filtered Event list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<Event> predicate);

    ///**
    // * Saves the current Event list state in history.
    // */
    //void commitEventList();
    /**
     * Saves the current todo list state in history.
     */
    void commitTodoList();

    /**
     * Restores the previous todo list state from history.
     */
    void undoTodoList() throws VersionedListException;

    /**
     * Restores the previously undone todo list state from history.
     */
    void redoTodoList() throws VersionedListException;

    /**
     * Saves the current CAP5Buddy list state in history.
     */
    void commit(int type);

    /**
     * Restores the previous CAP5Buddy state from history.
     */
    void undo() throws VersionedListException;

    /**
     * Restores the previously undone CAP5Buddy state from history.
     */
    void redo() throws VersionedListException;

    /**
     * Returns true if archived module list is being displayed
     */
    boolean getModuleListDisplay();
}
