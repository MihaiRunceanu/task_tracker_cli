package domain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    private final Task task = new Task(1,"Description", "todo");

    @Test
    void getId() {
        assertEquals(1, task.getId());
    }

    @Test
    void getDescription() {
        assertEquals("Description", task.getDescription());
    }

    @Test
    void setDescription() {
        task.setDescription("New Description");
        assertEquals("New Description", task.getDescription());
    }

    @Test
    void getStatus() {
        assertEquals("todo", task.getStatus());
    }

    @Test
    void setStatus() {
        task.setStatus("New Status");
        assertEquals("New Status", task.getStatus());
    }

    @Test
    void setUpdatedAt() {
        task.setUpdatedAt(ZonedDateTime.now());
        assertEquals(ZonedDateTime.now(), task.getUpdatedAt());
    }
}