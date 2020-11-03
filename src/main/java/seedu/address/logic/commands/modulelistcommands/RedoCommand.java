package seedu.address.logic.commands.modulelistcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exceptions.VersionedListException;


public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Redoes the last undone user command.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_REDO_COMMAND_SUCCESS = "Undone command has been redone";
    public RedoCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.redo();
        } catch (VersionedListException versionedListException) {
            throw new CommandException(versionedListException.getMessage());
        }
        return new CommandResult(MESSAGE_REDO_COMMAND_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RedoCommand); // instanceof handles nulls
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
