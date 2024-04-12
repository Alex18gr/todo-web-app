package ch.cern.todo.exception;

public class TaskCategoryAlreadyExists extends RuntimeException {


    public TaskCategoryAlreadyExists(String categoryName) {
        super("Task Category with name '" + categoryName + "' already exists");
    }

}
