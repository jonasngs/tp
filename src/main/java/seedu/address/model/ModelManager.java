package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.contact.Contact;
import seedu.address.model.module.Module;
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ModuleList moduleList;
    private final VersionedModuleList versionedModuleList;
    private final ContactList contactList;
    private final TodoList todoList;
    private final UserPrefs userPrefs;
    private final FilteredList<Module> filteredModules;
    private final FilteredList<Contact> filteredContacts;
    private final FilteredList<Task> filteredTasks;
    private final SortedList<Task> sortedTasks;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyModuleList moduleList, ReadOnlyContactList contactList, ReadOnlyTodoList todoList,
                        ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(moduleList, todoList, userPrefs);

        logger.fine("Initializing with module list: " + moduleList + " and todo list" + todoList
                + " and user prefs " + userPrefs);

        this.moduleList = new ModuleList(moduleList);
        this.versionedModuleList = new VersionedModuleList(moduleList);
        this.contactList = new ContactList(contactList);
        this.todoList = new TodoList(todoList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredModules = new FilteredList<Module>(this.moduleList.getModuleList());
        filteredContacts = new FilteredList<Contact>(this.contactList.getContactList());
        filteredTasks = new FilteredList<Task>(this.todoList.getTodoList());
        sortedTasks = new SortedList<Task>(this.todoList.getTodoList());
    }

    public ModelManager() {
        this(new ModuleList(), new ContactList(), new TodoList(), new UserPrefs());
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
    public void setModuleListFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setModuleListFilePath(addressBookFilePath);
    }

    //=========== Module List ================================================================================

    @Override
    public void setModuleList(ReadOnlyModuleList moduleList) {
        this.moduleList.resetData(moduleList);
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
    }

    @Override
    public void addModule(Module module) {
        moduleList.addModule(module);
        updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
    }

    @Override
    public void setModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);

        moduleList.setModule(target, editedModule);
    }

    @Override
    public void commitModuleList() {
        versionedModuleList.commit(moduleList);
    }

    @Override
    public void undoModuleList() {
        versionedModuleList.undo();
        setModuleList(versionedModuleList.getCurrentModuleList());
    };

    @Override
    public void redoModuleList() {
        versionedModuleList.redo();
        setModuleList(versionedModuleList.getCurrentModuleList());
    };

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

    //=========== Filtered Module List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Module} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return filteredModules;
    }

    @Override
    public void updateFilteredModuleList(Predicate<Module> predicate) {
        requireNonNull(predicate);
        filteredModules.setPredicate(predicate);
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

    //=========== Sorted List Accessors =============================================================

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
        requireNonNull(comparator);
        sortedTasks.setComparator(comparator);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return moduleList.equals(other.moduleList)
                && userPrefs.equals(other.userPrefs)
                && filteredModules.equals(other.filteredModules)
                && filteredContacts.equals(other.filteredContacts)
                && filteredTasks.equals(other.filteredTasks)
                && sortedTasks.equals(other.sortedTasks);
    }

}
