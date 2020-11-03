package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyEventList;

public class JsonEventListStorage implements EventListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonEventListStorage.class);

    private Path filePath;

    public JsonEventListStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getEventListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyEventList> readEventList() throws DataConversionException, IOException {
        return readEventList(filePath);
    }

    @Override
    public Optional<ReadOnlyEventList> readEventList(Path filePath) throws DataConversionException, IOException {
        requireNonNull(filePath);

        Optional<JsonSerializableEventList> jsonEventList = JsonUtil.readJsonFile(
                filePath, JsonSerializableEventList.class);
        if (!jsonEventList.isPresent()) {
            return Optional.empty();
        }
        try {
            return Optional.of(jsonEventList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveEventList(ReadOnlyEventList eventList) throws IOException {
        saveEventList(eventList, filePath);
    }

    @Override
    public void saveEventList(ReadOnlyEventList eventList, Path filePath) throws IOException {
        requireNonNull(eventList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableEventList(eventList), filePath);
    }
}
