package app.ddd.gsandwiches.core.exceptions;

/**
 * Exception for generic expected errors
 */
public class ApplicationException extends RuntimeException {

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
