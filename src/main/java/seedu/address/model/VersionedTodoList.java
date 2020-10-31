package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.exceptions.VersionedListException;

public class VersionedTodoList extends TodoList {
    private static final String MESSAGE_NO_REDO_HISTORY = "There are no Todo List commands to redo";
    private static final String MESSAGE_NO_UNDO_HISTORY = "There are no Todo List commands to undo";
    private List<ReadOnlyTodoList> todoListStateList = new ArrayList<>();
    private int currentStatePointer;

    /**
     * Creates a versioned todo list using the todo lists in the {@code toBeCopied}
     * @param toBeCopied
     */
    public VersionedTodoList(ReadOnlyTodoList toBeCopied) {
        super(toBeCopied);
        todoListStateList.add(toBeCopied);
        this.currentStatePointer = 0;
    }
    /**
     * Saves the current todo list state in history.
     */
    public void commit(ReadOnlyTodoList todoList) {
        todoListStateList.subList(this.currentStatePointer + 1, todoListStateList.size()).clear();
        todoListStateList.add(new TodoList(todoList));
        this.currentStatePointer += 1;
    }

    /**
     * Restores the previous todo list state from history.
     */
    public void undo() throws VersionedListException {
        if (isIndexZero()) {
            throw new VersionedListException(MESSAGE_NO_UNDO_HISTORY);
        }
        this.currentStatePointer -= 1;
    }

    /**
     * Restores the previously undone todo list state from history.
     */
    public void redo() throws VersionedListException {
        if (isLastIndex()) {
            throw new VersionedListException(MESSAGE_NO_REDO_HISTORY);
        }
        assert !isLastIndex() : "Assertion error, there are no instructions to redo";
        this.currentStatePointer += 1;
    }

    public boolean isIndexZero() {
        return currentStatePointer == 0;
    }

    public boolean isLastIndex() {
        return currentStatePointer >= todoListStateList.size() - 1;
    }

    /**
     * Returns the module list the current state pointer is pointing to in the form
     * of an observable list
     */
    public ReadOnlyTodoList getCurrentTodoList() {
        return todoListStateList.get(currentStatePointer);
    }
}
