package seedu.address.logic.commands.modulelistcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Lists all modules in the module list to the user.
 */
public class ViewModuleCommand extends Command {

    public static final String COMMAND_WORD = "viewmodule";

    public static final String MESSAGE_SUCCESS = "Module details have been displayed successfully!\n"
            + "%1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views a module in the module list. "
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + "1";

    private Index index;

    /**
     * Creates a ViewCommand to view the specified {@code Module}
     */
    public ViewModuleCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Module> lastShownList = model.getFilteredModuleList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        Module moduleToView = lastShownList.get(index.getZeroBased());

        return new CommandResult(String.format(MESSAGE_SUCCESS, moduleToView));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
