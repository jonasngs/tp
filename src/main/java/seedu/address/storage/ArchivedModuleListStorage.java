package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ModuleList;
import seedu.address.model.ReadOnlyModuleList;

/**
 * Represents a storage for {@link ModuleList}.
 */
public interface ArchivedModuleListStorage extends ModuleListStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getArchivedModuleListFilePath();

    /**
     * Returns ArchivedModuleList data as a {@link ReadOnlyModuleList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyModuleList> readArchivedModuleList() throws DataConversionException, IOException;

    /**
     * @see #getArchivedModuleListFilePath()
     */
    Optional<ReadOnlyModuleList> readArchivedModuleList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyModuleList} to the storage.
     * @param archivedModuleList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveArchivedModuleList(ReadOnlyModuleList archivedModuleList) throws IOException;

    /**
     * @see #saveArchivedModuleList(ReadOnlyModuleList)
     */
    void saveArchivedModuleList(ReadOnlyModuleList archivedModuleList, Path filePath) throws IOException;
}
