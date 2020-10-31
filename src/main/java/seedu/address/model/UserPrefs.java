package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path moduleListFilePath = Paths.get("data" , "moduleList.json");
    private Path archivedModuleListFilePath = Paths.get("data/archive" , "archivedModuleList.json");
    private Path contactListFilePath = Paths.get("data" , "contactList.json");
    private Path todoListFilePath = Paths.get("data" , "todoList.json");
    private Path eventListFilePath = Paths.get("data", "eventList.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        //ModuleList
        setModuleListFilePath(newUserPrefs.getModuleListFilePath());
        setArchivedModuleListFilePath(newUserPrefs.getArchivedModuleListFilePath());
        setContactListFilePath(newUserPrefs.getContactListFilePath());
        setTodoListFilePath(newUserPrefs.getTodoListFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }
    @Override
    public Path getModuleListFilePath() {
        return moduleListFilePath;
    }
    @Override
    public Path getArchivedModuleListFilePath() {
        return archivedModuleListFilePath;
    }

    public Path getContactListFilePath() {
        return contactListFilePath;
    }

    public Path getTodoListFilePath() {
        return todoListFilePath;
    }

    public Path getEventListFilePath() {
        return eventListFilePath;
    }

    public void setModuleListFilePath(Path moduleListFilePath) {
        requireNonNull(moduleListFilePath);
        this.moduleListFilePath = moduleListFilePath;
    }

    public void setArchivedModuleListFilePath(Path archivedModuleListFilePath) {
        requireNonNull(archivedModuleListFilePath);
        this.archivedModuleListFilePath = archivedModuleListFilePath;
    }

    public void setContactListFilePath(Path contactListFilePath) {
        requireNonNull(contactListFilePath);
        this.contactListFilePath = contactListFilePath;
    }

    public void setTodoListFilePath(Path todoListFilePath) {
        requireNonNull(todoListFilePath);
        this.todoListFilePath = todoListFilePath;
    }

    public void setEventListFilePath(Path eventListFilePath) {
        this.eventListFilePath = eventListFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && moduleListFilePath.equals(o.moduleListFilePath)
                && contactListFilePath.equals(o.contactListFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, moduleListFilePath, contactListFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + moduleListFilePath + "\n");
        sb.append(contactListFilePath);
        return sb.toString();
    }

}
