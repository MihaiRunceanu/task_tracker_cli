package service;

import domain.Task;
import repository.TaskRepository;


public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(String description) throws Exception {
        if (description.length() < 3) {
            throw new Exception("Invalid description.");
        }
        Task task = new Task(taskRepository.getTasks().size()+1, description, "todo");
        taskRepository.storeTask(task);
    }

    public void updateTask(int id, String description) throws Exception {
        if (!findTask(id)){
            throw new Exception("Task not found.");
        }
        for (Task task : taskRepository.getTasks()) {
            if (task.getId() == id) {
                taskRepository.updateTask(task, description);
            }
        }
    }

    public void deleteTask(int id) throws Exception {
        if (!findTask(id)){
            throw new Exception("Task not found.");
        }
        taskRepository.removeTask(id);
    }

    public boolean findTask(int id){
        for (Task task : taskRepository.getTasks()) {
            if (task.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void markAsDone(int id) throws Exception {
        if (!findTask(id)){
            throw new Exception("Task not found.");
        }
        for (Task task : taskRepository.getTasks()) {
            if (task.getId() == id) {
                task.setStatus("done");
            }
        }
    }

    public void markAsInProgress(int id) throws Exception {
        if (!findTask(id)){
            throw new Exception("Task not found.");
        }
        for (Task task : taskRepository.getTasks()) {
            if (task.getId() == id) {
                task.setStatus("in-progress");
            }
        }
    }

}
