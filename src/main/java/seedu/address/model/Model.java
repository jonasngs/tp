package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
    Path getAddressBookFilePath();

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
}
