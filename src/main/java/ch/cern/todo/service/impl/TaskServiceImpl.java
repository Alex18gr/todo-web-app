package ch.cern.todo.service.impl;

import ch.cern.todo.dto.TaskDTO;
import ch.cern.todo.dto.mapper.TaskMapper;
import ch.cern.todo.entity.Task;
import ch.cern.todo.repository.TaskRepository;
import ch.cern.todo.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    private Task createNewTask(TaskDTO taskDTO) {
        // because we are creating task, we need to set id to null
        taskDTO.setId(null);
        return taskRepository.save(taskMapper.toTask(taskDTO));
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
                    return taskRepository.save(task);
                }).orElseGet(() -> createNewTask(taskDTO))
        );
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}
