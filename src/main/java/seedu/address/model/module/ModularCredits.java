package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class ModularCredits {
    public static final String MESSAGE_CONSTRAINTS =
            "Modular Credits should be in the form of an number and cannot be less than zero.";
    public static final double DEFAULT_MODULAR_CREDITS = 4.0;
    public final double moduleCredits;
    /**
     * Constructs a {@code ModularCredits}.
     *
     * @param modularCredits A number in string form
     */
    public ModularCredits(double modularCredits) {
        requireNonNull(modularCredits);
        checkArgument(isValidModularCredits(Double.toString(modularCredits)), MESSAGE_CONSTRAINTS);
        this.moduleCredits = modularCredits;
    }

    /**
     * Constructs a {@code ModularCredits} with the default modular credits value.
     */
    public ModularCredits() {
        moduleCredits = DEFAULT_MODULAR_CREDITS;
    }

    /**
     * Returns true if a given double is a valid modular credits value.
     */
    public static boolean isValidModularCredits(String test) {
        double convertedModuleCredits;
        try {
            convertedModuleCredits = Double.parseDouble(test);
            return convertedModuleCredits >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return Double.toString(moduleCredits);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModularCredits // instanceof handles nulls
                && moduleCredits == (((ModularCredits) other).moduleCredits)); // state check
    }

}
