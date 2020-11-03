package seedu.address.logic.commands.modulelistcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MC_4;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULENAME_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULENAME_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CORE_MODULE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOMLINKS_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOMLINKS_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOMLINK_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOMLINK_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.ContactList;
import seedu.address.model.EventList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleLesson;
import seedu.address.testutil.EditModuleDescriptorBuilder;
import seedu.address.testutil.ModuleBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditModuleCommandTest {

    private Model model = new ModelManager(getTypicalModuleList(), new ModuleList(), new ContactList(), new TodoList(),
            new EventList(), new UserPrefs());
    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Module editedModule = new ModuleBuilder().build();
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(editedModule).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_FIRST_MODULE, descriptor);
        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);
        Model expectedModel = new ModelManager(new ModuleList(model.getModuleList()), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        expectedModel.setModule(model.getFilteredModuleList().get(0), editedModule);
        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastModule = Index.fromOneBased(model.getFilteredModuleList().size());
        Module lastModule = model.getFilteredModuleList().get(indexLastModule.getZeroBased());
        ModuleBuilder moduleInList = new ModuleBuilder(lastModule);
        Module editedModule = moduleInList.withName(VALID_MODULENAME_CS2103T)
                .withZoomLink(new ModuleLesson(VALID_TAG_LECTURE), VALID_ZOOMLINK_CS2103T)
                .withTags(VALID_TAG_CORE_MODULE).withMC(VALID_MC_4).build();
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withName(VALID_MODULENAME_CS2103T).withZoomLinks(VALID_ZOOMLINKS_CS2103T)
                .withTags(VALID_TAG_CORE_MODULE).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(indexLastModule, descriptor);
        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);
        Model expectedModel = new ModelManager(new ModuleList(model.getModuleList()), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        expectedModel.setModule(lastModule, editedModule);
        boolean equal = model.equals(expectedModel);
        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_FIRST_MODULE,
                new EditModuleDescriptor());
        Module editedModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);
        Model expectedModel = new ModelManager(new ModuleList(model.getModuleList()), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        Module moduleInFilteredList = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        Module editedModule = new ModuleBuilder(moduleInFilteredList).withName(VALID_MODULENAME_CS2030)
                .withZoomLink(new ModuleLesson(VALID_TAG_LECTURE), VALID_ZOOMLINK_CS2030)
                .withModularCredits(4.0).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_FIRST_MODULE,
                new EditModuleDescriptorBuilder().withZoomLinks(VALID_ZOOMLINKS_CS2030)
                        .withName(VALID_MODULENAME_CS2030).withMc(4.0).build());
        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);
        Model expectedModel = new ModelManager(new ModuleList(model.getModuleList()), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        expectedModel.setModule(model.getFilteredModuleList().get(0), editedModule);
        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateModuleUnfilteredList_failure() {
        Module firstModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(firstModule).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_SECOND_MODULE, descriptor);
        assertCommandFailure(editModuleCommand, model, EditModuleCommand.MESSAGE_DUPLICATE_MODULE);
    }

    @Test
    public void execute_duplicateModuleFilteredList_failure() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        // edit person in filtered list into a duplicate in module list
        Module moduleInList = model.getModuleList().getModuleList().get(INDEX_SECOND_MODULE.getZeroBased());
        EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_FIRST_MODULE,
            new EditModuleDescriptorBuilder(moduleInList).build());
        assertCommandFailure(editModuleCommand, model, EditModuleCommand.MESSAGE_DUPLICATE_MODULE);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredModuleList().size() + 1);
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
            .withName(VALID_MODULENAME_CS2030).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(outOfBoundIndex, descriptor);
        assertCommandFailure(editModuleCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        Index outOfBoundIndex = INDEX_SECOND_MODULE;
        // ensures that outOfBoundIndex is still in bounds of module list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getModuleList().getModuleList().size());
        EditModuleCommand editModuleCommand = new EditModuleCommand(outOfBoundIndex,
                    new EditModuleDescriptorBuilder().withName(VALID_MODULENAME_CS2030).build());
        assertCommandFailure(editModuleCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditModuleCommand standardCommand = new EditModuleCommand(INDEX_FIRST_MODULE, DESC_CS2030);

        // same values -> returns true
        EditModuleDescriptor copyDescriptor = new EditModuleDescriptor(DESC_CS2030);
        EditModuleCommand commandWithSameValues = new EditModuleCommand(INDEX_FIRST_MODULE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new UndoCommand()));

        // different module name -> returns false
        assertFalse(standardCommand.equals(new EditModuleCommand(INDEX_SECOND_MODULE, DESC_CS2030)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditModuleCommand(INDEX_SECOND_MODULE, DESC_CS2103T)));
    }

}
