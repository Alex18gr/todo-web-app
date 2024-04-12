package ch.cern.todo.exception.handler;

import ch.cern.todo.exception.TaskCategoryAlreadyExists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TodoApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({TaskCategoryAlreadyExists.class})
    public ResponseEntity<Object> handleTaskCategoryAlreadyExistsException(RuntimeException ex) {

        ApiError error = new ApiError(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
