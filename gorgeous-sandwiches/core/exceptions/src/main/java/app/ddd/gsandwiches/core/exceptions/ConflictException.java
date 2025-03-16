package app.ddd.gsandwiches.core.exceptions;

/**
 * Exception for errors representing conflicts with the system state
 */
public abstract class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }

}
