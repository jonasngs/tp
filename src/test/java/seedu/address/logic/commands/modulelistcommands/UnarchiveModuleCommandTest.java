package seedu.address.logic.commands.modulelistcommands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.*;
import seedu.address.model.module.Module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.*;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;

public class UnarchiveModuleCommandTest {
    private Model model = new ModelManager(new ModuleList(), getTypicalModuleList(), new ContactList(), new TodoList(),
            new EventList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Module moduleToUnarchive = model.getArchivedModuleList().getModuleList()
                .get(INDEX_FIRST_MODULE.getZeroBased());
        UnarchiveModuleCommand unarchiveModuleCommand = new UnarchiveModuleCommand(INDEX_FIRST_MODULE);
        String expectedMessage = String.format(UnarchiveModuleCommand.MESSAGE_UNARCHIVE_MODULE_SUCCESS,
                moduleToUnarchive.getName().getName());
        Model expectedModel = new ModelManager(new ModuleList(), model.getArchivedModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        expectedModel.unarchiveModule(moduleToUnarchive);
        assertCommandSuccess(unarchiveModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredModuleList().size() + 1);
        UnarchiveModuleCommand unarchiveModuleCommand = new UnarchiveModuleCommand(outOfBoundIndex);
        assertCommandFailure(unarchiveModuleCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showArchivedModuleAtIndex(model, INDEX_FIRST_MODULE);
        Module moduleToUnarchive = model.getFilteredArchivedModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        UnarchiveModuleCommand unarchiveModuleCommand = new UnarchiveModuleCommand(INDEX_FIRST_MODULE);
        String expectedMessage = String.format(ArchiveModuleCommand.MESSAGE_ARCHIVE_MODULE_SUCCESS, moduleToUnarchive);
        Model expectedModel = new ModelManager(new ModuleList(), model.getArchivedModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        expectedModel.unarchiveModule(moduleToUnarchive);
        showNoModule(expectedModel);
        assertCommandSuccess(unarchiveModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showArchivedModuleAtIndex(model, INDEX_FIRST_MODULE);
        Index outOfBoundIndex = INDEX_SECOND_MODULE;
        // ensures that outOfBoundIndex is still in bounds of archived module list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getArchivedModuleList().getModuleList().size());
        UnarchiveModuleCommand unarchiveModuleCommand = new UnarchiveModuleCommand(outOfBoundIndex);
        assertCommandFailure(unarchiveModuleCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UnarchiveModuleCommand unarchiveFirstCommand = new UnarchiveModuleCommand(INDEX_FIRST_MODULE);
        UnarchiveModuleCommand unarchiveSecondCommand = new UnarchiveModuleCommand(INDEX_SECOND_MODULE);

        // same object -> returns true
        assertTrue(unarchiveFirstCommand.equals(unarchiveFirstCommand));

        // same values -> returns true
        UnarchiveModuleCommand archiveFirstCommandCopy = new UnarchiveModuleCommand(INDEX_FIRST_MODULE);
        assertTrue(unarchiveFirstCommand.equals(archiveFirstCommandCopy));

        // different types -> returns false
        assertFalse(unarchiveFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unarchiveFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(unarchiveFirstCommand.equals(unarchiveSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoModule(Model model) {
        model.updateFilteredArchivedModuleList(p -> false);
        assertTrue(model.getFilteredArchivedModuleList().isEmpty());
    }
}
