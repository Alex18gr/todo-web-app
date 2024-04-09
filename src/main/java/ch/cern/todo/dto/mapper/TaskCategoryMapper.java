package ch.cern.todo.dto.mapper;

import ch.cern.todo.dto.TaskCategoryDTO;
import ch.cern.todo.entity.TaskCategory;

import java.util.List;

public interface TaskCategoryMapper {

    TaskCategory toTaskCategory(TaskCategoryDTO taskCategoryDTO);

    TaskCategoryDTO toTaskCategoryDTO(TaskCategory taskCategory);

}
