package app.ddd.gsandwiches.core.spi.exception;

/**
 * Exception for errors representing conflicts with the system state
 */
public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }

}
