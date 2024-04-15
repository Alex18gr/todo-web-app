package ch.cern.todo.service.impl;

import ch.cern.todo.dto.TaskDTO;
import ch.cern.todo.dto.mapper.TaskMapper;
import ch.cern.todo.entity.Task;
import ch.cern.todo.exception.RecordNotFoundException;
import ch.cern.todo.repository.TaskRepository;
import ch.cern.todo.service.TaskCategoryService;
import ch.cern.todo.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TaskCategoryService taskCategoryService;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, TaskCategoryService taskCategoryService) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.taskCategoryService = taskCategoryService;
    }

    private Task createNewTask(TaskDTO taskDTO) {
        Task saveTask = taskMapper.toTask(taskDTO);
        // because we are creating task, we need to set id to null
        saveTask.setId(null);
        saveTask.setCategory(taskCategoryService.createOrGetTaskCategory(taskDTO.getCategory()));
        return taskRepository.save(saveTask);
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        return taskMapper.toTaskDTO(createNewTask(taskDTO));
    }

    @Override
    public Page<TaskDTO> getTasks(Pageable pageable) {
        return taskRepository.findAll(pageable).map(taskMapper::toTaskDTO);
    }

    @Override
    public TaskDTO getTask(Long id) {
        return taskMapper.toTaskDTO(taskRepository.findById(id).orElse(null));
    }

    @Override
    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        return taskMapper.toTaskDTO(
                taskRepository.findById(id).map(task -> {
                    task.setName(taskDTO.getName());
                    task.setDescription(taskDTO.getDescription());
                    task.setCategory(taskCategoryService.createOrGetTaskCategory(taskDTO.getCategory()));
                    task.setDeadline(taskDTO.getDeadline());
                    return taskRepository.save(task);
                }).orElseGet(() -> createNewTask(taskDTO))
        );
    }

    @Override
    public void deleteTask(final Long id) {
        Task task = taskRepository.findById(id)
                        .orElseThrow(() -> new RecordNotFoundException(id, "Task"));
        taskRepository.delete(task);
    }

}
