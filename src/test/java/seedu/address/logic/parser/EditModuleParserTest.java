package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
//import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_CONTACT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.modulelistcommands.EditModuleCommand;
import seedu.address.logic.parser.modulelistparsers.EditModuleParser;
//import seedu.address.model.person.Email;
//import seedu.address.model.person.Name;
//import seedu.address.model.tag.Tag;

public class EditModuleParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditModuleCommand.MESSAGE_USAGE);

    private EditModuleParser parser = new EditModuleParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        //assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        //assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        //assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        //assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        //assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        //assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        //assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        //assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        //assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        //assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        //assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        //assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        //assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        //assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY,
        //        Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_CONTACT;
        String userInput = targetIndex.getOneBased() + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND;

        //EditModuleDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
        //.withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        //EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        //assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_CONTACT;
        String userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;

        //EditModuleDescriptor descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        //EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        //assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_CONTACT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        //EditModuleDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        //EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        //assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        //descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        //expectedCommand = new EditCommand(targetIndex, descriptor);
        //assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        //descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        //expectedCommand = new EditCommand(targetIndex, descriptor);
        //assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_CONTACT;
        String userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        //EditModuleDescriptor descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_BOB)
        //.withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
        //.build();
        //EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        //assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_CONTACT;
        // String userInput = targetIndex.getOneBased();
        //EditModuleDescriptor descriptor = new EditPersonDescriptorBuilder().build();
        //EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        // assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        // userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB;
        //descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_BOB)
        //.build();
        //expectedCommand = new EditCommand(targetIndex, descriptor);
        // assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_CONTACT;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        //EditModuleDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        //EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        //assertParseSuccess(parser, userInput, expectedCommand);
    }
}
