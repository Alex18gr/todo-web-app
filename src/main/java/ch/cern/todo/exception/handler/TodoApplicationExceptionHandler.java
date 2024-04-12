package ch.cern.todo.exception.handler;

import ch.cern.todo.exception.RecordNotFoundException;
import ch.cern.todo.exception.TaskCategoryAlreadyExistsException;
import ch.cern.todo.exception.TaskCategoryHasAssociationsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TodoApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({TaskCategoryAlreadyExistsException.class})
    public ResponseEntity<Object> handleTaskCategoryAlreadyExistsException(RuntimeException ex) {

        ApiError error = new ApiError(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RecordNotFoundException.class})
    public ResponseEntity<Object> handleRecordNotFound(RuntimeException ex) {
        ApiError error = new ApiError(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({TaskCategoryHasAssociationsException.class})
    public ResponseEntity<Object> handleTaskCategoryHasAssociations(RuntimeException ex) {
        ApiError error = new ApiError(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
