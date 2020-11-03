package seedu.address.logic.commands.modulelistcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

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

public class DeleteZoomLinkCommand extends Command {

    public static final String COMMAND_WORD = "deletezoom";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the zoom link from a module identified by the index number used "
            + "in the displayed module list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NAME + "MODULE_LESSON\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "lecture";

    public static final String MESSAGE_DELETE_ZOOM_SUCCESS = "Deleted Zoom from lesson %1$s in module %2$s";
    public static final String MESSAGE_INVALID_ZOOM_LINK = "The zoom link for the specified lesson does not exist";

    private final Logger logger = LogsCenter.getLogger(DeleteZoomLinkCommand.class);

    /** Index of the module in the module list which contains the zoom link to be deleted. */
    private final Index targetIndex;
    /** Module Lesson object which the zoom link to be deleted belongs to. */
    private final ModuleLesson lesson;

    /**
     * Creates and initialises a DeleteZoomLinkCommand object.
     *
     * @param targetIndex Index object encapsulating the index of the module in the
     *                    module list which contains the zoom link to be deleted.
     * @param lesson Module Lesson object which the zoom link to be deleted belongs to.
     */
    public DeleteZoomLinkCommand(Index targetIndex, ModuleLesson lesson) {
        requireAllNonNull(targetIndex, lesson);
        assert targetIndex.getZeroBased() >= 0 : "zero based index must be non-negative";
        logger.info("Deleting zoom link for module at index " + targetIndex.getOneBased());
        this.targetIndex = targetIndex;
        this.lesson = lesson;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        Module moduleToUpdate = lastShownList.get(targetIndex.getZeroBased());

        if (!moduleToUpdate.containsLesson(lesson)) {
            throw new CommandException(MESSAGE_INVALID_ZOOM_LINK);
        }

        Module updatedModule = moduleToUpdate.deleteZoomLink(lesson);
        model.setModule(moduleToUpdate, updatedModule);
        model.commitContactList();
        logger.info("Zoom Link has been deleted");
        return new CommandResult(String.format(MESSAGE_DELETE_ZOOM_SUCCESS,
                lesson, moduleToUpdate.getName()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteZoomLinkCommand)) {
            return false;
        }

        // state check
        DeleteZoomLinkCommand command = (DeleteZoomLinkCommand) other;
        return targetIndex.equals(command.targetIndex)
                && lesson.equals(command.lesson);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
