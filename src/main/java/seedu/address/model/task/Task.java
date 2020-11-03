package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Task in the todo list.
 * Guarantees: non-null field values are validated, immutable.
 */
public class Task {
    private final TaskName name;
    private final Set<Tag> tags = new HashSet<>();
    private final Priority priority;
    private final Date date;
    private final Status status;

    /**
     * Initial constructor to avoid having null as arguments.
     *
     * @param name name of the task
     */
    public Task(TaskName name) {
        assert name != null;
        this.name = name;
        this.priority = null;
        this.date = null;
        this.status = Status.NOT_COMPLETED;
    }

    /**
     * Constructor to support immutability.
     *
     * @param name name of the task
     * @param tags tags related to the task
     * @param priority priority of the task
     * @param date date of the task
     * @param status status of the task
     */
    public Task(TaskName name, Set<Tag> tags, Priority priority, Date date, Status status) {
        requireNonNull(name);
        this.name = name;
        this.tags.addAll(tags);
        this.priority = priority;
        this.date = date;
        this.status = status;
    }

    public Optional<TaskName> getName() {
        return Optional.of(this.name);
    }

    public Task setName(TaskName name) {
        return new Task(name, this.tags, this.priority, this.date, this.status);
    }

    public Optional<Set<Tag>> getTags() {
        return Optional.of(this.tags).map(Collections::unmodifiableSet);
    }

    public Task setTags(Set<Tag> tags) {
        return new Task(this.name, tags, this.priority, this.date, this.status);
    }

    public Optional<Priority> getPriority() {
        return Optional.ofNullable(this.priority);
    }

    public Task setPriority(Priority priority) {
        return new Task(this.name, this.tags, priority, this.date, this.status);
    }

    public Optional<Date> getDate() {
        return Optional.ofNullable(this.date);
    }

    public Task setDate(Date date) {
        return new Task(this.name, this.tags, this.priority, date, this.status);
    }

    public Optional<Status> getStatus() {
        assert this.status != null;
        return Optional.of(this.status);
    }

    public Task setStatus(Status status) {
        return new Task(this.name, this.tags, this.priority, this.date, status);
    }

    /**
     * Returns true if both task have the same name.
     * This defines a weaker notion of equality between two tasks.
     *
     * @param otherTask other task to be compared
     * @return true if both task has the same name.
     */
    public boolean isSameTask(Task otherTask) {
        if (this == otherTask) {
            return true;
        }

        return getName().equals((otherTask.getName()));
    }

    /**
     * Returns true if this task has the specified date.
     *
     * @param date the specified date
     * @return true if this task has the specified date.
     */
    public boolean hasSameDate(Date date) {
        requireNonNull(this.date);
        return this.date.equals(date);
    }

    /**
     * Returns true if this task has the specified priority.
     *
     * @param priority the specified priority
     * @return true if this task has the specified priority
     */
    public boolean hasSamePriority(Priority priority) {
        requireNonNull(this.priority);
        return this.priority.equals(priority);
    }

    /**
     * Returns true if this task has the specified tag(s).
     *
     * @param tag the specified tag
     * @return true if this task has the specified tag(s)
     */
    public boolean hasSameTag(Set<Tag> tag) {
        requireNonNull(this.tags);
        return this.tags.equals(tag);
    }

    /**
     * Checks if two tasks are equal.
     * This defines a stronger equality between two tasks.
     *
     * @param other other task to be compared
     * @return true if both task have the same name, type, priority, date, and status
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getName().equals(getName())
            && otherTask.getTags().equals(getTags())
            && otherTask.getPriority().equals(getPriority())
            && otherTask.getDate().equals(getDate())
            && otherTask.getStatus().equals(getStatus());
    }

    /**
     * Returns string representing of the name of the task for the UI.
     *
     * @return string to be displayed in the UI.
     */
    public String getNameForUi() {
        assert this.name != null;
        return this.name.toString();
    }

    /**
     * Returns a set representing of the tags of the task for the UI.
     *
     * @return set of tags to be displayed in the UI.
     */
    public Set<Tag> getTagsForUi() {
        if (this.tags == null) {
            HashSet<Tag> defaultTags = new HashSet<>();
            defaultTags.add(new Tag("TagNotProvided"));
            return defaultTags;
        } else {
            return this.tags;
        }
    }

    /**
     * Returns string representing of the priority of the task for the UI.
     *
     * @return string to be displayed in the UI.
     */
    public String getPriorityForUi() {
        if (this.priority == null) {
            return "Priority not provided";
        } else {
            return this.priority.toString();
        }
    }

    /**
     * Returns string representing of the date of the task for the UI.
     *
     * @return string to be displayed in the UI.
     */
    public String getDateForUi() {
        if (this.date == null) {
            return "Date not provided";
        } else {
            return this.date.toString();
        }
    }

    /**
     * Returns string representing of the status of the task for the UI.
     *
     * @return string to be displayed in the UI.
     */
    public String getStatusForUi() {
        assert this.status != null;
        return this.status.toString();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder
                .append(" *Name: ")
                .append(getName().isPresent() ? getName().get() : "")
                .append("\n")
                .append(" *Tag: ")
                //.append(getTags().isPresent() ? getTags().get() : "")
                .append(getTags().isPresent() ? getTags().get() : "")
                .append("\n")
                .append(" *Priority: ")
                .append(getPriority().isPresent() ? getPriority().get() : "")
                .append("\n")
                .append(" *Date: ")
                .append(getDate().isPresent() ? getDate().get() : "")
                .append("\n")
                .append(" *Status: ")
                .append(getStatus().isPresent() ? getStatus().get() : "")
                .append("\n");
        return builder.toString();
    }

}
