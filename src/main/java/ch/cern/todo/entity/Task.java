package ch.cern.todo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TASKS")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TASK_ID", columnDefinition = "NUMBER")
    private Long id;

    @Column(name = "TASK_NAME")
    private String name;

    @Column(name = "TASK_DESCRIPTION")
    private String description;

    @Column(name = "DEADLINE", columnDefinition = "TIMESTAMP")
    private LocalDateTime deadline;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CATEGORY_ID", columnDefinition = "NUMBER", foreignKey = @ForeignKey(name = "TASK_CATEGORIES_FK"))
    private TaskCategory category;

    public Long getId() {
        return id;
    }

    public void setId(Long taskId) {
        this.id = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String taskName) {
        this.name = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String taskDescription) {
        this.description = taskDescription;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory taskCategory) {
        this.category = taskCategory;
    }
}
