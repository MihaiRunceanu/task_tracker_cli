package repository;

import domain.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskRepositoryTest {

    private final TaskRepository repository = new TaskRepository(new ArrayList<>(), "D:\\IntelliJ IDEA\\Task_Manager\\TaskManager\\src\\tests.json");

    @AfterEach
    void setUp() {
        File file = new File("D:\\IntelliJ IDEA\\Task_Manager\\TaskManager\\src\\tests.json");
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void getTasks() {
        assertEquals(repository.getTasks().size(), 0);
    }

    @Test
    void storeTask() {
        repository.storeTask("new");
        assertEquals(repository.getTasks().size(), 1);
    }

    @Test
    void updateTask() {
        repository.storeTask("new");

        repository.updateTask(repository.getTasks().get(0), "updated");
        assertEquals(repository.getTasks().get(0).getDescription(), "updated");
    }

    @Test
    void removeTask() {
        repository.removeTask(1);
        assertEquals(repository.getTasks().size(), 0);
    }

}