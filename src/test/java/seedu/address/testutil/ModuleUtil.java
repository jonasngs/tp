package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULAR_CREDITS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
// import static seedu.address.logic.parser.CliSyntax.PREFIX_ZOOM_LINK;

import seedu.address.logic.commands.modulelistcommands.AddModuleCommand;
import seedu.address.logic.commands.modulelistcommands.DeleteModuleCommand;
import seedu.address.model.module.Module;

/**
 * A utility class for Person.
 */
public class ModuleUtil {

    /**
     * Returns an add command string for adding the {@code module}.
     */
    public static String getAddCommand(Module module) {
        return AddModuleCommand.COMMAND_WORD + " " + getModuleDetails(module);
    }

    public static String getDeleteCommand(int index) {
        return DeleteModuleCommand.COMMAND_WORD + " " + index;
    }

    /**
     * Returns the part of command string for the given {@code module}'s details.
     */
    public static String getModuleDetails(Module module) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + module.getName().fullName + " ");
        // sb.append(PREFIX_ZOOM_LINK + module.getLink().value + " ");
        sb.append(PREFIX_MODULAR_CREDITS + module.getModularCredits().toString() + " ");
        module.getTags().stream().forEach(s -> sb.append(PREFIX_TAG + s.tagName + " "));
        return sb.toString();
    }

    ///**
    // * Returns the part of command string for the given {@code EditModuleDescriptor}'s details.
    // */
    // public static String getEditPersonDescriptorDetails(EditModuleCommand.EditModuleDescriptor descriptor) {
    //     StringBuilder sb = new StringBuilder();
    //     descriptor.getModuleName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
    //     descriptor.getZoomLink().ifPresent(zoomLink -> sb.append(PREFIX_EMAIL).append(zoomLink.value).append(" "));
    //     if (descriptor.getTags().isPresent()) {
    //         Set<Tag> tags = descriptor.getTags().get();
    //         if (tags.isEmpty()) {
    //            sb.append(PREFIX_TAG);
    //         } else {
    //             tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
    //         }
    //     }
    //     return sb.toString();
    // }
}
