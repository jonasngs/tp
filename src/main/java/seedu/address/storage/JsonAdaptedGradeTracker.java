package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.grade.Grade;
import seedu.address.model.module.grade.GradePoint;
import seedu.address.model.module.grade.GradeTracker;

/**
 * Jackson-friendly version of {@link GradeTracker}.
 */

public class JsonAdaptedGradeTracker {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "GradeTracker's %s field is missing!";

    private final List<JsonAdaptedAssignment> assignments = new ArrayList<>();
    private final double grade;
    //private final Optional<GradePoint> gradePoint;
    private final String gradePoint;
    /**
     * Constructs a {@code JsonAdaptedGradeTracker} with the given Grade Tracker details.
     */
    @JsonCreator
    public JsonAdaptedGradeTracker(@JsonProperty("assignments") List<JsonAdaptedAssignment> assignments,
                             @JsonProperty("grade") double grade, @JsonProperty("gradepoint") String gradePoint) {
        this.grade = grade;
        if (assignments != null) {
            this.assignments.addAll(assignments);
        }
        this.gradePoint = gradePoint;
    }

    /**
     * Converts a given {@code GradeTracker} into this class for Jackson use.
     */
    public JsonAdaptedGradeTracker(GradeTracker source) {
        assignments.addAll(source.getAssignments().stream()
                .map(JsonAdaptedAssignment::new)
                .collect(Collectors.toList()));
        this.grade = source.getGrade().gradeResult;
        assert source.getGradePoint() != null : "Assertion error, gradepoint not defined";
        if (source.getGradePoint().isEmpty()) {
            this.gradePoint = null;
        } else {
            this.gradePoint = source.getGradePoint().get().toString();
        }
    }

    /**
     * Converts this Jackson-friendly adapted Grade Tracker object into the model's {@code GradeTracker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted GradeTracker.
     */
    public GradeTracker toModelType() throws IllegalValueException {
        GradeTracker modelGradeTracker = new GradeTracker();
        for (JsonAdaptedAssignment assignment : assignments) {
            if (!modelGradeTracker.containsDuplicateAssignment(assignment.toModelType())) {
                modelGradeTracker.addAssignment(assignment.toModelType());
            } else {
                throw new IllegalValueException(GradeTracker.MESSAGE_DUPLICATE_ASSIGNMENT);
            }
        }
        if (Double.isNaN(grade)) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.class.getSimpleName()));
        }
        if (!Grade.isValidGrade(grade)) {
            throw new IllegalValueException(GradeTracker.MESSAGE_INVALID_GRADE);
        }
        final Grade modelGrade = new Grade(grade);
        final GradePoint modelGradePoint;
        if (gradePoint == null) {
            modelGradePoint = null;
        } else if (!GradePoint.isValidGradePoint(gradePoint)) {
            throw new IllegalValueException(GradeTracker.MESSAGE_INVALID_GRADEPOINT);
        } else {
            modelGradePoint = new GradePoint(Double.parseDouble(gradePoint));
        }
        modelGradeTracker.setGrade(modelGrade);
        modelGradeTracker.setGradePoint(modelGradePoint);
        return modelGradeTracker;
    }
}
