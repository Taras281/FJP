package main;

import main.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoController {

    @PostMapping("/toDo/")
    public synchronized int addTask(Task task) {
        return Storage.addToDo(task);
    }

    @GetMapping("/toDo/")
    public synchronized List<Task> showAllTasks() {
        return Storage.getAllTusks();
    }

    @GetMapping("/toDo/{id}")
    public synchronized ResponseEntity getTask(@PathVariable int id) {
        Task task = Storage.getTask(id);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return new ResponseEntity(task, HttpStatus.OK);
        }
    }

    @DeleteMapping("/toDo/")
    public synchronized void deleteAllTasks() {
        Storage.deleteAllTasks();
    }


    @DeleteMapping("/toDo/{id}")
    public synchronized ResponseEntity deleteTask(@PathVariable int id) {
        Task task = Storage.getTask(id);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            Storage.deleteTask(id);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @PatchMapping("/toDo/{id}")
    public synchronized ResponseEntity changeTask(@PathVariable int id, Task newTask) {
        Task task = Storage.getTask(id);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            Storage.changeTask(id, newTask);
            return new ResponseEntity(HttpStatus.OK);
        }
    }


}
