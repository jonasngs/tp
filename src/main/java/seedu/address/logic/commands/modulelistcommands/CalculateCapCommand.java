package seedu.address.logic.commands.modulelistcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.modulelistcommands.modulelistexceptions.CapCalculationException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

public class CalculateCapCommand extends Command {
    public static final String COMMAND_WORD = "calculatecap";
    public static final String MESSAGE_CONSTRAINTS = "You do not have any completed modules for CAP to be calculated";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Calculates the user's CAP "
            + "Example: " + COMMAND_WORD;
    private double cap;
    /**
     * Creates and initialises a new CalculateCapCommand object.
     */
    public CalculateCapCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredUnarchivedModuleList();
        lastShownList.addAll(model.getFilteredArchivedModuleList());
        try {
            cap = calculateCap(lastShownList);
        } catch (CapCalculationException capCalculationException) {
            throw new CommandException(capCalculationException.getMessage());
        }
        return new CommandResult(createSuccessMessage(cap));
    }

    /**
     * Creates a success message when the CAP has been successfully calculated.
     *
     * @param cap Double containing the calculated CAP
     * @return String containing the success message.
     */
    public String createSuccessMessage(double cap) {
        String message = "Your CAP for completed mods has been successfully calculated : " + Double.toString(cap);
        return message;
    }

    /**
     * Calculates CAP score with a given list of modules.
     * @param modules List of modules
     */
    public static double calculateCap(List<Module> modules) throws CapCalculationException {
        double totalPoints = 0.0;
        double totalMC = 0.0;
        for (Module m : modules) {
            if (m.isCompleted() && m.hasGradePoint()) {
                double modularCredits = m.getModularCredits().moduleCredits;
                double gradePoints = m.getGradeTracker().getGradePoint().get().gradePoint;
                totalMC += modularCredits;
                totalPoints += gradePoints * modularCredits;
            }
        }
        if (totalMC == 0) {
            throw new CapCalculationException(MESSAGE_CONSTRAINTS);
        }
        return totalPoints / totalMC;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddModuleCommand); // instanceof handles nulls
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
