package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Zoom Link for a module in the Module List.
 * Guarantees: immutable; is valid as declared in {@link #isValidZoomLink(String)}
 */
public class ZoomLink {

    public static final String MESSAGE_CONSTRAINTS = "Zoom links should belong to the NUS domain"
            + " and adhere to the format https://nus-sg.zoom.us/[path]";
    public static final String ZOOM_LINK_DOMAIN = "https://nus-sg.zoom.us/";
    public static final String ZOOM_LINK_PATH = "[a-zA-Z0-9?=/]+";
    public static final String VALIDATION_REGEX = ZOOM_LINK_DOMAIN + ZOOM_LINK_PATH;

    private final String link;

    /**
     * Constructs an {@code ZoomLink}.
     *
     * @param zoomLink A valid zoom link.
     */
    public ZoomLink(String zoomLink) {
        requireNonNull(zoomLink);
        checkArgument(isValidZoomLink(zoomLink), MESSAGE_CONSTRAINTS);
        link = zoomLink;
    }

    /**
     * Returns true if the given zoom link is a valid zoom link.
     */
    public static boolean isValidZoomLink(String zoomLink) {
        return zoomLink.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the link stored in the object.
     * @return String zoom link.
     */
    public String getLink() {
        return this.link;
    }

    @Override
    public String toString() {
        return this.link;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ZoomLink // instanceof handles nulls
                && link.equals(((ZoomLink) other).link)); // state check
    }

    @Override
    public int hashCode() {
        return link.hashCode();
    }

}
