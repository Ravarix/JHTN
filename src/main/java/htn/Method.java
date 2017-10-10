package htn;

import htn.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Doug on 10/8/2017.
 */
public class Method<T> {
    Predicate<T> condition;
    List<Task<T>> subtasks = new ArrayList<>();

    public Method(Predicate<T> condition, Task<T> subtask1) {
        this.condition = condition;
        subtasks.add(subtask1);
    }

    public Method(Predicate<T> condition, Task<T> subtask1, Task<T> subtask2) {
        this(condition, subtask1);
        subtasks.add(subtask2);
    }

    public Method(Predicate<T> condition, Task<T> subtask1, Task<T> subtask2, Task<T> subtask3) {
        this(condition, subtask1, subtask2);
        subtasks.add(subtask3);
    }

    public Method(Predicate<T> condition, Task<T> subtask1, Task<T> subtask2, Task<T> subtask3, Task<T> subtask4) {
        this(condition, subtask1, subtask2, subtask3);
        subtasks.add(subtask4);
    }
}
