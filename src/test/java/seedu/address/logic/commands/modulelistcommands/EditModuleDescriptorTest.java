package seedu.address.logic.commands.modulelistcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULENAME_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_UNGRADED_MODULE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOMLINKS_CS2103T;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditModuleDescriptorBuilder;

public class EditModuleDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditModuleDescriptor descriptorWithSameValues = new EditModuleDescriptor(DESC_CS2030);
        assertTrue(DESC_CS2030.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CS2030.equals(DESC_CS2030));

        // null -> returns false
        assertFalse(DESC_CS2030.equals(null));

        // different types -> returns false
        assertFalse(DESC_CS2030.equals(5));

        // different values -> returns false
        assertFalse(DESC_CS2030.equals(DESC_CS2103T));

        // different name -> returns false
        EditModuleDescriptor editedCS2030 = new EditModuleDescriptorBuilder(DESC_CS2030)
                    .withName(VALID_MODULENAME_CS2103T).build();
        assertFalse(DESC_CS2030.equals(editedCS2030));

        // different zoom link -> returns false
        editedCS2030 = new EditModuleDescriptorBuilder(DESC_CS2030)
                .withZoomLinks(VALID_ZOOMLINKS_CS2103T).build();
        assertFalse(DESC_CS2030.equals(editedCS2030));

        // different tags -> returns false
        editedCS2030 = new EditModuleDescriptorBuilder(DESC_CS2030).withTags(VALID_TAG_UNGRADED_MODULE).build();
        assertFalse(DESC_CS2030.equals(editedCS2030));
    }
}
