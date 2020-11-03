package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactName;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Telegram;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Contact}.
 */
public class JsonAdaptedContact {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Contact's %s field is missing!";

    private final String name;
    private final String email;
    private final String telegram;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final boolean isImportant;

    /**
     * Constructs a {@code JsonAdaptedContact} with the given contact details.
     */
    @JsonCreator
    public JsonAdaptedContact(@JsonProperty("name") String name,
            @JsonProperty("email") String email,
            @JsonProperty("telegram") String telegram,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("isImportant") boolean isImportant) {
        this.name = name;
        this.email = email;
        this.telegram = telegram;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.isImportant = isImportant;
    }

    /**
     * Converts a given {@code Contact} into this class for Jackson use.
     */
    public JsonAdaptedContact(Contact source) {
        name = source.getName().getName();
        email = source.getEmail().value;

        if (source.getTelegram().isPresent()) {
            telegram = source.getTelegram().get().telegramUsername;
        } else {
            telegram = null;
        }

        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        isImportant = source.isImportant();
    }

    /**
     * Converts this Jackson-friendly adapted contact object into the model's {@code Contact} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted contact.
     */
    public Contact toModelType() throws IllegalValueException {
        final List<Tag> contactTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            contactTags.add(tag.toModelType());
        }
        final Set<Tag> modelTags = new HashSet<>(contactTags);

        if (name == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, ContactName.class.getSimpleName()));
        }
        if (!ContactName.isValidName(name)) {
            throw new IllegalValueException(ContactName.MESSAGE_CONSTRAINTS);
        }
        final ContactName modelName = new ContactName(name);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (telegram == null) {
            return new Contact(modelName, modelEmail, modelTags, isImportant);
        }
        if (!Telegram.isValidTelegram(telegram)) {
            throw new IllegalValueException(Telegram.MESSAGE_CONSTRAINTS);
        }

        final Telegram modelTelegram = new Telegram(telegram);

        return new Contact(modelName, modelEmail, modelTelegram, modelTags, isImportant);
    }

}
