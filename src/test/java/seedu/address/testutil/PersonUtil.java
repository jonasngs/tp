package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

//import java.util.Set;
import seedu.address.logic.commands.modulelistcommands.AddModuleCommand;
import seedu.address.logic.commands.modulelistcommands.EditModuleDescriptor;
import seedu.address.model.contact.Contact;
//import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Contact person) {
        return AddModuleCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Contact person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().getName() + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditModuleDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditModuleDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getModuleName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        // descriptor.getZoomLink().ifPresent(zoomLink -> sb.append(PREFIX_EMAIL).append(zoomLink.value).append(" "));
        //if (descriptor.getTags().isPresent()) {
        //    Set<Tag> tags = descriptor.getTags().get();
        //    if (tags.isEmpty()) {
        //       sb.append(PREFIX_TAG);
        //    } else {
        //        tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
        //    }
        //}
        return sb.toString();
    }
}
