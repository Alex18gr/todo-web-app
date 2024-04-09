package ch.cern.todo.service;

import ch.cern.todo.dto.TaskDTO;
import ch.cern.todo.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

    TaskDTO createTask(TaskDTO taskDTO);

    Page<TaskDTO> getTasks(Pageable pageable);

    TaskDTO getTask(Long id);

}
