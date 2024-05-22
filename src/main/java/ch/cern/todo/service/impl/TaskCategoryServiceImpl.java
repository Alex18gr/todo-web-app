package ch.cern.todo.service.impl;

import ch.cern.todo.dto.TaskCategoryDTO;
import ch.cern.todo.dto.mapper.TaskCategoryMapper;
import ch.cern.todo.entity.TaskCategory;
import ch.cern.todo.exception.RecordNotFoundException;
import ch.cern.todo.exception.TaskCategoryAlreadyExistsException;
import ch.cern.todo.exception.TaskCategoryHasAssociationsException;
import ch.cern.todo.repository.TaskCategoryRepository;
import ch.cern.todo.service.TaskCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskCategoryServiceImpl implements TaskCategoryService {

    private final TaskCategoryRepository taskCategoryRepository;
    private final TaskCategoryMapper taskCategoryMapper;

    public TaskCategoryServiceImpl(TaskCategoryRepository taskCategoryRepository, TaskCategoryMapper taskCategoryMapper) {
        this.taskCategoryRepository = taskCategoryRepository;
        this.taskCategoryMapper = taskCategoryMapper;
    }


    @Override
    public Page<TaskCategoryDTO> getTaskCategories(Pageable pageable) {
        return taskCategoryRepository.findAll(pageable).map(taskCategoryMapper::toTaskCategoryDTO);
    }

    @Override
    public TaskCategoryDTO getTaskCategory(Long id) {
        return taskCategoryMapper.toTaskCategoryDTO(taskCategoryRepository.findById(id).orElse(null));
    }

    @Override
    public TaskCategoryDTO getTaskCategoryByName(String name) {
        return taskCategoryMapper.toTaskCategoryDTO(taskCategoryRepository.findByName(name).orElse(null));
    }

    @Override
    public TaskCategoryDTO createTaskCategory(TaskCategoryDTO taskCategoryDTO) {
        // check if the name of the task already exists
        if (taskCategoryRepository.existsByName(taskCategoryDTO.getName())) {
            throw new TaskCategoryAlreadyExistsException(taskCategoryDTO.getName());
        }
        return taskCategoryMapper.toTaskCategoryDTO(createNewTaskCategory(taskCategoryDTO));
    }

    @Override
    public TaskCategoryDTO updateTaskCategory(Long id, TaskCategoryDTO taskCategoryDTO) {
        // check if the name of the update task already exists in different task category
        if (taskCategoryRepository.existsByNameAndIdNot(taskCategoryDTO.getName(), id)) {
            throw new TaskCategoryAlreadyExistsException(taskCategoryDTO.getName());
        }
        return taskCategoryMapper.toTaskCategoryDTO(
                taskCategoryRepository.findById(id).map(taskCategory -> {
                    taskCategory.setName(taskCategoryDTO.getName());
                    taskCategory.setDescription(taskCategoryDTO.getDescription());
                    return taskCategoryRepository.save(taskCategory);
                }).orElseGet(() -> createNewTaskCategory(taskCategoryDTO))
        );
    }

    private TaskCategory createNewTaskCategory(TaskCategoryDTO taskCategoryDTO) {
        TaskCategory saveTaskCategory = taskCategoryMapper.toTaskCategory(taskCategoryDTO);
        // because we are creating task category, we need to set id to null
        saveTaskCategory.setId(null);
        return taskCategoryRepository.save(saveTaskCategory);
    }

    @Override
    public TaskCategory createOrGetTaskCategory(TaskCategoryDTO taskCategoryDTO) {
        return taskCategoryRepository.findByName(taskCategoryDTO.getName()).orElseGet(() -> createNewTaskCategory(taskCategoryDTO));
    }

    @Override
    public boolean checkIfTaskCategoryHasTasks(Long id) {
        if (!taskCategoryRepository.existsById(id)) {
            throw new RecordNotFoundException(id, "Task category");
        }
        return taskCategoryRepository.hasTasksById(id);
    }

    @Override
    public void deleteTaskCategory(final Long id) {
        // we check if the task category exists with the given id
        TaskCategory category = taskCategoryRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id, "TaskCategory"));

        // we check if the category has any tasks related to it, because it that case, we will have a referential
        // integrity constraint violation
        if (taskCategoryRepository.hasTasksById(id)) {
            throw new TaskCategoryHasAssociationsException(category.getName());
        }

        taskCategoryRepository.delete(category);
    }

    @Override
    public List<TaskCategoryDTO> searchForTaskCategoriesByCategoryName(String categoryName) {
        return taskCategoryMapper.toTaskCategoryDTOList(
                taskCategoryRepository.findAllByNameLimit(categoryName, Pageable.ofSize(12))
        );
    }

}
