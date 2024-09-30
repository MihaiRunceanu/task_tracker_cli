package repository;

import domain.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskRepositoryTest {

    private final TaskRepository repository = new TaskRepository(new ArrayList<>());

    @Test
    void getTasks() {
        assertEquals(repository.getTasks().size(), 0);
    }

    @Test
    void storeTask() {
        repository.storeTask(new Task(1, "new", "todo"));
        assertEquals(repository.getTasks().size(), 1);
    }

    @Test
    void updateTask() {
        repository.storeTask(new Task(1, "new", "todo"));

        repository.updateTask(repository.getTasks().get(0), "updated");
        assertEquals(repository.getTasks().get(0).getDescription(), "updated");
    }

    @Test
    void removeTask() {
        repository.removeTask(1);
        assertEquals(repository.getTasks().size(), 0);
    }

}