package seedu.address.logic.commands.modulelistcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModuleList;

/**
 * Clears the module list.
 */
public class ClearModuleCommand extends Command {

    public static final String COMMAND_WORD = "clearmodule";
    public static final String MESSAGE_SUCCESS = "Module list has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setModuleList(new ModuleList());
        model.commitModuleList();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
