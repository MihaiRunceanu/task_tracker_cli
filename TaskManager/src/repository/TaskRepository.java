package repository;

import domain.Task;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class TaskRepository {
    private final ArrayList<Task> tasks;

    public TaskRepository(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void storeTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int id){
        tasks.removeIf(task -> task.getId() == id);
    }

    public void updateTask(Task task, String description) {
        task.setDescription(description);
        task.setUpdatedAt(ZonedDateTime.now());
    }
}
