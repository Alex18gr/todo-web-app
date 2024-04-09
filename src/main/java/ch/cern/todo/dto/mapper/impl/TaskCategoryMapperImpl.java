package ch.cern.todo.dto.mapper.impl;

import ch.cern.todo.dto.TaskCategoryDTO;
import ch.cern.todo.dto.mapper.TaskCategoryMapper;
import ch.cern.todo.entity.TaskCategory;
import org.springframework.stereotype.Component;

@Component
public class TaskCategoryMapperImpl implements TaskCategoryMapper {
    @Override
    public TaskCategory toTaskCategory(TaskCategoryDTO taskCategoryDTO) {
        TaskCategory taskCategory = new TaskCategory();
        taskCategory.setId(taskCategory.getId());
        taskCategory.setName(taskCategory.getName());
        taskCategory.setDescription(taskCategory.getDescription());
        return taskCategory;
    }

    @Override
    public TaskCategoryDTO toTaskCategoryDTO(TaskCategory taskCategory) {
        TaskCategoryDTO taskCategoryDTO = new TaskCategoryDTO();
        taskCategoryDTO.setId(taskCategory.getId());
        taskCategoryDTO.setName(taskCategory.getName());
        taskCategoryDTO.setDescription(taskCategory.getDescription());
        return taskCategoryDTO;
    }
}
