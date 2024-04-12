package ch.cern.todo.service.impl;

import ch.cern.todo.dto.TaskCategoryDTO;
import ch.cern.todo.dto.mapper.TaskCategoryMapper;
import ch.cern.todo.entity.TaskCategory;
import ch.cern.todo.exception.TaskCategoryAlreadyExists;
import ch.cern.todo.repository.TaskCategoryRepository;
import ch.cern.todo.service.TaskCategoryService;
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
    public List<TaskCategoryDTO> getTaskCategories() {
        return taskCategoryRepository.findAll().stream().map(taskCategoryMapper::toTaskCategoryDTO).toList();
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
        if (taskCategoryRepository.existsByName(taskCategoryDTO.getName())) {
            throw new TaskCategoryAlreadyExists(taskCategoryDTO.getName());
        }
        return taskCategoryMapper.toTaskCategoryDTO(createNewTaskCategory(taskCategoryDTO));
    }

    @Override
    public TaskCategoryDTO updateTaskCategory(Long id, TaskCategoryDTO taskCategoryDTO) {
        if (taskCategoryRepository.existsByName(taskCategoryDTO.getName())) {
            throw new TaskCategoryAlreadyExists(taskCategoryDTO.getName());
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
    public void deleteTaskCategory(Long id) {
        taskCategoryRepository.deleteById(id);
    }

}
