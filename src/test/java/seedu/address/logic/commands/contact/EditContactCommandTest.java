package seedu.address.logic.commands.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.showContactAtIndex;
import static seedu.address.testutil.TypicalContacts.getTypicalContactList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CONTACT;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.model.ContactList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.testutil.ContactBuilder;
import seedu.address.testutil.EditContactDescriptorBuilder;

public class EditContactCommandTest {

    private Model model = new ModelManager(new ModuleList(), getTypicalContactList(),
            new TodoList(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        EditContactDescriptor validDescriptor = new EditContactDescriptor();
        assertThrows(NullPointerException.class, () -> new EditContactCommand(null, validDescriptor));
    }

    @Test
    public void constructor_nullDescriptor_throwsNullPointerException() {
        Index validIndex = INDEX_FIRST_CONTACT;
        assertThrows(NullPointerException.class, () -> new EditContactCommand(validIndex, null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        Index index = INDEX_FIRST_CONTACT;
        EditContactDescriptor descriptor = new EditContactDescriptor();
        EditContactCommand command = new EditContactCommand(index, descriptor);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_editSuccess() {
        Contact editedContact = new ContactBuilder().build();
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(editedContact).build();
        EditContactCommand editContactCommand = new EditContactCommand(INDEX_FIRST_CONTACT, descriptor);

        String expectedMessage = String.format(EditContactCommand.MESSAGE_EDIT_CONTACT_SUCCESS, editedContact);

        Model expectedModel = new ModelManager(new ModuleList(model.getModuleList()),
                new ContactList(model.getContactList()), new TodoList(model.getTodoList()),
                new UserPrefs());
        expectedModel.setContact(model.getFilteredContactList().get(0), editedContact);

        assertCommandSuccess(editContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_editSuccess() {
        Index indexFirstContact = INDEX_FIRST_CONTACT;
        Contact firstContact = model.getFilteredContactList().get(indexFirstContact.getZeroBased());

        ContactBuilder contactInList = new ContactBuilder(firstContact);
        Contact editedContact = contactInList.withName(VALID_NAME_BOB).withEmail(VALID_EMAIL_BOB)
                .build();

        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withName(VALID_NAME_BOB)
                .withEmail(VALID_EMAIL_BOB).build();
        EditContactCommand editContactCommand = new EditContactCommand(indexFirstContact, descriptor);

        String expectedMessage = String.format(EditContactCommand.MESSAGE_EDIT_CONTACT_SUCCESS, editedContact);

        Model expectedModel = new ModelManager(new ModuleList(model.getModuleList()),
                new ContactList(model.getContactList()), new TodoList(model.getTodoList()),
                new UserPrefs());
        expectedModel.setContact(firstContact, editedContact);

        assertCommandSuccess(editContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_editSuccess() {
        EditContactCommand editContactCommand = new EditContactCommand(INDEX_FIRST_CONTACT, new EditContactDescriptor());
        Contact editedContact = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());

        String expectedMessage = String.format(EditContactCommand.MESSAGE_EDIT_CONTACT_SUCCESS, editedContact);

        Model expectedModel = new ModelManager(new ModuleList(model.getModuleList()),
                new ContactList(model.getContactList()), new TodoList(model.getTodoList()),
                new UserPrefs());

        assertCommandSuccess(editContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredContactList_editSuccess() {

        // update Contact filtered list to contain only a single Contact
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        Contact contactInFilteredList = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        Contact editedContact = new ContactBuilder(contactInFilteredList).withName(VALID_NAME_BOB).build();

        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withName(VALID_NAME_BOB).build();

        EditContactCommand editContactCommand = new EditContactCommand(INDEX_FIRST_CONTACT, descriptor);

        String expectedMessage = String.format(EditContactCommand.MESSAGE_EDIT_CONTACT_SUCCESS, editedContact);

        Model expectedModel = new ModelManager(new ModuleList(model.getModuleList()),
                new ContactList(model.getContactList()), new TodoList(model.getTodoList()),
                new UserPrefs());

        expectedModel.setContact(contactInFilteredList, editedContact);

        assertCommandSuccess(editContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateContactUnfilteredList_editFailure() {
        Index firstIndex = INDEX_FIRST_CONTACT;
        Contact firstContact = model.getFilteredContactList().get(firstIndex.getZeroBased());
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(firstContact).build();
        EditContactCommand editContactCommand = new EditContactCommand(INDEX_SECOND_CONTACT, descriptor);

        assertCommandFailure(editContactCommand, model, EditContactCommand.MESSAGE_DUPLICATE_CONTACT);
    }

    @Test
    public void execute_duplicateContactFilteredList_editFailure() {

        // update Contact filtered list to contain only a single Contact
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        int secondContactIndex = INDEX_SECOND_CONTACT.getZeroBased();

        // edit contact in filtered list into a duplicate in the contact list
        Contact secondContactInList = model.getContactList().getContactList().get(secondContactIndex);
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(secondContactInList).build();
        EditContactCommand editContactCommand = new EditContactCommand(INDEX_FIRST_CONTACT, descriptor);

        assertCommandFailure(editContactCommand, model, EditContactCommand.MESSAGE_DUPLICATE_CONTACT);
    }

    @Test
    public void execute_invalidContactIndexUnfilteredList_editFailure() {
        int outOfBoundIndex = model.getFilteredContactList().size() + 1;
        Index invalidIndex = Index.fromOneBased(outOfBoundIndex);
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditContactCommand editContactCommand = new EditContactCommand(invalidIndex, descriptor);

        assertCommandFailure(editContactCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidContactIndexFilteredList_editFailure() {
        // update Contact filtered list to contain only a single Contact
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        Index outOfBoundIndex = INDEX_SECOND_CONTACT;

        // ensures that outOfBoundIndex is still in bounds of contact list
        int contactListSize = model.getContactList().getContactList().size();
        assertTrue(outOfBoundIndex.getZeroBased() < contactListSize);

        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditContactCommand editContactCommand = new EditContactCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editContactCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void createEditedContact_validInputs_success() {

    }


    @Test
    public void equals() {
        final EditContactCommand standardCommand = new EditContactCommand(INDEX_FIRST_CONTACT, DESC_AMY);

        // same index and descriptor -> returns true
        EditContactDescriptor copyDescriptor = new EditContactDescriptor(DESC_AMY);
        EditContactCommand commandWithSameValues = new EditContactCommand(INDEX_FIRST_CONTACT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different command types -> returns false
        assertFalse(standardCommand.equals(new ExitCommand()));

        // different index, same descriptor -> returns false
        assertFalse(standardCommand.equals(new EditContactCommand(INDEX_SECOND_CONTACT, DESC_AMY)));

        // different descriptor, same index -> returns false
        assertFalse(standardCommand.equals(new EditContactCommand(INDEX_FIRST_CONTACT, DESC_BOB)));
    }

}

