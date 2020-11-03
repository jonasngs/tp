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

public class UnarchiveModuleCommand extends Command {
    public static final String COMMAND_WORD = "unarchivemodule";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Un-Archives the module identified by the index number used in the displayed archived module list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNARCHIVE_MODULE_SUCCESS = "Un-Archived module: %1$s";

    private final Index targetIndex;

    public UnarchiveModuleCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredArchivedModuleList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        Module moduleToUnarchive = lastShownList.get(targetIndex.getZeroBased());
        model.unarchiveModule(moduleToUnarchive);
        model.commitModuleList();
        return new CommandResult(String.format(MESSAGE_UNARCHIVE_MODULE_SUCCESS, moduleToUnarchive));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteModuleCommand // instanceof handles nulls
                && targetIndex.equals(((UnarchiveModuleCommand) other).targetIndex)); // state check
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
