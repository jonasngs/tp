package seedu.address.logic.commands;

//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
// import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
// import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
// import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CONTACT;
// import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

//import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
// import seedu.address.model.Model;
// import seedu.address.model.ModelManager;
// import seedu.address.model.ModuleList;
// import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
//import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.ContactBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditModuleCommandTest {

    // private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Contact editedPerson = new ContactBuilder().build();
        //EditCommand.EditModuleDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        //EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        //String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        // Model expectedModel = new ModelManager(new ModuleList(model.getAddressBook()), new UserPrefs());
        // expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        // assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        // Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        // Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        // PersonBuilder personInList = new PersonBuilder(lastPerson);
        // Person editedPerson = personInList.withName(VALID_NAME_BOB)
        //         .withTags(VALID_TAG_HUSBAND).build();

        //EditCommand.EditModuleDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
        //        .withTags(VALID_TAG_HUSBAND).build();
        // EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        // String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        // Model expectedModel = new ModelManager(new ModuleList(model.getAddressBook()), new UserPrefs());
        // expectedModel.setPerson(lastPerson, editedPerson);

        // assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        //EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditCommand.EditModuleDescriptor());
        // Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        // String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        // Model expectedModel = new ModelManager(new ModuleList(model.getAddressBook()), new UserPrefs());

        // assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        // showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        // Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        // editCommand = new EditCommand(INDEX_FIRST_PERSON,
        //        new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        // String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        // Model expectedModel = new ModelManager(new ModuleList(model.getAddressBook()), new UserPrefs());
        // expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        // ssertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        // Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        // EditModuleDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        // ditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);

        // assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        // showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        // Person personInList = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        // EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
        //         new EditPersonDescriptorBuilder(personInList).build());

        // assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        // Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        //EditCommand.EditModuleDescriptor descriptor = new EditPersonDescriptorBuilder()
        // .withName(VALID_NAME_BOB).build();
        // EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        // assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        // showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_CONTACT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        // assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        //EditCommand editCommand = new EditCommand(outOfBoundIndex,
        //        new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        //assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        //final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        //EditCommand.EditModuleDescriptor copyDescriptor = new EditCommand.EditModuleDescriptor(DESC_AMY);
        //EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        //assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        //assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        //assertFalse(standardCommand.equals(null));

        // different types -> returns false
        //assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        //assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        //assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }

}
