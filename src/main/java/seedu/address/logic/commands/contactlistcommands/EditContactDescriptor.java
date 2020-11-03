package seedu.address.logic.commands.contactlistcommands;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.contact.ContactName;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Telegram;
import seedu.address.model.tag.Tag;

/**
 * Stores the details to edit the contact with. Each non-empty field value will replace the
 * corresponding field value of the contact.
 */
public class EditContactDescriptor {
    private ContactName name;
    private Email email;
    private Telegram telegram;
    private Set<Tag> tags;

    public EditContactDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditContactDescriptor(EditContactDescriptor toCopy) {
        setName(toCopy.name);
        setEmail(toCopy.email);
        setTelegram(toCopy.telegram);
        setTags(toCopy.tags);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, email, telegram, tags);
    }

    public void setName(ContactName name) {
        this.name = name;
    }

    public Optional<ContactName> getName() {
        return Optional.ofNullable(name);
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Optional<Email> getEmail() {
        return Optional.ofNullable(email);
    }

    public void setTelegram(Telegram telegram) {
        this.telegram = telegram;
    }

    public Optional<Telegram> getTelegram() {
        return Optional.ofNullable(telegram);
    }

    /**
     * Sets {@code tags} to this object's {@code tags}.
     * A defensive copy of {@code tags} is used internally.
     */
    public void setTags(Set<Tag> tags) {
        this.tags = (tags != null) ? new HashSet<>(tags) : null;
    }

    /**
     * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code tags} is null.
     */
    public Optional<Set<Tag>> getTags() {
        return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditContactDescriptor)) {
            return false;
        }

        // state check
        EditContactDescriptor e = (EditContactDescriptor) other;

        return getName().equals(e.getName())
                && getEmail().equals(e.getEmail())
                && getTelegram().equals(e.getTelegram())
                && getTags().equals(e.getTags());
    }
}
