package ch.cern.todo.exception;

public class TaskCategoryHasAssociationsException extends RuntimeException {

    public TaskCategoryHasAssociationsException(String taskCategoryName) {
        super("Task category '" + taskCategoryName + "' has one or more tasks associated with it");
    }

}
