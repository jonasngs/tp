package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Tokenizes arguments string of the form: {@code preamble <prefix>value <prefix>value ...}<br>
 *     e.g. {@code some preamble text t/ 11.00 t/12.00 k/ m/ July}  where prefixes are {@code t/ k/ m/}.<br>
 * 1. An argument's value can be an empty string e.g. the value of {@code k/} in the above example.<br>
 * 2. Leading and trailing whitespaces of an argument value will be discarded.<br>
 * 3. An argument may be repeated and all its values will be accumulated e.g. the value of {@code t/}
 *    in the above example.<br>
 */
public class ArgumentTokenizer {

    /** User input to be tokenized. Arguments string of the form: {@code preamble <prefix>value <prefix>value ...}*/
    private final String userInput;
    /** Array of prefixes which will be used to tokenize the user input. */
    private final Prefix[] prefixes;

    /**
     * Creates and initialises an ArgumentTokenizer to tokenize the user input.
     *
     * @param userInput User input.
     * @param prefixes Array of prefixes.
     */
    public ArgumentTokenizer(String userInput, Prefix... prefixes) {
        this.userInput = userInput;
        this.prefixes = prefixes;
    }

    /**
     * Tokenizes an arguments string and returns an {@code ArgumentMultimap} object that maps prefixes to their
     * respective argument values. Only the given prefixes will be recognized in the arguments string.
     *
     * @return ArgumentMultimap object that maps prefixes to their arguments
     */
    public ArgumentMultimap tokenize() throws ParseException {
        List<PrefixPosition> positions = findAllPrefixPositions();
        return extractArguments(positions);
    }

    /**
     * Finds all zero-based prefix positions in the given arguments string.
     *
     * @return List of zero-based prefix positions in the given arguments string
     */
    private List<PrefixPosition> findAllPrefixPositions() throws ParseException {
        return Arrays.stream(prefixes)
              .flatMap(prefix -> findPrefixPositions(userInput, prefix).stream())
              .collect(Collectors.toList());
        /*List<PrefixPosition> prefixPositions = new ArrayList<>();
        for (Prefix prefix : this.prefixes) {
            Optional<PrefixPosition> prefixPosition = findPrefixPosition(prefix);
            prefixPosition.ifPresent(position -> prefixPositions.add(position));
        }
        return prefixPositions;*/
    }
    private static List<PrefixPosition> findPrefixPositions(String argsString, Prefix prefix) {
        List<PrefixPosition> positions = new ArrayList<>();
        int prefixPosition = findPrefixPosition(argsString, prefix.getPrefix(), 0);
        while (prefixPosition != -1) {
            PrefixPosition extendedPrefix = new PrefixPosition(prefix, prefixPosition);
            positions.add(extendedPrefix);
            prefixPosition = findPrefixPosition(argsString, prefix.getPrefix(), prefixPosition);
        }
        return positions;
    }


    /**
     * Returns the index of the first occurrence of {@code prefix} in
     * {@code argsString} starting from index {@code fromIndex}. An occurrence
     * is valid if there is a whitespace before {@code prefix}. Returns -1 if no
     * such occurrence can be found.
     *
     * E.g if {@code argsString} = "e/hip/900", {@code prefix} = "p/" and
     * {@code fromIndex} = 0, this method returns -1 as there are no valid
     * occurrences of "p/" with whitespace before it. However, if
     * {@code argsString} = "e/hi p/900", {@code prefix} = "p/" and
     * {@code fromIndex} = 0, this method returns 5.
     */
    /*private Optional<PrefixPosition> findPrefixPosition(Prefix prefix) throws ParseException {
        // int prefixIndex = argsString.indexOf(" " + prefix, fromIndex);
        // return prefixIndex == -1 ? -1
        //        : prefixIndex + 1; // +1 as offset for whitespace
        String prefixSearch = " " + prefix.getPrefix();
        int prefixIndex = this.userInput.indexOf(prefixSearch);

        boolean hasMultipleSamePrefixes = hasMultipleSamePrefixes(prefix, prefixIndex + 1);
        if (hasMultipleSamePrefixes) {
            String error = "User input has multiple arguments for the same prefix";
            throw new ParseException(error);
        }

        return (prefixIndex == -1
                ? Optional.empty()
                : Optional.of(new PrefixPosition(prefix, prefixIndex + 1)));
    }*/

    private static int findPrefixPosition(String argsString, String prefix, int fromIndex) {
        int prefixIndex = argsString.indexOf(" " + prefix, fromIndex);
        return prefixIndex == -1 ? -1
                : prefixIndex + 1; // +1 as offset for whitespace
    }

    /**
     * Determines if the same prefix is used more than once.
     *
     * @param prefix Prefix.
     * @param currentPrefixIndex Index of the current prefix in the user input.
     * @return boolean.
     */
    private boolean hasMultipleSamePrefixes(Prefix prefix, int currentPrefixIndex) {
        String toSearch = this.userInput.substring(currentPrefixIndex);
        return toSearch.contains(" " + prefix.getPrefix());
    }

    /**
     * Extracts prefixes and their argument values, and returns an {@code ArgumentMultimap} object that maps the
     * extracted prefixes to their respective arguments. Prefixes are extracted based on their zero-based positions in
     * {@code argsString}.
     *
     * @param prefixPositions Zero-based positions of all prefixes in {@code argsString}
     * @return ArgumentMultimap object that maps prefixes to their arguments
     */
    private ArgumentMultimap extractArguments(List<PrefixPosition> prefixPositions) {

        // Sort by start position
        prefixPositions.sort((prefix1, prefix2) -> prefix1.getStartPosition() - prefix2.getStartPosition());

        // Insert a PrefixPosition to represent the preamble
        PrefixPosition preambleMarker = new PrefixPosition(new Prefix(""), 0);
        prefixPositions.add(0, preambleMarker);

        // Add a dummy PrefixPosition to represent the end of the string
        PrefixPosition endPositionMarker = new PrefixPosition(new Prefix(""), this.userInput.length());
        prefixPositions.add(endPositionMarker);

        // Map prefixes to their argument values (if any)
        ArgumentMultimap argMultimap = new ArgumentMultimap();
        for (int i = 0; i < prefixPositions.size() - 1; i++) {
            // Extract and store prefixes and their arguments
            Prefix prefix = prefixPositions.get(i).getPrefix();
            String prefixArgument = extractArgumentValue(this.userInput,
                    prefixPositions.get(i), prefixPositions.get(i + 1));
            argMultimap.put(prefix, prefixArgument);
        }
        return argMultimap;
    }

    /**
     * Returns the trimmed value of the argument in the arguments string specified by {@code currentPrefixPosition}.
     * The end position of the value is determined by {@code nextPrefixPosition}.
     */
    private String extractArgumentValue(String argsString,
                                        PrefixPosition currentPrefixPosition,
                                        PrefixPosition nextPrefixPosition) {
        Prefix prefix = currentPrefixPosition.getPrefix();

        int valueStartPos = currentPrefixPosition.getStartPosition() + prefix.getPrefix().length();
        String value = argsString.substring(valueStartPos, nextPrefixPosition.getStartPosition());

        return value.trim();
    }

    /**
     * Represents a prefix's position in an arguments string.
     */
    public static class PrefixPosition {
        private int startPosition;
        private final Prefix prefix;

        PrefixPosition(Prefix prefix, int startPosition) {
            this.prefix = prefix;
            this.startPosition = startPosition;
        }

        int getStartPosition() {
            return startPosition;
        }

        Prefix getPrefix() {
            return prefix;
        }
    }

}
