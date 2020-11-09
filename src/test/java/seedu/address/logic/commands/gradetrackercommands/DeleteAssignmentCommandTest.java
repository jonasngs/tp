package seedu.address.logic.commands.gradetrackercommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_NAME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_NAME_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_PERCENTAGE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_PERCENTAGE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_RESULT_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_RESULT_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULENAME_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULENAME_ES2660;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.model.ContactList;
import seedu.address.model.EventList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.grade.Assignment;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.ModuleListBuilder;
import seedu.address.testutil.gradetracker.AssignmentBuilder;

public class DeleteAssignmentCommandTest {
    private static final Index INDEX_FIRST_ASSIGNMENT = Index.fromOneBased(1);
    private static final Assignment assignmentQuiz1 = new AssignmentBuilder()
            .withAssignmentName(VALID_ASSIGNMENT_NAME_1)
            .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_1)
            .withAssignmentResult(VALID_ASSIGNMENT_RESULT_1).build();

    private static final Assignment assignmentOralPresentation2 = new AssignmentBuilder()
            .withAssignmentName(VALID_ASSIGNMENT_NAME_2)
            .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_2)
            .withAssignmentResult(VALID_ASSIGNMENT_RESULT_2).build();

    @Test
    public void constructor_nullModuleToDelete_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteAssignmentCommand(INDEX_FIRST_ASSIGNMENT,
                null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        Model model = new ModelManager(getTypicalModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());

        Module moduleToUpdate = model.getFilteredModuleList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());

        ModuleName moduleToAddName = moduleToUpdate.getName();
        assertThrows(NullPointerException.class, () -> new DeleteAssignmentCommand(null, moduleToAddName));
    }

    @Test
    public void executeValidAssignmentDeleteSuccess() {
        ModuleList moduleList = new ModuleListBuilder().build();
        Module module = new ModuleBuilder().withName(VALID_MODULENAME_CS2103T)
                .withAssignment(VALID_ASSIGNMENT_NAME_1, VALID_ASSIGNMENT_PERCENTAGE_1, VALID_ASSIGNMENT_RESULT_1)
                .build();
        module.addAssignment(assignmentOralPresentation2);
        moduleList.addModule(module);
        Model model = new ModelManager(moduleList, new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());

        Module expectedModule = new ModuleBuilder().withName(VALID_MODULENAME_CS2103T).build();
        Model expectedModel = new ModelManager(model.getModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());

        DeleteAssignmentCommand command = new DeleteAssignmentCommand(INDEX_FIRST_ASSIGNMENT, module.getName());

        String expectedMessage = String.format(DeleteAssignmentCommand.MESSAGE_DELETE_ASSIGNMENT_SUCCESS,
                assignmentQuiz1, expectedModule.getName());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        Model model = new ModelManager(getTypicalModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());

        Module moduleToUpdate = model.getFilteredModuleList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());

        ModuleName moduleToDeleteName = moduleToUpdate.getName();
        DeleteAssignmentCommand command = new DeleteAssignmentCommand(INDEX_FIRST_ASSIGNMENT, moduleToDeleteName);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_invalidModuleName_throwsCommandException() {
        ModuleList expectedModuleList = new ModuleListBuilder().build();
        Module expectedModule = new ModuleBuilder().withName(VALID_MODULENAME_CS2103T)
                .withAssignment(VALID_ASSIGNMENT_NAME_1, VALID_ASSIGNMENT_PERCENTAGE_1, VALID_ASSIGNMENT_RESULT_1)
                .build();
        expectedModuleList.addModule(expectedModule);
        Model expectedModel = new ModelManager(expectedModuleList, new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());

        ModuleName moduleNameNotInList = new ModuleName(VALID_MODULENAME_ES2660);
        DeleteAssignmentCommand command = new DeleteAssignmentCommand(INDEX_FIRST_ASSIGNMENT, moduleNameNotInList);

        assertCommandFailure(command, expectedModel, DeleteAssignmentCommand.MESSAGE_ASSIGNMENT_NOT_DELETED);
    }

    @Test
    public void equals() {
        Model model = new ModelManager(getTypicalModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());

        Module moduleToUpdate = model.getFilteredModuleList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        moduleToUpdate.addAssignment(assignmentQuiz1);

        ModuleName moduleToDeleteName = moduleToUpdate.getName();

        final DeleteAssignmentCommand standardCommand = new DeleteAssignmentCommand(INDEX_FIRST_ASSIGNMENT,
                moduleToDeleteName);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different type -> returns false
        assertNotEquals(8, standardCommand);

        // different command types -> returns false
        assertNotEquals(standardCommand, new ExitCommand());

        Module secondModule = model.getFilteredModuleList().get(INDEX_SECOND_MODULE.getZeroBased());
        ModuleName differentModuleName = secondModule.getName();
        // same index, different module name -> returns false
        assertNotEquals(standardCommand, new DeleteAssignmentCommand(INDEX_FIRST_ASSIGNMENT,
                differentModuleName));

        // same module name , different index -> returns false
        assertNotEquals(standardCommand, new DeleteAssignmentCommand(INDEX_SECOND_MODULE, moduleToDeleteName));
    }
}
