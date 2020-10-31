package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a lesson of a module.
 * Guarantees: immutable; is valid as declared in {@link #isValidLesson(String)}
 */
public class ModuleLesson {

    public static final String MESSAGE_CONSTRAINTS =
            "Lesson name should only contain alphanumeric characters and spaces, and it should not be blank.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private final String lessonName;

    /**
     * Creates and initialises a new {@code Lesson} object with a {@code lessonName}.
     *
     * @param lessonName String containing the lesson name.
     */
    public ModuleLesson(String lessonName) {
        requireNonNull(lessonName);
        checkArgument(isValidLesson(lessonName), MESSAGE_CONSTRAINTS);
        this.lessonName = lessonName;
    }

    public String getLesson() {
        return this.lessonName;
    }

    /**
     * Returns true if the given String is a valid Lesson.
     *
     * @param lesson String containing the lesson name.
     * @return True if the lesson is valid, false otherwise.
     */
    public static boolean isValidLesson(String lesson) {
        return lesson.matches(VALIDATION_REGEX);
    }

    @Override
    public int hashCode() {
        return this.lessonName.hashCode();
    }

    @Override
    public String toString() {
        return this.lessonName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleLesson // instanceof handles nulls
                && lessonName.equals(((ModuleLesson) other).lessonName)); // state check
    }

}
