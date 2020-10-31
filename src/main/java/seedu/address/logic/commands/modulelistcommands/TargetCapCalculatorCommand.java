package seedu.address.logic.commands.modulelistcommands;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TARGET_CAP;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.modulelistcommands.modulelistexceptions.CapCalculationException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Determines CAP needed for ongoing modules for user to reach target CAP
 */
public class TargetCapCalculatorCommand extends Command {
    public static final String COMMAND_WORD = "targetcap";
    public static final String MESSAGE_CONSTRAINTS =
            "Unable to calculate CAP details because you do not have any completed mods";
    public static final String MESSAGE_NO_PLANNED_MODULAR_CREDITS_CONSTRAINT =
            "Unable to calculate CAP details because you do not have any planned modular credits";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Determines CAP needed for ongoing modules for user to reach specified target CAP. "
            + "Parameters: "
            + PREFIX_TARGET_CAP + "Target CAP"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TARGET_CAP + "4.5";
    private final double targetCap;
    /*private final double plannedCredits;
    private final double completedCredits;
    private final double totalCredits;*/

    /**
     * Creates an AddCommand to add the specified {@code Module}
     */
    public TargetCapCalculatorCommand(double targetCap) {
        requireNonNull(targetCap);
        this.targetCap = targetCap;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        double capNeeded;
        try {
            capNeeded = calculateCapNeeded(model.getFilteredModuleList());
        } catch (CapCalculationException capCalculationException) {
            throw new CommandException(capCalculationException.getMessage());
        }
        return new CommandResult(createSuccessMessage(capNeeded,
                getPlannedCredits(model.getFilteredModuleList())));
    }

    /**
     * Calculates CAP needed for current ongoing modules to reach target CAP.
     * @param modules List of modules
     * @return
     */
    public double calculateCapNeeded(List<Module> modules) throws CapCalculationException {
        double currentCap;
        try {
            currentCap = CalculateCapCommand.calculateCap(modules);
        } catch (CapCalculationException capCalculationException) {
            throw new CapCalculationException(MESSAGE_CONSTRAINTS);
        }
        double plannedCredits = getPlannedCredits(modules);
        if (plannedCredits == 0) {
            throw new CapCalculationException(MESSAGE_NO_PLANNED_MODULAR_CREDITS_CONSTRAINT);
        }
        double completedCredits = getCompletedCredits(modules);
        double totalCredits = plannedCredits + completedCredits;
        double capNeeded = (targetCap - (currentCap * (completedCredits / totalCredits)))
                * (totalCredits / plannedCredits);
        return capNeeded;
    }

    private double getPlannedCredits(List<Module> modules) {
        double plannedCredits = 0;
        for (Module m : modules) {
            if (!m.isCompleted()) {
                plannedCredits += m.getModularCredits().moduleCredits;
            }
        }
        return plannedCredits;
    }

    private double getCompletedCredits(List<Module> modules) {
        double completedCredits = 0;
        for (Module m : modules) {
            if (m.isCompleted()) {
                completedCredits += m.getModularCredits().moduleCredits;
            }
        }
        return completedCredits;
    }

    /**
     * Creates a success message when the CAP needed has been successfully calculated.
     *
     * @param capNeeded Double containing the calculated CAP needed
     * @return String containing the success message.
     */
    public String createSuccessMessage(double capNeeded, double plannedCredits) {
        //String message = "Your CAP for completed mods has been successfully calculated : " + Double.toString(cap);
        String message;
        if (capNeeded > 5) {
            message = "It is impossible to attain the targeted CAP with the amount of planned credits";
        } else {
            message = "To attain the target CAP of " + Double.toString(targetCap) + "\n"
                    + "You will need to achieve a CAP of at least " + Double.toString(capNeeded)
                    + " for your ongoing modules worth a total of " + Double.toString(plannedCredits) + " credits";
        }
        return message;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddModuleCommand // instanceof handles nulls
                && targetCap == (((TargetCapCalculatorCommand) other).targetCap));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
