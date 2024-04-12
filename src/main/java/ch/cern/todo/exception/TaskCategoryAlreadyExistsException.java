package ch.cern.todo.exception;

public class TaskCategoryAlreadyExistsException extends RuntimeException {


    public TaskCategoryAlreadyExistsException(String categoryName) {
        super("Task Category with name '" + categoryName + "' already exists");
    }

}
