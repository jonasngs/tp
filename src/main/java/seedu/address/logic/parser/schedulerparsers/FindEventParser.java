package seedu.address.logic.parser.schedulerparsers;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.schedulercommands.FindEventCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventContainsDatePredicate;
import seedu.address.model.event.EventContainsTagsPredicate;
import seedu.address.model.event.EventNameContainsKeyWordsPredicate;
import seedu.address.model.event.FindEventCriteria;
import seedu.address.model.tag.Tag;

public class FindEventParser implements Parser<FindEventCommand> {
    @Override
    public FindEventCommand parse(String userInput) throws ParseException {
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(userInput, PREFIX_NAME, PREFIX_DATE, PREFIX_TAG);
        ArgumentMultimap argMultiMap = tokenizer.tokenize();
        if (!atLeastOnePresent(argMultiMap, PREFIX_NAME, PREFIX_DATE, PREFIX_TAG)) {
            throw new ParseException("At least one search parameter needed!");
        }
        FindEventCriteria criteria = new FindEventCriteria();
        if (argMultiMap.getValue(PREFIX_NAME).isPresent()) {
            List<String> keywordsAsList = ParserUtil.parseSearchKeywords(argMultiMap.getValue(PREFIX_NAME).get());
            EventNameContainsKeyWordsPredicate namePredicate = new EventNameContainsKeyWordsPredicate(keywordsAsList);
            criteria.addPredicate(namePredicate);
        }
        if (argMultiMap.getValue(PREFIX_DATE).isPresent()) {
            LocalDateTime toSearch = ParserUtil.parseDate(argMultiMap.getValue(PREFIX_DATE).get());
            List<String> keywordsAsList = List.of(toSearch.toString());
            EventContainsDatePredicate datePredicate = new EventContainsDatePredicate(keywordsAsList);
            criteria.addPredicate(datePredicate);
        }
        if (argMultiMap.getValue(PREFIX_TAG).isPresent()) {
            List<String> tags = ParserUtil.parseSearchKeywords(argMultiMap.getValue(PREFIX_TAG).get());
            Set<Tag> eventTags = ParserUtil.parseTags(tags);
            EventContainsTagsPredicate tagsPredicate = new EventContainsTagsPredicate(eventTags);
            criteria.addPredicate(tagsPredicate);
        }
        return new FindEventCommand(criteria);
    }

    private boolean atLeastOnePresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
