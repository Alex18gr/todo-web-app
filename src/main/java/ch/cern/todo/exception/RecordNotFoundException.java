package ch.cern.todo.exception;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(Long id, String recordName) {
        super("Record '" + recordName + "' not found for id '" + id + "'");
    }

}
