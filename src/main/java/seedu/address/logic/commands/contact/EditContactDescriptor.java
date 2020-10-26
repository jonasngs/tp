package seedu.address.logic.commands.contact;

import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Telegram;

/**
 * Stores the details to edit the contact with. Each non-empty field value will replace the
 * corresponding field value of the contact.
 */
public class EditContactDescriptor {
    private Name name;
    private Email email;
    private Telegram telegram;

    public EditContactDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditContactDescriptor(EditContactDescriptor toCopy) {
        setName(toCopy.name);
        setEmail(toCopy.email);
        setTelegram(toCopy.telegram);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, email, telegram);
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Optional<Name> getName() {
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
                && getTelegram().equals(e.getTelegram());
    }
}
