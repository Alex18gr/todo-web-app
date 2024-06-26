package ch.cern.todo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "TASK_CATEGORIES", uniqueConstraints = {
        @UniqueConstraint(name = "CATEGORY_NAME_UK", columnNames = {"CATEGORY_NAME"})
})
public class TaskCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID", columnDefinition = "NUMBER")
    private Long id;

    @Column(name = "CATEGORY_NAME")
    @NotNull
    private String name;

    @Column(name = "CATEGORY_DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Task> tasks;

    public Long getId() {
        return id;
    }

    public void setId(Long categoryId) {
        this.id = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String categoryName) {
        this.name = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String categoryDescription) {
        this.description = categoryDescription;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
