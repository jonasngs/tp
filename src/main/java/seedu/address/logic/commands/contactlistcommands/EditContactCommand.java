package seedu.address.logic.commands.contactlistcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactName;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Telegram;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing contact in the contact list.
 */
public class EditContactCommand extends Command {

    public static final String COMMAND_WORD = "editcontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the contact identified "
            + "by the index number used in the displayed contact list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_TELEGRAM + "TELEGRAM]..."
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "johndoe"
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_CONTACT_SUCCESS = "Edited Contact: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_CONTACT = "This contact already exists in the contact list.";

    private final Logger logger = LogsCenter.getLogger(EditContactCommand.class);

    private final Index index;
    private final EditContactDescriptor editContactDescriptor;

    /**
     * Creates and initialises a new EditContactCommand object.
     *
     * @param index of the contact in the filtered contact list to edit
     * @param editContactDescriptor details to edit the contact with
     */
    public EditContactCommand(Index index, EditContactDescriptor editContactDescriptor) {
        requireAllNonNull(index, editContactDescriptor);

        assert index.getZeroBased() >= 0 : "zero based index must be non-negative";

        this.index = index;
        this.editContactDescriptor = new EditContactDescriptor(editContactDescriptor);
        logger.info("Editing a contact at index " + index.getOneBased());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownList = model.getFilteredContactList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        Contact contactToEdit = lastShownList.get(index.getZeroBased());
        Contact editedContact = createEditedContact(contactToEdit, editContactDescriptor);

        if (!contactToEdit.isSameContact(editedContact) && model.hasContact(editedContact)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONTACT);
        }

        model.setContact(contactToEdit, editedContact);
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        model.commitContactList();
        logger.info("Contact has been edited");
        return new CommandResult(String.format(MESSAGE_EDIT_CONTACT_SUCCESS, editedContact));
    }

    /**
     * Creates and returns a {@code Contact} with the details of {@code contactToEdit}
     * edited with {@code editContactDescriptor}.
     */
    private Contact createEditedContact(Contact contactToEdit, EditContactDescriptor editContactDescriptor) {
        requireAllNonNull(contactToEdit, editContactDescriptor);

        Contact editedContact;
        ContactName updatedName = editContactDescriptor.getName().orElse(contactToEdit.getName());
        Email updatedEmail = editContactDescriptor.getEmail().orElse(contactToEdit.getEmail());
        Set<Tag> updatedTags = editContactDescriptor.getTags().orElse(contactToEdit.getTags());
        boolean isImportant = contactToEdit.isImportant();

        if (contactToEdit.getTelegram().isPresent() || editContactDescriptor.getTelegram().isPresent()) {
            Telegram updatedTelegram = editContactDescriptor.getTelegram()
                    .orElseGet(() -> contactToEdit.getTelegram().get());
            editedContact = new Contact(updatedName, updatedEmail, updatedTelegram, updatedTags, isImportant);
        } else {
            editedContact = new Contact(updatedName, updatedEmail, updatedTags, isImportant);
        }

        logger.info("Edited contact created: " + editedContact.toString());
        return editedContact;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditContactCommand)) {
            return false;
        }

        // state check
        EditContactCommand e = (EditContactCommand) other;
        return index.equals(e.index)
                && editContactDescriptor.equals(e.editContactDescriptor);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
