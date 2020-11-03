package seedu.address.logic.parser.schedulerparsers;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.schedulercommands.EditEventCommand;
import seedu.address.logic.commands.schedulercommands.EditEventCommand.EditEventDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

public class EditEventParser implements Parser<EditEventCommand> {
    @Override
    public EditEventCommand parse(String userInput) throws ParseException {
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(userInput, PREFIX_NAME, PREFIX_DATE);
        ArgumentMultimap argMultiMap = tokenizer.tokenize();
        Index index = ParserUtil.parseIndex(argMultiMap.getPreamble());
        EditEventDescriptor descriptor = new EditEventDescriptor();
        parseTagsForEdit(argMultiMap.getAllValues(PREFIX_TAG)).ifPresent(descriptor::setTags);
        if (argMultiMap.getValue(PREFIX_NAME).isPresent()) {
            descriptor.setName(ParserUtil.parseEventName(argMultiMap.getValue(PREFIX_NAME).get()));
        }
        if (argMultiMap.getValue(PREFIX_DATE).isPresent()) {
            descriptor.setTime(ParserUtil.parseEventTime(argMultiMap.getValue(PREFIX_DATE).get()));
        }
        if (!descriptor.isAnyFieldEdited()) {
            throw new ParseException(EditEventCommand.MESSAGE_NOT_EDITED);
        }
        return new EditEventCommand(index, descriptor);
    }


    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
