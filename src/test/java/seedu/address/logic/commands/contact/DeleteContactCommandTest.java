package seedu.address.logic.commands.contact;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showContactAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.getTypicalContactList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CONTACT;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;

public class DeleteContactCommandTest {

    private Model model = new ModelManager(new ModuleList(), getTypicalContactList(),
            new TodoList(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteContactCommand(null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        DeleteContactCommand deleteContactCommand = new DeleteContactCommand(INDEX_FIRST_CONTACT);
        assertThrows(NullPointerException.class, () -> deleteContactCommand.execute(null));
    }

    @Test
    public void execute_validIndexUnfilteredList_deleteSuccess() {
        Contact contactToDelete = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        DeleteContactCommand deleteContactCommand = new DeleteContactCommand(INDEX_FIRST_CONTACT);

        String expectedMessage = String.format(DeleteContactCommand.MESSAGE_DELETE_PERSON_SUCCESS, contactToDelete);

        ModelManager expectedModel = new ModelManager(model.getModuleList(), model.getContactList(),
                model.getTodoList(), new UserPrefs());
        expectedModel.deleteContact(contactToDelete);

        assertCommandSuccess(deleteContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        int outOfBoundIndex = model.getFilteredContactList().size() + 1;
        Index invalidIndex = Index.fromOneBased(outOfBoundIndex);
        DeleteContactCommand deleteContactCommand = new DeleteContactCommand(invalidIndex);

        assertCommandFailure(deleteContactCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_deleteSuccess() {

        // update Contact filtered list to contain only a single Contact
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        Contact contactToDelete = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        DeleteContactCommand deleteContactCommand = new DeleteContactCommand(INDEX_FIRST_CONTACT);

        String expectedMessage = String.format(DeleteContactCommand.MESSAGE_DELETE_PERSON_SUCCESS, contactToDelete);

        Model expectedModel = new ModelManager(model.getModuleList(), model.getContactList(),
                model.getTodoList(), new UserPrefs());
        expectedModel.deleteContact(contactToDelete);
        showNoContact(expectedModel);

        assertCommandSuccess(deleteContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        Index outOfBoundIndex = INDEX_SECOND_CONTACT;

        // ensures that outOfBoundIndex is still in bounds of contact list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getContactList().getContactList().size());

        DeleteContactCommand deleteContactCommand = new DeleteContactCommand(outOfBoundIndex);

        assertCommandFailure(deleteContactCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }


    /**
     * Updates {@code model}'s filtered contact list to show no contact.
     */
    private void showNoContact(Model model) {
        model.updateFilteredContactList(p -> false);

        assertTrue(model.getFilteredContactList().isEmpty());
    }

}
