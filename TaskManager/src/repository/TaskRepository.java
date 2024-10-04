package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import domain.Task;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;

public class TaskRepository {
    private ArrayList<Task> tasks;

    private final String fileName;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private int currentId = 1; //The ID that will be given to the next added task

    public TaskRepository(ArrayList<Task> tasks, String fileName) {
        this.tasks = tasks;
        this.fileName = fileName;

        //Registering the JavaTimeModule so that the LocalDateTime fields of the tasks are loaded and written properly
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        loadFromFile();
    }


    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void storeTask(String description) {
        Task task = new Task(currentId++, description, "todo");
        tasks.add(task);
        writeToFile();
    }

    public void removeTask(int id){
        tasks.removeIf(task -> task.getId() == id);
        writeToFile();
    }

    public void updateTask(Task task, String description) {
        task.setDescription(description);
        task.setUpdatedAt(ZonedDateTime.now());
        writeToFile();
    }

    private void loadFromFile() {
        try {
            File file = new File(fileName);
            if (file.exists()) {
                tasks = objectMapper.readValue(file, new TypeReference<ArrayList<Task>>() {
                });
                System.out.println("Loaded tasks: " + tasks);
                // Update currentId based on the highest task ID
                currentId = tasks.stream().mapToInt(Task::getId).max().orElse(0) + 1;
            } else {
                System.out.println("No tasks file found. Starting with an empty list.");
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks from file: " + e.getMessage());
            tasks = new ArrayList<>(); // Fallback to empty list if loading fails
        }
    }

    private void writeToFile(){
        try{
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT); //Used for more readable serialization

            objectMapper.writeValue(new File(fileName), tasks);
        } catch (IOException e){
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
