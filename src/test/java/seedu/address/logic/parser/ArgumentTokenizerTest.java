package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULAR_CREDITS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ZOOM_LINK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class ArgumentTokenizerTest {

    private final Prefix unknownPrefix = new Prefix("--u");
    private final Prefix pSlash = new Prefix("p/");
    private final Prefix dashT = new Prefix("-t");
    private final Prefix hatQ = new Prefix("^Q");

    @Test
    public void tokenize_emptyArgsString_noValues() throws ParseException {
        String argsString = "  ";
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(argsString, PREFIX_NAME);
        ArgumentMultimap argMultimap = tokenizer.tokenize();
        assertPreambleEmpty(argMultimap);
    }

    @Test
    public void tokenize_missingArgument() throws ParseException {
        String argsString = "add n/";
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(argsString, PREFIX_NAME);
        ArgumentMultimap argMultimap = tokenizer.tokenize();
        assertArgumentAbsent(argMultimap, PREFIX_NAME);
    }

    @Test
    public void tokenize_validArgsString_preamblePresent() throws ParseException {
        String argsString = "hello n/";
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(argsString, PREFIX_NAME);
        ArgumentMultimap argMultimap = tokenizer.tokenize();
        assertPreamblePresent(argMultimap, "hello");
    }

    @Test
    public void tokenize_validArgument() throws ParseException {
        String argsString = "add n/matthias";
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(argsString, PREFIX_NAME);
        ArgumentMultimap argMultimap = tokenizer.tokenize();
        assertArgumentPresent(argMultimap, PREFIX_NAME, "matthias");
    }

    @Test
    public void tokenize_validArgument_multiple() throws ParseException {
        String argsString = "add n/matthias testing";
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(argsString, PREFIX_NAME);
        ArgumentMultimap argMultimap = tokenizer.tokenize();
        assertArgumentPresent(argMultimap, PREFIX_NAME, "matthias testing");
    }

    private void assertPreamblePresent(ArgumentMultimap argMultimap, String expectedPreamble) {
        assertEquals(expectedPreamble, argMultimap.getPreamble());
    }

    private void assertPreambleEmpty(ArgumentMultimap argMultimap) {
        assertTrue(argMultimap.getPreamble().isEmpty());
    }

    /**
     * Asserts all the arguments in {@code argMultimap} with {@code prefix} match the {@code expectedValues}
     * and only the last value is returned upon calling {@code ArgumentMultimap#getValue(Prefix)}.
     */
    private void assertArgumentPresent(ArgumentMultimap argMultimap, Prefix prefix, String... expectedValues) {

        // Verify the last value is returned
        assertEquals(expectedValues[expectedValues.length - 1], argMultimap.getValue(prefix).get());

        // Verify the number of values returned is as expected
        assertEquals(expectedValues.length, argMultimap.getAllValues(prefix).size());

        // Verify all values returned are as expected and in order
        for (int i = 0; i < expectedValues.length; i++) {
            assertEquals(expectedValues[i], argMultimap.getAllValues(prefix).get(i));
        }
    }

    private void assertArgumentAbsent(ArgumentMultimap argMultimap, Prefix prefix) {
        if (argMultimap.getValue(prefix).isPresent()) {
            assertTrue(argMultimap.getValue(prefix).get().equals(""));
        } else {
            assertFalse(argMultimap.getValue(prefix).isPresent());
        }
    }

    @Test
    public void tokenize_noPrefixes_allTakenAsPreamble() throws ParseException {
        String argsString = "  some random stringn/ tag with leading and trailing spaces ";
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(argsString, PREFIX_NAME);
        ArgumentMultimap argMultimap = tokenizer.tokenize();

        // Same string expected as preamble, but leading/trailing spaces should be trimmed
        assertPreamblePresent(argMultimap, argsString.trim());
    }

    @Test
    public void tokenize_oneArgument() throws ParseException {
        // Preamble present
        String argString = "addmodule n/hello";
        ArgumentTokenizer tokenizer1 = new ArgumentTokenizer(argString, PREFIX_NAME);
        ArgumentMultimap map1 = tokenizer1.tokenize();
        assertPreamblePresent(map1, "addmodule");
        assertArgumentPresent(map1, PREFIX_NAME, "hello");

        // No preamble
        argString = " n/bye";
        ArgumentTokenizer tokenizer2 = new ArgumentTokenizer(argString, PREFIX_NAME);
        ArgumentMultimap map2 = tokenizer2.tokenize();
        assertPreambleEmpty(map2);
        assertArgumentPresent(map2, PREFIX_NAME, "bye");
    }

    @Test
    public void tokenize_multipleArguments() throws ParseException {
        // Only two arguments are present
        String argsString = "SomePreambleString n/CS2103T z/www.example.com";
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(argsString, PREFIX_NAME, PREFIX_ZOOM_LINK);
        ArgumentMultimap argMultimap = tokenizer.tokenize();
        // assertArgumentPresent(argMultimap, PREFIX_NAME, "CS2103T");
        assertArgumentPresent(argMultimap, PREFIX_ZOOM_LINK, "www.example.com");
        assertArgumentAbsent(argMultimap, PREFIX_ASSIGNMENT_NAME);

        // All three arguments are present
        argsString = "SomePreambleString n/CS2103T z/www.example.com mc/4.0";
        ArgumentTokenizer tokenizer2 = new ArgumentTokenizer(argsString, PREFIX_NAME, PREFIX_ZOOM_LINK,
                PREFIX_MODULAR_CREDITS);
        ArgumentMultimap argMultimap2 = tokenizer2.tokenize();
        assertArgumentPresent(argMultimap2, PREFIX_NAME, "CS2103T");
        assertArgumentPresent(argMultimap2, PREFIX_ZOOM_LINK, "www.example.com");
        assertArgumentPresent(argMultimap2, PREFIX_MODULAR_CREDITS, "4.0");

        /* Also covers: testing for prefixes not specified as a prefix */

        // Prefixes not previously given to the tokenizer should not return any values
        argsString = "addmodule n/test l/www.example.com";
        ArgumentTokenizer tokenizer3 = new ArgumentTokenizer(argsString, PREFIX_NAME);
        ArgumentMultimap argMultiMap3 = tokenizer3.tokenize();
        assertArgumentAbsent(argMultiMap3, PREFIX_ZOOM_LINK); // prefix not added into tokenizer should be absent
        // assertArgumentPresent(argMultiMap3, PREFIX_NAME, "test z/www.example.com");
    }

    @Test
    public void tokenize_multipleArgumentsWithRepeats() throws ParseException {
        // Two arguments repeated
        String argsString = "addmodule n/test1 n/test2";
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(argsString, PREFIX_NAME);
        ArgumentMultimap argMultiMap = tokenizer.tokenize();
        assertArgumentPresent(argMultiMap, PREFIX_NAME, "test1", "test2");
        assertPreamblePresent(argMultiMap, "addmodule");
    }

    @Test
    public void tokenize_multipleArgumentsJoined() throws ParseException {
        String argsString = "addmodule n/matthiasn/jonas";
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(argsString, PREFIX_NAME);
        ArgumentMultimap argMultiMap = tokenizer.tokenize();
        assertPreamblePresent(argMultiMap, "addmodule");
        assertArgumentPresent(argMultiMap, PREFIX_NAME, "matthiasn/jonas");
    }

    @Test
    public void equalsMethod() {
        Prefix aaa = new Prefix("aaa");

        assertEquals(aaa, aaa);
        assertEquals(aaa, new Prefix("aaa"));

        assertNotEquals(aaa, "aaa");
        assertNotEquals(aaa, new Prefix("aab"));
    }

}
