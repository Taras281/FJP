package main;

import main.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Storage {

    private static int currentId = 1;
    private static final Map<Integer, Task> tasks = new ConcurrentHashMap<>();

    private static synchronized void increment() {
        currentId++;
    }

    public static int addToDo(Task task) {
        tasks.put(currentId, task);
        increment();
        return currentId;
    }

    public static List<Task> getAllTusks() {
        List<Task> toDoList = new ArrayList<>();
        toDoList.addAll(tasks.values());
        return toDoList;
    }

    public static Task getTask(int taskId) {
        if (tasks.containsKey(taskId)) {
            return tasks.get(taskId);
        }
        return null;
    }

    public static void deleteAllTasks() {
        tasks.clear();
    }

    public static void deleteTask(int taskId) {
        tasks.remove(taskId);
    }

    public static void changeTask(int taskId, Task task) {
        if (tasks.containsKey(taskId)) {
            tasks.replace(taskId, task);
        }
    }
}
