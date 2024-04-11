package ch.cern.todo.service;

import ch.cern.todo.dto.TaskCategoryDTO;
import ch.cern.todo.entity.TaskCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskCategoryService {

    List<TaskCategoryDTO> getTaskCategories();

    TaskCategoryDTO getTaskCategory(Long id);

    TaskCategoryDTO getTaskCategoryByName(String name);

    TaskCategoryDTO createTaskCategory(TaskCategoryDTO taskCategoryDTO);

    TaskCategoryDTO updateTaskCategory(Long id, TaskCategoryDTO taskCategoryDTO);

    TaskCategory createOrGetTaskCategory(TaskCategoryDTO taskCategoryDTO);

    void deleteTaskCategory(Long id);

}
