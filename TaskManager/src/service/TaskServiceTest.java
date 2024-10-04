package service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import repository.TaskRepository;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {

    private final TaskService taskService = new TaskService(new TaskRepository(new ArrayList<>(), "D:\\IntelliJ IDEA\\Task_Manager\\TaskManager\\src\\tests.json"));

    @AfterEach
    void setUp() {
        File file = new File("D:\\IntelliJ IDEA\\Task_Manager\\TaskManager\\src\\tests.json");
        if (file.exists()) {
            file.delete();
        }
    }


    @Test
    void getTasks() {
        assertEquals(0, taskService.getTasks().size());
    }

    @Test
    void addTask() {
        try{
            taskService.addTask("added task");
            assertEquals(1, taskService.getTasks().size());
            taskService.addTask("a");
        } catch (Exception e){
            assertEquals(e.getMessage(), "Invalid description.");
        }
    }

    @Test
    void updateTask() {
        try{
            taskService.addTask("added task");
            taskService.updateTask(1, "new description");
            assertEquals("new description", taskService.getTasks().get(0).getDescription());

            taskService.updateTask(2, "new description");
        } catch (Exception e){
            assertEquals(e.getMessage(), "Task not found.");
        }
    }

    @Test
    void deleteTask() {
        try{
            taskService.addTask("added task");
            taskService.deleteTask(1);
            assertEquals(0, taskService.getTasks().size());

            taskService.deleteTask(1);
        } catch (Exception e){
            assertEquals(e.getMessage(), "Task not found.");
        }
    }

    @Test
    void findTask() throws Exception {
        assertFalse(taskService.findTask(1));

        taskService.addTask("new task");
        assertTrue(taskService.findTask(1));
    }

    @Test
    void markAsDone() {
        try{
            taskService.addTask("added task");
            taskService.markAsDone(1);
            assertEquals(taskService.getTasks().get(0).getStatus(), "done");

            taskService.markAsDone(2);
        } catch (Exception e){
            assertEquals(e.getMessage(), "Task not found.");
        }
    }

    @Test
    void markAsInProgress() {
        try{
            taskService.addTask("added task");
            taskService.markAsInProgress(1);
            assertEquals(taskService.getTasks().get(0).getStatus(), "in-progress");

            taskService.markAsInProgress(2);
        } catch (Exception e){
            assertEquals(e.getMessage(), "Task not found.");
        }
    }
}