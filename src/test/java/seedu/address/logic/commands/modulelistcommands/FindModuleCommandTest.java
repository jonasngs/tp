package seedu.address.logic.commands.modulelistcommands;

// import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.commons.core.Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW;
// import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
// import static seedu.address.testutil.TypicalPersons.CARL;
// import static seedu.address.testutil.TypicalPersons.ELLE;
// import static seedu.address.testutil.TypicalPersons.FIONA;
// import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

//import java.util.Arrays;
//import java.util.Collections;
//import seedu.address.model.contact.NameContainsKeywordsPredicate;
//import org.junit.jupiter.api.Test;

// import seedu.address.model.Model;
// import seedu.address.model.ModelManager;
// import seedu.address.model.UserPrefs;
//import seedu.address.logic.commands.FindCommand;


//not implemented yet
/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindModuleCommandTest {
    // private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    // private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    /*
    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindModuleCommand findFirstCommand = new FindModuleCommand(firstPredicate);
        FindModuleCommand findSecondCommand = new FindModuleCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindModuleCommand findFirstCommandCopy = new FindModuleCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noModuleFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED_OVERVIEW, 0);
        //NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        //FindModuleCommand command = new FindModuleCommand(predicate);
        // expectedModel.updateFilteredPersonList(predicate);
        // assertCommandSuccess(command, model, expectedMessage, expectedModel);
        // assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindModuleCommand command = new FindModuleCommand(predicate);
        // expectedModel.updateFilteredPersonList(predicate);
        // assertCommandSuccess(command, model, expectedMessage, expectedModel);
        // assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }
    */
    ///**
     //* Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     //*/
    /*private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }*/
}
