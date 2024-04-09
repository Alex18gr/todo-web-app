package ch.cern.todo.dto.mapper;

import ch.cern.todo.dto.TaskDTO;
import ch.cern.todo.entity.Task;

import java.util.List;

public interface TaskMapper {

    Task toTask(final TaskDTO taskDTO);

    TaskDTO toTaskDTO(final Task task);

    List<Task> toTaskList(final List<TaskDTO> taskDTOList);

    List<TaskDTO> toTaskDTOList(final List<Task> taskList);

}
