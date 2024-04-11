package ch.cern.todo.controller;

import ch.cern.todo.dto.TaskCategoryDTO;
import ch.cern.todo.entity.TaskCategory;
import ch.cern.todo.service.TaskCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task-categories")
public class TaskCategoryController {

    private final TaskCategoryService taskCategoryService;

    public TaskCategoryController(TaskCategoryService taskCategoryService) {
        this.taskCategoryService = taskCategoryService;
    }

//    @GetMapping
//    public ResponseEntity<List<TaskCategoryDTO>> getTaskCategories() {
//        return ResponseEntity.ok(taskCategoryService.getTaskCategories());
//    }

    @GetMapping
    public ResponseEntity<TaskCategoryDTO> getTaskCategoryByName(@RequestParam String name) {
        return ResponseEntity.ok(taskCategoryService.getTaskCategoryByName(name));
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskCategoryDTO> getTaskCategoryById(@PathVariable long id) {
        return ResponseEntity.ok(taskCategoryService.getTaskCategory(id));
    }

    @PostMapping
    public ResponseEntity<TaskCategoryDTO> createTaskCategory(@RequestBody TaskCategoryDTO taskCategoryDTO) {
        return ResponseEntity.ok(taskCategoryService.createTaskCategory(taskCategoryDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<TaskCategoryDTO> updateTaskCategory(@PathVariable long id, @RequestBody TaskCategoryDTO taskCategoryDTO) {
        return ResponseEntity.ok(taskCategoryService.updateTaskCategory(id, taskCategoryDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTaskCategory(@PathVariable long id) {
        taskCategoryService.deleteTaskCategory(id);
        return ResponseEntity.noContent().build();
    }

}
