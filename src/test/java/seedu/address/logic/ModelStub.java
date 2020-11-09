package seedu.address.logic;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ModuleList;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.ReadOnlyEventList;
import seedu.address.model.ReadOnlyModuleList;
import seedu.address.model.ReadOnlyTodoList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.model.event.Event;
import seedu.address.model.module.Module;
import seedu.address.model.task.Task;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getModuleListFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setContactListFilePath(Path contactListFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addModule(Module person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setModuleList(ReadOnlyModuleList newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyModuleList getModuleList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyModuleList getModuleListDisplayed() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasModule(Module person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteModule(Module target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setModule(Module target, Module editedPerson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredModuleList(Predicate<Module> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addContact(Contact person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setContactList(ReadOnlyContactList newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyContactList getContactList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasContact(Contact person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteContact(Contact target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setContact(Contact target, Contact editedPerson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Contact> getFilteredContactList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredContactList(Predicate<Contact> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getContactListFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addTask(Task task) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTodoList(ReadOnlyTodoList newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyTodoList getTodoList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasTask(Task task) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteTask(Task target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Task> getFilteredTodoList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredTodoList(Predicate<Task> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Task> getSortedTodoList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateSortedTodoList(Comparator<Task> comparator) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setEventList(ReadOnlyEventList eventList) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyEventList getEventList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasEvent(Event event) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteEvent(Event target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addEvent(Event event) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void displayEventList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setEvent(Event target, Event editedEvent) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Event> getFilteredEventList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void commitEventList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void undoEventList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void redoEventList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Contact> getSortedContactList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateSortedContactList(Comparator<Contact> comparator) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void commitModuleList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void redoModuleList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void undoModuleList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void commitContactList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void redoContactList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void undoContactList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void commitTodoList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void redoTodoList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void undoTodoList() {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void commit(int i) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void undo() {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void redo() {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void setArchivedModuleList(ReadOnlyModuleList readOnlyArchivedModuleList) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public ModuleList getArchivedModuleList() {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public boolean hasArchivedModule(Module module) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void deleteArchivedModule(Module target) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void addArchivedModule(Module module) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setArchivedModule(Module module, Module editedModule) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void archiveModule(Module target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void unarchiveModule(Module target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void displayArchivedModules() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void displayNonArchivedModules() {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public boolean getModuleListDisplay() {
        throw new AssertionError("This method should not be called.");
    }
}
