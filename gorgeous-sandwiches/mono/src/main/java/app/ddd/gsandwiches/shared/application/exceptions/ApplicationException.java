package app.ddd.gsandwiches.shared.application.exceptions;

/**
 * Base exception for the system.
 * Should be extended with custom exceptions.
 */
public abstract class ApplicationException extends RuntimeException {

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
