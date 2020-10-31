package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
// import seedu.address.model.contact.Contact;
import seedu.address.model.module.ModularCredits;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleLesson;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.ZoomLink;
import seedu.address.model.module.grade.GradeTracker;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Module}.
 */
class JsonAdaptedModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";

    private final String name;
    private final List<JsonAdaptedZoomLink> zoomLinks = new ArrayList<>();
    private final String modularCredits;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final JsonAdaptedGradeTracker gradeTracker;

    /**
     * Constructs a {@code JsonAdaptedModule} with the given module details.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("name") String name,
                             @JsonProperty("zoomLinks") List<JsonAdaptedZoomLink> zoomLinks,
                             @JsonProperty("gradeTracker") JsonAdaptedGradeTracker storedGradeTracker,
                             @JsonProperty("modularCredits") String storedModularCredits,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        if (zoomLinks != null) {
            this.zoomLinks.addAll(zoomLinks);
        }
        if (storedGradeTracker == null) {
            this.gradeTracker = new JsonAdaptedGradeTracker(new GradeTracker());
        } else {
            this.gradeTracker = storedGradeTracker;
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.modularCredits = storedModularCredits;
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        name = source.getName().fullName;
        source.getAllLinks().forEach((lesson, link) -> zoomLinks.add(new JsonAdaptedZoomLink(lesson.getLesson(),
                link.getLink())));
        gradeTracker = new JsonAdaptedGradeTracker(source.getGradeTracker());
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        modularCredits = source.getModularCredits().toString();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Module toModelType() throws IllegalValueException {

        // ============================= Tags ================================== //

        final List<Tag> moduleTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            moduleTags.add(tag.toModelType());
        }
        final Set<Tag> modelTags = new HashSet<>(moduleTags);

        // ============================= Name ================================== //

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleName.class.getSimpleName()));
        }
        if (!ModuleName.isValidName(name)) {
            throw new IllegalValueException(ModuleName.MESSAGE_CONSTRAINTS);
        }
        final ModuleName modelName = new ModuleName(name);

        // =========================== ZoomLink =============================== //

        final Map<ModuleLesson, ZoomLink> modelZoomLinks = new HashMap<>();
        for (JsonAdaptedZoomLink link : zoomLinks) {
            ModuleLesson lesson = new ModuleLesson(link.getLesson());
            ZoomLink zoomLink = link.toModelType();
            modelZoomLinks.put(lesson, zoomLink);
        }

        // ========================= GradeTracker ============================= //

        if (gradeTracker == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GradeTracker.class.getSimpleName()));
        }
        if (!GradeTracker.isValidGradeTracker(gradeTracker.toModelType())) {
            throw new IllegalValueException(GradeTracker.MESSAGE_INVALID_GRADE);
        }

        // ========================= ModularCredits =========================== //

        if (!ModularCredits.isValidModularCredits(modularCredits)) {
            throw new IllegalValueException(ModularCredits.MESSAGE_CONSTRAINTS);
        }
        final ModularCredits modelModularCredits = new ModularCredits(Double.parseDouble(modularCredits));

        return new Module(modelName, modelZoomLinks, gradeTracker.toModelType(), modelTags, modelModularCredits);
    }

}
