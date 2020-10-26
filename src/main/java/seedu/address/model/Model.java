package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.contact.Contact;
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
    void setModuleListFilePath(Path addressBookFilePath);

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
    void undoModuleList();

    /**
     * Restores the previously undone module list state from history.
     */
    void redoModuleList();
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

    /**
     * Returns the file path of the contact list.
     * @return Path contact list file path.
     */
    public Path getContactListFilePath();

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
}
