package seedu.address.logic.commands.modulelistcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ZOOM_LINK;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleLesson;
import seedu.address.model.module.ZoomLink;

/**
 * Adds a zoom link to a module.
 */
public class AddZoomLinkCommand extends Command {

    public static final String COMMAND_WORD = "addzoom";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a zoom link for a specific lesson to the module. "
            + "Parameters: " + "INDEX (must be a positive integer) "
            + PREFIX_NAME + "MODULE_LESSON "
            + PREFIX_ZOOM_LINK + "ZOOM_LINK "
            + "Example: " + COMMAND_WORD + " "
            + "1 " + PREFIX_NAME + "lecture "
            + PREFIX_ZOOM_LINK + "https://nus-sg.zoom.us/j/uasoihd637bf";

    public static final String MESSAGE_ADD_ZOOM_SUCCESS = "Added Zoom Link: %1$s";

    private final Logger logger = LogsCenter.getLogger(AddZoomLinkCommand.class);

    private final Index targetIndex;
    private final AddZoomDescriptor descriptor;

    /**
     * Creates and initialises a new AddZoomLinkCommand object.
     *
     * @param targetIndex Index object representing the index of the module in the module list.
     * @param descriptor Encapsulates details of the zoom link to be added.
     */
    public AddZoomLinkCommand(Index targetIndex, AddZoomDescriptor descriptor) {
        requireAllNonNull(targetIndex, descriptor);
        logger.info("Executing command to add a zoom link to a module");
        this.targetIndex = targetIndex;
        this.descriptor = new AddZoomDescriptor(descriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        ZoomLink zoomLink = descriptor.getZoomLink();
        ModuleLesson moduleLesson = descriptor.getModuleLesson();

        Module moduleToAddLink = lastShownList.get(targetIndex.getZeroBased());
        Module updatedModule = moduleToAddLink.addZoomLink(moduleLesson, zoomLink);

        model.setModule(moduleToAddLink, updatedModule);
        logger.info("Zoom link added to the module");
        model.commitModuleList();
        return new CommandResult(String.format(MESSAGE_ADD_ZOOM_SUCCESS, zoomLink));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddZoomLinkCommand)) {
            return false;
        }

        // state check
        AddZoomLinkCommand command = (AddZoomLinkCommand) other;
        return targetIndex.equals(command.targetIndex)
                && descriptor.equals(command.descriptor);
    }

    /**
     * Indicates if the application session has ended.
     *
     * @return False since the sessions has not been terminated.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
