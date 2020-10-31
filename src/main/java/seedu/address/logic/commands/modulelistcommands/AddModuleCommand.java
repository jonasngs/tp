package seedu.address.logic.commands.modulelistcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ZOOM_LINK;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Adds a module to the module list.
 */
public class AddModuleCommand extends Command {

    public static final String COMMAND_WORD = "addmodule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a module to the module list. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "CS2100 "
            + PREFIX_ZOOM_LINK + "www.zoom.com";

    public static final String MESSAGE_SUCCESS = "New module added: %1$s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in the module list";

    private final Module module;

    /**
     * Creates an AddCommand to add the specified {@code Module}
     */
    public AddModuleCommand(Module module) {
        requireNonNull(module);
        this.module = module;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasModule(module) || model.hasArchivedModule(module)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.addModule(module);
        model.commitModuleList();
        return new CommandResult(String.format(MESSAGE_SUCCESS, module));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof AddModuleCommand) {
            AddModuleCommand temp = (AddModuleCommand) other;
            Module temp2 = temp.module;
            return this.module.getName().equals(temp2.getName())
                    && this.module.getAllLinks().equals(temp2.getAllLinks())
                    && this.module.getModularCredits().equals(temp2.getModularCredits());
        } else {
            return false;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return COMMAND_WORD + " " + module.toString();
    }
}
