package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.modulelistcommands.ClearModuleCommand;
import seedu.address.logic.commands.modulelistcommands.DeleteModuleCommand;
import seedu.address.logic.commands.modulelistcommands.ListModuleCommand;
// import seedu.address.logic.commands.modulelistcommands.AddModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.NameContainsKeywordsPredicate;
// import seedu.address.model.module.Module;
import seedu.address.testutil.ContactBuilder;
// import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.ModuleUtil;


public class ModuleListParserTest {

    private final ModuleListParser parser = new ModuleListParser();

    /*
    @Test
    public void parseCommand_add() throws Exception {
        Module module = new ModuleBuilder().build();
        Command command = parser.parseCommand(ModuleUtil.getAddCommand(module));
        assertEquals(new AddModuleCommand(module), command);
    }
     */

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearModuleCommand.COMMAND_WORD) instanceof ClearModuleCommand);
        assertTrue(parser.parseCommand(ClearModuleCommand.COMMAND_WORD + " 3") instanceof ClearModuleCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        Command command = parser.parseCommand(
                ModuleUtil.getDeleteCommand(INDEX_FIRST_PERSON.getOneBased()));
        assertEquals(new DeleteModuleCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Contact person = new ContactBuilder().build();
        //EditModuleDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        //EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
        //        + INDEX_FIRST_PERSON.getOneBased() + " " + ModuleUtil.getEditPersonDescriptorDetails(descriptor));
        //assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListModuleCommand.COMMAND_WORD) instanceof ListModuleCommand);
        assertTrue(parser.parseCommand(ListModuleCommand.COMMAND_WORD + " 3") instanceof ListModuleCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
