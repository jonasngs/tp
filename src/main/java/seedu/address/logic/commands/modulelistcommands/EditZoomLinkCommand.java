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
 * Edits an existing zoom link of a module in the module list.
 */
public class EditZoomLinkCommand extends Command {

    public static final String COMMAND_WORD = "editzoom";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the zoom link of a specific lesson in "
            + "the module identified by the index number used in the displayed module list. "
            + "The existing zoom link will be overwritten by the input zoom link.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NAME + "LESSON_NAME "
            + PREFIX_ZOOM_LINK + "EDITED_ZOOM_LINK\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "lecture "
            + PREFIX_ZOOM_LINK + "https://nus-sg.zoom.us/j/bascu637gdy";

    public static final String MESSAGE_EDIT_ZOOM_SUCCESS = "Edited Zoom: %1$s for lesson %2$s";
    public static final String MESSAGE_INVALID_LESSON = "This lesson does not exist in the module";
    public static final String MESSAGE_DUPLICATE_ZOOM = "This zoom link already exists in the module.";

    private final Logger logger = LogsCenter.getLogger(EditZoomLinkCommand.class);

    private final Index index;
    private final EditZoomDescriptor editZoomDescriptor;

    /**
     * Creates and initialises a new EditZoomLinkCommand object.
     *
     * @param index Index of the module containing the zoom link to be edited.
     * @param editZoomDescriptor EditZoomDescriptor object that stores details of the edited zoom link
     *                           and the module lesson that the zoom link to be edited belongs to.
     */
    public EditZoomLinkCommand(Index index, EditZoomDescriptor editZoomDescriptor) {
        requireAllNonNull(index, editZoomDescriptor);
        assert index.getZeroBased() >= 0 : "zero based index must be non-negative";
        this.index = index;
        this.editZoomDescriptor = editZoomDescriptor;
        logger.info("Editing the zoom link of the module at index " + index.getOneBased());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        Module moduleToEdit = lastShownList.get(index.getZeroBased());
        ZoomLink editedLink = editZoomDescriptor.getZoomLink();
        ModuleLesson lesson = editZoomDescriptor.getModuleLesson();

        if (moduleToEdit.containsLink(editedLink)) {
            throw new CommandException(MESSAGE_DUPLICATE_ZOOM);
        }

        if (!moduleToEdit.containsLesson(lesson)) {
            throw new CommandException(MESSAGE_INVALID_LESSON);
        }

        Module updatedModule = moduleToEdit.editZoomLink(lesson, editedLink);
        model.setModule(moduleToEdit, updatedModule);
        model.commitModuleList();
        logger.info("Zoom link has been edited");
        return new CommandResult(String.format(MESSAGE_EDIT_ZOOM_SUCCESS, editedLink, lesson));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditZoomLinkCommand)) {
            return false;
        }

        // state check
        EditZoomLinkCommand e = (EditZoomLinkCommand) other;
        return index.equals(e.index)
                && editZoomDescriptor.equals(e.editZoomDescriptor);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
