package seedu.address.testutil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import seedu.address.model.module.ModularCredits;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleLesson;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.ZoomLink;
import seedu.address.model.module.grade.GradeTracker;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Module objects.
 */
public class ModuleBuilder {

    public static final String DEFAULT_MODULENAME = "CS2020";
    public static final String DEFAULT_MODULELESSONTYPE = "lecture";
    public static final String DEFAULT_ZOOMLINK = "https://nus-sg.zoom.us/CS2020";
    public static final double DEFAULT_MODULARCREDITS = 4.0;

    private ModuleName moduleName;
    private Map<ModuleLesson, ZoomLink> zoomLinkMap;
    private ModularCredits modularCredits;
    private GradeTracker gradeTracker;
    private Set<Tag> tags;
    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        moduleName = new ModuleName(DEFAULT_MODULENAME);
        zoomLinkMap = new HashMap<ModuleLesson, ZoomLink>();
        //zoomLinkMap.put(new ModuleLesson(DEFAULT_MODULELESSONTYPE), new ZoomLink(DEFAULT_ZOOMLINK));
        modularCredits = new ModularCredits(DEFAULT_MODULARCREDITS);
        gradeTracker = new GradeTracker();
        tags = new HashSet<Tag>();
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        moduleName = moduleToCopy.getName();
        zoomLinkMap = new HashMap<>(moduleToCopy.getAllLinks());
        modularCredits = moduleToCopy.getModularCredits();
        gradeTracker = moduleToCopy.getGradeTracker();
        tags = new HashSet<>(moduleToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Module} that we are building.
     */
    public ModuleBuilder withName(String name) {
        this.moduleName = new ModuleName(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Module} that we are building.
     */
    public ModuleBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code ZoomLinks} of the {@code Module} that we are building.
     */
    public ModuleBuilder withZoomLink(ModuleLesson moduleLessonType, String zoomLink) {
        Map<ModuleLesson, ZoomLink> updatedLinks = new HashMap<>();
        updatedLinks.put(moduleLessonType, new ZoomLink(zoomLink));
        this.zoomLinkMap = updatedLinks;
        return this;
    }

    /**
     * Sets the {@code ZoomLink} of the {@code Module} that we are building.
     */
    public ModuleBuilder withModularCredits(double modularCredits) {
        this.modularCredits = new ModularCredits(modularCredits);
        return this;
    }
    /**
     * Sets the {@code ModuleCredits} of the {@code Module} that we are building.
     */
    public ModuleBuilder withMC(double value) {
        this.modularCredits = new ModularCredits(value);
        return this;
    }

    /**
     * Adds the {@code Tag} to the {@code Module} that we are building.
     */
    public ModuleBuilder withTag(String tag) {
        Set<Tag> updatedTag = new HashSet<Tag>(this.tags);
        updatedTag.add(new Tag(tag));
        return this;
    }

    /**
     * Builds the module.
     *
     * @return a module
     */
    public Module build() {
        return new Module(moduleName, zoomLinkMap, gradeTracker, tags, modularCredits);
    }
}
