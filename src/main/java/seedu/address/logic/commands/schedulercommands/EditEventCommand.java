package seedu.address.logic.commands.schedulercommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.tag.Tag;

public class EditEventCommand extends Command {

    public static final String COMMAND_WORD = "editevent";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the Event identified "
            + "by the index number used in the displayed Event list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "CS2103T homework"
            + PREFIX_DATE + "1-2-2020 1259";
    public static final String MESSAGE_EDIT_EVENT_SUCCESS = "Edited Event: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the Event list.";

    private Index index;
    private EditEventDescriptor descriptor;

    /**
     * Creates an EditEventCommand.
     *
     * @param index index of the event.
     * @param descriptor the container that hold the changes.
     */
    public EditEventCommand(Index index, EditEventDescriptor descriptor) {
        requireNonNull(index);
        requireNonNull(descriptor);
        this.index = index;
        this.descriptor = descriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredEventList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        Event eventToEdit = lastShownList.get(index.getZeroBased());
        Event updatedEvent = createEditedEvent(eventToEdit, descriptor);
        System.out.println(eventToEdit.equals(updatedEvent));
        if (model.hasEvent(updatedEvent)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }
        model.setEvent(eventToEdit, updatedEvent);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        //TODO: Implement undo redo for scheduler
        //model.commitEventList();
        return new CommandResult(String.format(MESSAGE_EDIT_EVENT_SUCCESS, updatedEvent));
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private static Event createEditedEvent(Event toEdit, EditEventDescriptor descriptor) {
        requireNonNull(toEdit);
        requireNonNull(descriptor);
        Set<Tag> updatedTags = descriptor.getTags().orElse(toEdit.getTags());
        EventName updatedName = descriptor.getName().orElse(toEdit.getName());
        //TODO: Implement Event to take in Tags
        EventTime updatedTime = descriptor.getTime().orElse(toEdit.getTime());
        return new Event(updatedName, updatedTime);
    }

    /**
     * Represents the EditEventDescriptor that holds all the changes.
     */
    public static class EditEventDescriptor {
        private EventName eventName;
        private EventTime eventTime;
        private Set<Tag> tags;

        public EditEventDescriptor() {}

        /**
         * Represents the constructor that creates the container to hold the changes.
         *
         * @param toCopy EditEventDescriptor to be cpoied.
         */
        public EditEventDescriptor(EditEventDescriptor toCopy) {
            this.eventName = toCopy.eventName;
            this.eventTime = toCopy.eventTime;
            this.tags = toCopy.tags;
        }

        public void setName(EventName name) {
            this.eventName = name;
        }

        public void setTime(EventTime time) {
            this.eventTime = time;
        }

        public void setTags(Set<Tag> tags) {
            this.tags = tags;
        }

        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(eventName, eventTime);
        }

        public Optional<EventName> getName() {
            return Optional.ofNullable(this.eventName);
        }

        public Optional<EventTime> getTime() {
            return Optional.ofNullable(this.eventTime);
        }

        public Optional<Set<Tag>> getTags() {
            return (this.tags != null) ? Optional.of(Collections.unmodifiableSet(this.tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            } else if (other instanceof EditEventDescriptor) {
                EditEventDescriptor descriptor = (EditEventDescriptor) other;
                return this.eventName.equals(descriptor.eventName)
                        && this.eventTime.equals(descriptor.eventTime);
            } else {
                return false;
            }
        }
    }
}
