package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.ReadOnlyEventList;
import seedu.address.model.ReadOnlyModuleList;
import seedu.address.model.ReadOnlyTodoList;
import seedu.address.model.contact.Contact;
import seedu.address.model.event.Event;
import seedu.address.model.module.Module;
import seedu.address.model.task.Task;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getModuleList()
     */
    ReadOnlyModuleList getModuleList();

    /** Returns an unmodifiable view of the filtered list of modules */
    ObservableList<Module> getFilteredModuleList();

    /**
     * Returns an unmodifiable view of the filtered list of modules for display
     */
    ObservableList<Module> getFilteredModuleListDisplay();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getModuleListFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the Contact List.
     */
    ReadOnlyContactList getContactList();

    /**
     * Returns an unmodifiable list of filtered contacts.
     */
    ObservableList<Contact> getFilteredContactList();

    /**
     * Returns the Todo List.
     */
    public ReadOnlyTodoList getTodoList();

    /**
     * Returns an unmodifiable list of filtered tasks.
     */
    ObservableList<Task> getFilteredTodoList();

    /**
     * Returns the file Path of the Contact List.
     * @return Path of contact list.
     */
    public Path getContactListFilePath();

    public ReadOnlyEventList getEventList();

    public ObservableList<Event> getFilteredEventList();

    public Path getEventListFilePath();

}
