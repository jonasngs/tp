package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.ZoomLink;

/**
 * Jackson-friendly version of {@link ZoomLink}.
 */
class JsonAdaptedZoomLink {

    private final String link;

    // Additional data for mapping
    private final String lesson;

    /**
     * Constructs a {@code JsonAdaptedZoomLink} with the given {@code link}.
     */
    @JsonCreator
    public JsonAdaptedZoomLink(@JsonProperty("lesson") String lesson,
                               @JsonProperty("link") String link) {
        this.lesson = lesson;
        this.link = link;
    }

    /**
     * Converts a given {@code ZoomLink} into this class for Jackson use.
     */
    public JsonAdaptedZoomLink(String key, ZoomLink source) {
        this.lesson = key;
        link = source.getLink();
    }

    public String getLesson() {
        return lesson;
    }

    public String getLink() {
        return link;
    }

    /**
     * Converts this Jackson-friendly adapted zoom link object into the model's {@code ZoomLink} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted zoom link.
     */
    public ZoomLink toModelType() throws IllegalValueException {
        if (!ZoomLink.isValidZoomLink(link)) {
            throw new IllegalValueException(ZoomLink.MESSAGE_CONSTRAINTS);
        }
        return new ZoomLink(link);
    }

}
