package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.module.Module;
import seedu.address.model.module.UniqueModuleList;

/**
 * Wraps all data at the module-list level
 * Duplicates are not allowed (by .isSameModule comparison)
 */
public class ModuleList implements ReadOnlyModuleList {

    private final UniqueModuleList modules;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        modules = new UniqueModuleList();
    }

    public ModuleList() {}

    /**
     * Creates a ModuleList using the Modules in the {@code toBeCopied}
     */
    public ModuleList(ReadOnlyModuleList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * * Resets the existing data of this {@code ModuleList} with {@code newData}.
     */
    public void resetData(ReadOnlyModuleList newData) {
        requireNonNull(newData);
        setModule(newData.getModuleList());
    }

    //// Module-level operations

    /**
     * Returns true if a module with the same identity as {@code module} exists in the module list.
     */
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return modules.contains(module);
    }

    /**
     * Adds a module to the module list.
     * The module must not already exist in the module list.
     */
    public void addModule(Module m) {
        modules.add(m);
    }
    /**
     * Replaces the contents of the module list with {@code module}.
     * {@code module} must not contain duplicate module.
     */
    public void setModule(List<Module> modules) {
        this.modules.setModules(modules);
    }

    /**
     * Replaces the given module {@code target} in the list with {@code editedModule}.
     * {@code target} must exist in the module list.
     * The module identity of {@code editedModule} must not be the same as another existing module in the module list.
     */
    public void setModule(Module target, Module editedModule) {
        requireNonNull(editedModule);
        modules.setModule(target, editedModule);
    }

    /**
     * Removes {@code key} from this {@code ModuleList}.
     * {@code key} must exist in the module list.
     */
    public void removeModule(Module key) {
        modules.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return modules.asUnmodifiableObservableList().size() + " modules";
        // TODO: refine later
    }

    @Override
    public ObservableList<Module> getModuleList() {
        return modules.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleList // instanceof handles nulls
                && modules.equals(((ModuleList) other).modules));
    }

    @Override
    public int hashCode() {
        return modules.hashCode();
    }
}
