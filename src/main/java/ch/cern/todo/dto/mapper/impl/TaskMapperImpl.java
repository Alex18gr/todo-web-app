package ch.cern.todo.dto.mapper.impl;

import ch.cern.todo.dto.TaskDTO;
import ch.cern.todo.dto.mapper.TaskCategoryMapper;
import ch.cern.todo.dto.mapper.TaskMapper;
import ch.cern.todo.entity.Task;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskMapperImpl implements TaskMapper {

    private final TaskCategoryMapper taskCategoryMapper;

    public TaskMapperImpl(TaskCategoryMapper taskCategoryMapper) {
        this.taskCategoryMapper = taskCategoryMapper;
    }

    @Override
    public Task toTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setDeadline(taskDTO.getDeadline());
        task.setCategory(taskCategoryMapper.toTaskCategory(taskDTO.getCategory()));
        return task;
    }

    @Override
    public TaskDTO toTaskDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setName(task.getName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setDeadline(task.getDeadline());
        taskDTO.setCategory(taskCategoryMapper.toTaskCategoryDTO(task.getCategory()));
        return taskDTO;
    }

    @Override
    public List<Task> toTaskList(List<TaskDTO> taskDTOList) {
        return taskDTOList.stream().map(this::toTask).toList();
    }

    @Override
    public List<TaskDTO> toTaskDTOList(List<Task> taskList) {
        return taskList.stream().map(this::toTaskDTO).toList();
    }
}
