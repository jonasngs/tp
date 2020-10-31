package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTodoList;

/**
 * Wraps all data at the todo list level.
 * Duplicates are not allowed (by .isSameTask comparison)
 */
public class TodoList implements ReadOnlyTodoList {

    private final UniqueTodoList tasks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        tasks = new UniqueTodoList();
    }

    public TodoList() {}

    /**
     * Creates a TodoList using the tasks in the {@code toBeCopied}
     */
    public TodoList(ReadOnlyTodoList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * * Resets the existing data of this {@code TodoList} with {@code newData}.
     */
    public void resetData(ReadOnlyTodoList newData) {
        requireNonNull(newData);

        setTask(newData.getTodoList());
    }

    //// task-level operation

    /**
     * Returns true if a task with the same name as {@code tasks} exists in the todo list.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Adds a task to the todo list.
     * The task must not already exist in the todo list.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Replaces the contents of the todo list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTask(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the todo list.
     * The name of the task {@code editedTask} must not be the same as another
     * existing task in the todo list.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);
        tasks.setTask(target, editedTask);
    }

    /**
     * Removes {@code key} from this {@code TodoList}.
     * {@code key} must exist in the todo list.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return tasks.asUnmodifiableObservableList().size() + " tasks";
    }

    @Override
    public ObservableList<Task> getTodoList() {
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TodoList // instanceof handles nulls
            && tasks.equals(((TodoList) other).tasks));
    }

    @Override
    public int hashCode() {
        return tasks.hashCode();
    }
}
