package ru.job4j.concurrent.cache;

public class OptimisticException extends RuntimeException {
    public OptimisticException() {
    }

    public OptimisticException(String message) {
        super(message);
    }

    public OptimisticException(String message, Throwable cause) {
        super(message, cause);
    }
}
