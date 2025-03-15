package app.ddd.gsandwiches.shared.application.exceptions;

/**
 * Indicates that a conflict with another entity ocurred
 */
public abstract class ConflictException extends ApplicationException {

    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }

}
