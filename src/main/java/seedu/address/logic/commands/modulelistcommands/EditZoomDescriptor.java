package seedu.address.logic.commands.modulelistcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.module.ModuleLesson;
import seedu.address.model.module.ZoomLink;

/**
 * Stores the details to edit the zoom link with.
 */
public class EditZoomDescriptor {

    /** Module lesson which the zoom link to be edited belongs to. */
    private ModuleLesson lesson;
    /** Edited zoom link. */
    private ZoomLink zoomLink;

    public EditZoomDescriptor() {}

    /**
     * Copy constructor.
     */
    public EditZoomDescriptor(EditZoomDescriptor toCopy) {
        setZoomLink(toCopy.zoomLink);
        setModuleLesson(toCopy.lesson);
    }

    public void setZoomLink(ZoomLink zoomLink) {
        requireNonNull(zoomLink);
        this.zoomLink = zoomLink;
    }

    public ZoomLink getZoomLink() {
        requireNonNull(this.zoomLink);
        return this.zoomLink;
    }

    public void setModuleLesson(ModuleLesson lesson) {
        requireNonNull(lesson);
        this.lesson = lesson;
    }

    public ModuleLesson getModuleLesson() {
        requireNonNull(this.lesson);
        return this.lesson;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditZoomDescriptor)) {
            return false;
        }

        // state check
        EditZoomDescriptor e = (EditZoomDescriptor) other;

        return getZoomLink().equals(e.getZoomLink())
                && getModuleLesson().equals(e.getModuleLesson());
    }
}
