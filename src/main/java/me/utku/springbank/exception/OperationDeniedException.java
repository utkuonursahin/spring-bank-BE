package me.utku.springbank.exception;

public class OperationDeniedException extends RuntimeException {
    public OperationDeniedException(String message) {
        super(message);
    }

    public static OperationDeniedException of(String message) {
        return new OperationDeniedException(message);
    }
}
