package ch.cern.todo.service.impl;

import ch.cern.todo.dto.TaskDTO;
import ch.cern.todo.dto.mapper.TaskMapper;
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

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        // because we are creating task, we need to set id to null
        taskDTO.setId(null);
        return taskMapper.toTaskDTO(taskRepository.save(taskMapper.toTask(taskDTO)));
    }

    @Override
    public Page<TaskDTO> getTasks(Pageable pageable) {
        return taskRepository.findAll(pageable).map(taskMapper::toTaskDTO);
    }

    @Override
    public TaskDTO getTask(Long id) {
        return taskMapper.toTaskDTO(taskRepository.findById(id).orElse(null));
    }
}
