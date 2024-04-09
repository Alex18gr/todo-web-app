package ch.cern.todo.controller;

import ch.cern.todo.dto.TaskDTO;
import ch.cern.todo.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tasks")
public class TaskController {

    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.createTask(taskDTO));
    }

    @GetMapping
    public ResponseEntity<Page<TaskDTO>> getTasks(Pageable pageable) {
        return ResponseEntity.ok(taskService.getTasks(pageable));
    }

    @GetMapping(":id")
    public ResponseEntity<TaskDTO> getTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }
}
