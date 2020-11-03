package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.ReadOnlyEventList;


/**
 * Represents a storage for {@link seedu.address.model.EventList}.
 */
public interface EventListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getEventListFilePath();

    /**
     * Returns EventList data as a {@link ReadOnlyEventList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyEventList> readEventList() throws DataConversionException, IOException;

    /**
     * @see #getEventListFilePath()
     */
    Optional<ReadOnlyEventList> readEventList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyContactList} to the storage.
     * @param eventList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveEventList(ReadOnlyEventList eventList) throws IOException;

    /**
     * @see #saveEventList(ReadOnlyEventList)
     */
    void saveEventList(ReadOnlyEventList eventList, Path filePath) throws IOException;
}
