package domain;

import java.time.ZonedDateTime;

public class Task {
    private int id;
    private String description;
    private String status;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public Task(){}

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public Task(int id, String description, String status) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt;
    }
}
