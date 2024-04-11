package ch.cern.todo.dto.mapper.impl;

import ch.cern.todo.dto.TaskCategoryDTO;
import ch.cern.todo.dto.mapper.TaskCategoryMapper;
import ch.cern.todo.entity.TaskCategory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskCategoryMapperImpl implements TaskCategoryMapper {
    @Override
    public TaskCategory toTaskCategory(TaskCategoryDTO taskCategoryDTO) {
        if (taskCategoryDTO == null) {
            return null;
        }
        TaskCategory taskCategory = new TaskCategory();
        taskCategory.setId(taskCategoryDTO.getId());
        taskCategory.setName(taskCategoryDTO.getName());
        taskCategory.setDescription(taskCategoryDTO.getDescription());
        return taskCategory;
    }

    @Override
    public TaskCategoryDTO toTaskCategoryDTO(TaskCategory taskCategory) {
        if (taskCategory == null) {
            return null;
        }
        TaskCategoryDTO taskCategoryDTO = new TaskCategoryDTO();
        taskCategoryDTO.setId(taskCategory.getId());
        taskCategoryDTO.setName(taskCategory.getName());
        taskCategoryDTO.setDescription(taskCategory.getDescription());
        return taskCategoryDTO;
    }

    @Override
    public List<TaskCategoryDTO> toTaskCategoryDTOList(List<TaskCategory> taskCategories) {
        if (taskCategories == null) {
            return null;
        }
        return taskCategories.stream().map(this::toTaskCategoryDTO).toList();
    }

    @Override
    public List<TaskCategory> toTaskCategoryList(List<TaskCategoryDTO> taskCategoriesDTOList) {
        if (taskCategoriesDTOList == null) {
            return null;
        }
        return taskCategoriesDTOList.stream().map(this::toTaskCategory).toList();
    }
}
