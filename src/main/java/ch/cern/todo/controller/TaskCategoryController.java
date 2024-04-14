package ch.cern.todo.controller;

import ch.cern.todo.dto.TaskCategoryDTO;
import ch.cern.todo.entity.TaskCategory;
import ch.cern.todo.service.TaskCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping
    public ResponseEntity<Page<TaskCategoryDTO>> getTaskCategories(Pageable pageable) {
        return ResponseEntity.ok(taskCategoryService.getTaskCategories(pageable));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<TaskCategoryDTO> getTaskCategoryByName(@PathVariable String name) {
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

    /**
     * Search task categories by name. Used by autocomplete field on create or edit tasks
     * @param name the part of the category name
     * @return a list of the categories which meet the criteria given
     */
    @GetMapping("/search-name")
    public ResponseEntity<List<TaskCategoryDTO>> searchTaskCategoryByName(@RequestParam String name) {
        return ResponseEntity.ok(taskCategoryService.searchForTaskCategoriesByCategoryName(name));
    }

    /**
     * checks if the tasks category of the given id has any tasks associated with it. It is used for checking before deleting in the FrontEnd
     * @param id the id of the task category to be checked
     * @return boolean value whether the task category has any tasks associated with it or not
     */
    @GetMapping("/{id}/has-tasks")
    public ResponseEntity<Boolean> hasTasks(@PathVariable long id) {
        return ResponseEntity.ok(taskCategoryService.checkIfTaskCategoryHasTasks(id));
    }

}
