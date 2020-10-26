package seedu.address.storage;

/*import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedModule.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;*/
import static seedu.address.testutil.TypicalContacts.BENSON;

//import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;*/

public class JsonAdaptedModuleTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    /*@Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedModule person = new JsonAdaptedModule(BENSON);
        assertEquals(BENSON, person.toModelType());
    }*/

    /*@Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedModule person =
                new JsonAdaptedModule(INVALID_NAME, VALID_EMAIL, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }*/

    /*@Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedModule person = new JsonAdaptedModule(null, VALID_EMAIL, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }*/

    /*@Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedModule person =
                new JsonAdaptedModule(VALID_NAME, INVALID_EMAIL, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }*/

    /*@Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedModule person = new JsonAdaptedModule(VALID_NAME, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }*/

    /*@Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedModule person =
                new JsonAdaptedModule(VALID_NAME, VALID_EMAIL, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }*/

}
