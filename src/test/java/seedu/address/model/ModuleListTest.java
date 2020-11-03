package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOMLINK_CS2103T;
import static seedu.address.testutil.Assert.assertThrows;
// import static seedu.address.testutil.TypicalModules.CS2030;
// import static seedu.address.testutil.TypicalModules.getTypicalModuleList;

// import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
// import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.module.Module;
// import seedu.address.model.module.exceptions.DuplicateModulesException;
// import seedu.address.testutil.ModuleBuilder;

public class ModuleListTest {

    private final ModuleList moduleList = new ModuleList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), moduleList.getModuleList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleList.resetData(null));
    }

    /*
    @Test
    public void resetData_withValidReadOnlyModuleList_replacesData() {
        ModuleList newData = getTypicalModuleList();
        moduleList.resetData(newData);
        assertEquals(newData, moduleList);
    }
     */

    /*
    @Test
    public void resetData_withDuplicateModules_throwsDuplicateModuleException() {
        // Two modules with the same name
        Module editedCS2030 = new ModuleBuilder(CS2030).withZoomLink(VALID_ZOOMLINK_CS2103T)
                .build();
        List<Module> newModules = Arrays.asList(CS2030, editedCS2030);
        ModuleListStub newData = new ModuleListStub(newModules);

        // assertThrows(DuplicateModuleException.class, () -> moduleList.resetData(newData));
    }
    */

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleList.hasModule(null));
    }

    /*
    @Test
    public void hasModule_moduleNotInModuleList_returnsFalse() {
        assertFalse(moduleList.hasModule(CS2030));
    }
     */

    /*
    @Test
    public void hasModule_moduleInModuleList_returnsTrue() {
        moduleList.addModule(CS2030);
        assertTrue(moduleList.hasModule(CS2030));
    }
     */

    /*
    @Test
    public void hasModule_moduleWithSameNameModuleList_returnsTrue() {
        moduleList.addModule(CS2030);
        Module editedCS2030 = new ModuleBuilder(CS2030).withZoomLink(VALID_ZOOMLINK_CS2103T)
                .build();
        assertTrue(moduleList.hasModule(editedCS2030));
    }
     */

    @Test
    public void getModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> moduleList.getModuleList().remove(0));
    }

    /**
     * A stub ReadOnlyModuleList whose modules list can violate interface constraints.
     */
    private static class ModuleListStub implements ReadOnlyModuleList {
        private final ObservableList<Module> modules = FXCollections.observableArrayList();

        ModuleListStub(Collection<Module> modules) {
            this.modules.setAll(modules);
        }

        @Override
        public ObservableList<Module> getModuleList() {
            return modules;
        }
    }

}
