package ch.cern.todo.exception.handler;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

public class ApiError {

    private HttpStatus status;
    private String message;
    private Long timestamp;

    public ApiError(HttpStatus status, String message, Long timestamp) {
        super();
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
