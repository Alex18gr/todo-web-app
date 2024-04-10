package ch.cern.todo.service;

import ch.cern.todo.dto.TaskDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface TaskService {

    TaskDTO createTask(TaskDTO taskDTO);

    Page<TaskDTO> getTasks(Pageable pageable);

    TaskDTO getTask(Long id);

    TaskDTO updateTask(Long id, TaskDTO taskDTO);

    void deleteTask(Long id);

}
