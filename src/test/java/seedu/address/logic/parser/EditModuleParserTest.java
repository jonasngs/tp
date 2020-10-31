package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADEPOINT_4;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MC_4;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULENAME_ES2660;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOMLINK_ES2660;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE_POINT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULAR_CREDITS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.modulelistcommands.EditModuleCommand;
import seedu.address.logic.commands.modulelistcommands.EditModuleDescriptor;
import seedu.address.logic.parser.modulelistparsers.EditModuleParser;
import seedu.address.model.module.ModularCredits;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.grade.GradePoint;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditModuleDescriptorBuilder;

public class EditModuleParserTest {
    private static final int INDEX = 1;
    private static final String VALID_INPUT_EDIT_NAME = " " + INDEX
            + " " + PREFIX_NAME + VALID_MODULENAME_ES2660;

    private static final String VALID_INPUT_EDIT_TAG = " " + INDEX
            + " " + PREFIX_TAG + VALID_TAG_LECTURE;

    private static final String VALID_INPUT_EDIT_GRADEPOINT = " " + INDEX
            + " " + PREFIX_GRADE_POINT + VALID_GRADEPOINT_4;

    private static final String VALID_INPUT_EDIT_MC = " " + INDEX
            + " " + PREFIX_MODULAR_CREDITS + VALID_MC_4;

    private static final String VALID_INPUT_ALL_FIELDS = " " + INDEX
            + " " + PREFIX_NAME + VALID_MODULENAME_ES2660
            + " " + PREFIX_TAG + VALID_TAG_LECTURE
            + " " + PREFIX_MODULAR_CREDITS + VALID_GRADEPOINT_4
            + " " + PREFIX_MODULAR_CREDITS + VALID_MC_4;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditModuleCommand.MESSAGE_USAGE);

    private EditModuleParser parser = new EditModuleParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withName(VALID_MODULENAME_ES2660)
                .withGradePoint(VALID_GRADEPOINT_4).withMc(VALID_MC_4).withTags(VALID_TAG_LECTURE).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(Index.fromOneBased(1), descriptor);
        assertParseSuccess(parser, VALID_INPUT_ALL_FIELDS, expectedCommand);
    }

    @Test
    public void parse_onlyNameSpecified_success() {
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withName(VALID_MODULENAME_ES2660).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(Index.fromOneBased(1), descriptor);
        assertParseSuccess(parser, VALID_INPUT_EDIT_NAME, expectedCommand);
    }

    @Test
    public void parse_onlyTagSpecified_success() {
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withTags(VALID_TAG_LECTURE).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(Index.fromOneBased(1), descriptor);
        assertParseSuccess(parser, VALID_INPUT_EDIT_TAG, expectedCommand);
    }

    @Test
    public void parse_onlyGradePointSpecified_success() {
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withGradePoint(VALID_GRADEPOINT_4).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(Index.fromOneBased(1), descriptor);
        assertParseSuccess(parser, VALID_INPUT_EDIT_GRADEPOINT, expectedCommand);
    }

    @Test
    public void parse_onlyMcSpecified_success() {
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withMc(VALID_MC_4).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(Index.fromOneBased(1), descriptor);
        assertParseSuccess(parser, VALID_INPUT_EDIT_MC, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withName(VALID_MODULENAME_ES2660)
                .withZoomLink(VALID_ZOOMLINK_ES2660).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(Index.fromOneBased(1), descriptor);
        assertParseSuccess(parser, VALID_INPUT_EDIT_NAME, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        String invalidThenValid = " " + 1
                + " " + PREFIX_NAME + "@123"
                + " " + PREFIX_NAME + VALID_MODULENAME_ES2660;
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withName(VALID_MODULENAME_ES2660).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(Index.fromOneBased(1), descriptor);
        assertParseSuccess(parser, invalidThenValid, expectedCommand);
    }

    @Test
    public void parse_missingIndex_failure() {
        String missingIndex = " " + PREFIX_NAME + VALID_MODULENAME_ES2660;
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withName(VALID_MODULENAME_ES2660).build();
        assertParseFailure(parser, missingIndex, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingAllFields_failure() {
        String missingFields = " ";
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().build();
        assertParseFailure(parser, missingFields, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_failure() {
        //String invalidIndex = " " + 100
        //        + " " + PREFIX_NAME + VALID_MODULENAME_ES2660;
        //EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withName(VALID_MODULENAME_ES2660).build();
        //assertParseFailure(parser, invalidIndex, MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidName_failure() {
        String invalidName = " " + 1
                + " " + PREFIX_NAME + "@123";
        assertParseFailure(parser, invalidName, ModuleName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidTag_failure() {
        String invalidTag = " " + 1
                + " " + PREFIX_TAG + "@@@@";
        assertParseFailure(parser, invalidTag, Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidGradePoint_failure() {
        String invalidGradePoint = " " + 1
                + " " + PREFIX_GRADE_POINT + "10";
        assertParseFailure(parser, invalidGradePoint, GradePoint.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidMC_failure() {
        String invalidMC = " " + 1
                + " " + PREFIX_MODULAR_CREDITS + "aa";
        assertParseFailure(parser, invalidMC, ModularCredits.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_multipleInvalidFields_failure() {
        String invalidMC = " " + 1
                + " " + PREFIX_NAME + "2020CS"
                + " " + PREFIX_MODULAR_CREDITS + "aa";
        //shows error message of name because name takes priority over modular credits.
        assertParseFailure(parser, invalidMC, ModuleName.MESSAGE_CONSTRAINTS);
    }
}
