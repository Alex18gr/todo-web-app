package ch.cern.todo.service;

import ch.cern.todo.dto.TaskCategoryDTO;
import ch.cern.todo.entity.TaskCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskCategoryService {

    Page<TaskCategoryDTO> getTaskCategories(Pageable pageable);

    TaskCategoryDTO getTaskCategory(Long id);

    TaskCategoryDTO getTaskCategoryByName(String name);

    TaskCategoryDTO createTaskCategory(TaskCategoryDTO taskCategoryDTO);

    TaskCategoryDTO updateTaskCategory(Long id, TaskCategoryDTO taskCategoryDTO);

    TaskCategory createOrGetTaskCategory(TaskCategoryDTO taskCategoryDTO);

    boolean checkIfTaskCategoryHasTasks(Long id);

    void deleteTaskCategory(Long id);

    List<TaskCategoryDTO> searchForTaskCategoriesByCategoryName(String categoryName);

}
