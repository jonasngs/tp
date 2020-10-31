package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_SEARCH_KEYWORD;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.ContactName;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Telegram;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.module.ModularCredits;
import seedu.address.model.module.ModuleLesson;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.ZoomLink;
import seedu.address.model.module.grade.Assignment;
import seedu.address.model.module.grade.AssignmentName;
import seedu.address.model.module.grade.AssignmentPercentage;
import seedu.address.model.module.grade.AssignmentResult;
import seedu.address.model.module.grade.Grade;
import seedu.address.model.module.grade.GradePoint;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Criterion;
import seedu.address.model.task.Date;
import seedu.address.model.task.Priority;
import seedu.address.model.task.TaskName;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses a {@code String keywords} into a {@code List<String>}.
     *
     * @param keywords String containing search keywords provided by the user.
     * @return List of search keywords.
     * @throws ParseException If the given {@code keywords} is invalid.
     */
    public static List<String> parseSearchKeywords(String keywords) throws ParseException {
        requireNonNull(keywords);
        String trimmedKeywords = keywords.trim();
        if (trimmedKeywords.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_SEARCH_KEYWORD));
        } else {
            String[] keywordArray = keywords.split("\\s+");
            assert keywordArray.length > 0 : "There must be at least one search keyword provided";
            return Arrays.asList(keywordArray);
        }
    }

    // ==================== ContactList ===============================================================
    /**
     * Parses a {@code String name} into a {@code ContactName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static ContactName parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!ContactName.isValidName(trimmedName)) {
            throw new ParseException(ContactName.MESSAGE_CONSTRAINTS);
        }
        return new ContactName(trimmedName);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String telegramUsername} into an {@code Telegram}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code telegramUsername} is invalid.
     */
    public static Telegram parseTelegram(String telegramUsername) throws ParseException {
        requireNonNull(telegramUsername);
        String trimmedTelegram = telegramUsername.trim();
        if (!Telegram.isValidTelegram(trimmedTelegram)) {
            throw new ParseException(Telegram.MESSAGE_CONSTRAINTS);
        }
        return new Telegram(trimmedTelegram);
    }

    // ==================== ModuleList ===============================================================

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static ModuleName parseModuleName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!ModuleName.isValidName(trimmedName)) {
            throw new ParseException(ModuleName.MESSAGE_CONSTRAINTS);
        }
        return new ModuleName(trimmedName);
    }

    /**
     * Parses a {@code String zoomLink} into a {@code ZoomLink}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code zoomLink} is invalid.
     */
    public static ZoomLink parseZoomLink(String zoomLink) throws ParseException {
        requireNonNull(zoomLink);
        String trimmedZoomLink = zoomLink.trim();
        if (!ZoomLink.isValidZoomLink(trimmedZoomLink)) {
            throw new ParseException(ZoomLink.MESSAGE_CONSTRAINTS);
        }
        return new ZoomLink(trimmedZoomLink);
    }

    /**
     * Parses a {@code String lesson} into a {@code ModuleLesson}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code lesson} is invalid.
     */
    public static ModuleLesson parseModuleLesson(String lesson) throws ParseException {
        requireNonNull(lesson);
        String trimmedLesson = lesson.trim();
        if (!ModuleLesson.isValidLesson(trimmedLesson)) {
            throw new ParseException(ModuleLesson.MESSAGE_CONSTRAINTS);
        }
        return new ModuleLesson(trimmedLesson);
    }

    /**
     * Parses a {@code String assignmentName}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static AssignmentName parseAssignmentName(String assignmentName) throws ParseException {
        String trimmedAssignmentName = assignmentName.trim();
        if (!AssignmentName.isValidAssignmentName(trimmedAssignmentName)) {
            throw new ParseException(Assignment.MESSAGE_ASSIGNMENT_NAME_CONSTRAINTS);
        }
        return new AssignmentName(trimmedAssignmentName);
    }

    /**
     * Parses a {@code String assignmentPercentage}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static AssignmentPercentage parseAssignmentPercentage(String assignmentPercentage) throws ParseException {
        double trimmedAssignmentPercentage = Double.parseDouble(assignmentPercentage.trim());
        if (!AssignmentPercentage.isValidAssignmentPercentage(trimmedAssignmentPercentage)) {
            throw new ParseException(Assignment.MESSAGE_ASSIGNMENT_PERCENTAGE_CONSTRAINTS);
        }
        return new AssignmentPercentage(trimmedAssignmentPercentage);
    }

    /**
     * Parses a {@code String assignmentResult}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static AssignmentResult parseAssignmentResult(String assignmentResult) throws ParseException {
        double trimmedAssignmentResult = Double.parseDouble(assignmentResult.trim());
        if (!AssignmentResult.isValidAssignmentResult(trimmedAssignmentResult)) {
            throw new ParseException(Assignment.MESSAGE_ASSIGNMENT_RESULT_CONSTRAINTS);
        }
        return new AssignmentResult(trimmedAssignmentResult);
    }

    /**
     * Parses a {@code String modularCredits}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static ModularCredits parseModularCredits(String modularCredits) throws ParseException {
        double trimmedModularCredits;
        if (!ModularCredits.isValidModularCredits(modularCredits)) {
            throw new ParseException(ModularCredits.MESSAGE_CONSTRAINTS);
        } else {
            trimmedModularCredits = Double.parseDouble(modularCredits.trim());
        }
        return new ModularCredits(trimmedModularCredits);
    }
    /**
     * Parses a {@code String grade}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static double parseGrade(String grade) throws ParseException {
        double trimmedGrade = Double.parseDouble(grade.trim());
        if (!Grade.isValidGrade(trimmedGrade)) {
            throw new ParseException(Grade.MESSAGE_CONSTRAINTS);
        }
        return trimmedGrade;
    }
    /**
     * Parses a {@code String modularCredits}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static GradePoint parseGradePoint(String gradePoint) throws ParseException {
        double trimmedGradePoint;
        if (!GradePoint.isValidGradePoint(gradePoint)) {
            throw new ParseException(GradePoint.MESSAGE_CONSTRAINTS);
        } else {
            trimmedGradePoint = Double.parseDouble(gradePoint.trim());
        }
        return new GradePoint(trimmedGradePoint);
    }

    // ==================== TodoList ===============================================================

    /**
     * Parses a {@code String name} into a {@code TaskName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static TaskName parseTaskName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!TaskName.isValidTaskName(trimmedName)) {
            throw new ParseException(TaskName.MESSAGE_CONSTRAINTS);
        }
        return new TaskName(trimmedName);
    }

    /**
     * Parses a {@code String priority} into a {@code Priority}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code priority} is invalid.
     */
    public static Priority parseTaskPriority(String priority) throws ParseException {
        assert priority != null;
        String priorityAllUpperCase = priority.toUpperCase();
        switch(priorityAllUpperCase) {
        case("HIGHEST"):
            return Priority.HIGHEST;
        case("HIGH"):
            return Priority.HIGH;
        case("NORMAL"):
            return Priority.NORMAL;
        case("LOW"):
            return Priority.LOW;
        default:
            throw new ParseException(Priority.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String date} into a {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static Date parseTaskDate(String date) throws ParseException {
        assert date != null;
        if (!Date.isValidDate(date)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(date);
    }

    /**
     * Parses a {@code String criterion} into a {@code Criterion}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code criterion} is invalid.
     */
    public static Criterion parseTaskCriterion(String criterion) throws ParseException {
        assert criterion != null;
        String criterionAllUpperCase = criterion.toUpperCase();
        switch(criterionAllUpperCase) {
        case("NAME"):
            return Criterion.NAME;
        case("DATE"):
        case("DEADLINE"):
            return Criterion.DATE;
        case("PRIORITY"):
        case("PRIO"):
            return Criterion.PRIORITY;
        default:
            throw new ParseException(Criterion.MESSAGE_CONSTRAINTS);
        }
    }
    ///////////////////// Scheduler /////////////////////////////

    public static EventName parseEventName(String name) {
        return new EventName(name);
    }

    /**
     * Parses the input string and creates an EventTime object based on that.
     * @param date string to be parsed.
     * @return EventTime.
     */
    public static EventTime parseEventTime(String date) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-uuuu HHmm");
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
            return new EventTime(localDateTime);
        } catch (DateTimeParseException e) {
            throw new ParseException("Invalid date and time entered. Please follow this format: "
                    + System.lineSeparator() + "day-month-year 24h time (d-M-uuuu HHmm)");
        }
    }
}
